package ch.buedev.iot_coap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ch.buedev.iot_coap.datasources.CoapBackendDatasource
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.ui.nav.CoapBackendNavType
import ch.buedev.iot_coap.ui.pages.CoapBackendDetailPage
import ch.buedev.iot_coap.ui.pages.CoapBackendListPage

const val TAG = "MainActivity"

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = getString(R.string.route_coap_backend_list_page)
            ) {
                composable(route = getString(R.string.route_coap_backend_list_page)) {
                    CoapBackendListPage(
                        coapBackends = CoapBackendDatasource.loadCoapBackends(),
                        navController = navController
                    )
                }
                composable(
                    route = getString(R.string.route_coap_backend_detail_page) + "/{coapBackend}?isNew={isNew}",
                    arguments = listOf(
                        navArgument("coapBackend") {
                            type = CoapBackendNavType()
                        },
                        navArgument("isNew") {
                            defaultValue = "false"
                        }
                    )
                ) {
                    CoapBackendDetailPage(
                        coapBackend = it.arguments?.getParcelable<CoapBackend>("coapBackend")!!,
                        isNew = it.arguments?.getString("isNew").toBoolean(),
                        navController = navController
                    )
                }
            }
        }
    }
}
