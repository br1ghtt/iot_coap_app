package ch.buedev.iot_coap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import ch.buedev.iot_coap.ui.viewmodel.CoapBackendFormViewModel

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
            var coapBackendList by remember { mutableStateOf(listOf<CoapBackend>()) }

            NavHost(
                navController = navController,
                startDestination = getString(R.string.route_coap_backend_list_page)
            ) {
                composable(route = getString(R.string.route_coap_backend_list_page)) {
                    coapBackendService.load {
                        coapBackendList = it
                    }
                    CoapBackendListPage(
                        coapBackends = coapBackendList,
                        navController = navController
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
            }
        }
    }
}
