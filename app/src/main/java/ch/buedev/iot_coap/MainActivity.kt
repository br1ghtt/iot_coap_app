package ch.buedev.iot_coap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.services.CoapBackendService
import ch.buedev.iot_coap.services.impl.CoapBackendServiceSharedPref
import ch.buedev.iot_coap.ui.nav.CoapBackendNavType
import ch.buedev.iot_coap.ui.page.CoapBackendFormPage
import ch.buedev.iot_coap.ui.page.CoapBackendListPage
import ch.buedev.iot_coap.ui.page.CoapRequestPage
import ch.buedev.iot_coap.ui.viewmodel.CoapBackendFormViewModel
import ch.buedev.iot_coap.ui.viewmodel.CoapBackendListViewModel
import ch.buedev.iot_coap.ui.viewmodel.CoapRequestViewModel

private const val TAG = "MainActivity"

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private lateinit var _coapBackendService: CoapBackendService
    private val coapBackendService: CoapBackendService get() = _coapBackendService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _coapBackendService = CoapBackendServiceSharedPref(this)

        setContent {
            val navController = rememberNavController()
            this.window.navigationBarColor = MaterialTheme.colors.primaryVariant.toArgb()

            NavHost(
                navController = navController,
                startDestination = getString(R.string.route_coap_backend_list_page)
            ) {
                composable(route = getString(R.string.route_coap_backend_list_page)) {
                    CoapBackendListPage(
                        CoapBackendListViewModel(coapBackendService, navController, this@MainActivity)
                    )
                }
                composable(
                    route = getString(R.string.route_coap_backend_detail_page) + "/{coapBackend}",
                    arguments = listOf(
                        navArgument("coapBackend") {
                            type = CoapBackendNavType()
                        }
                    )
                ) {
                    CoapBackendFormPage(
                        CoapBackendFormViewModel(
                            it.arguments?.getParcelable<CoapBackend>(
                                "coapBackend"
                            )!!,
                            coapBackendService, navController, this@MainActivity
                        ),
                    )
                }
                composable(route = getString(R.string.route_coap_request_page)) {
                    CoapRequestPage(modelView = CoapRequestViewModel())
                }
            }
        }
    }
}
