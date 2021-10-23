package ch.buedev.iot_coap

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.ui.components.CoapBackendForm
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val title = "Coap Backends"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = getString(R.string.route_coap_backend_list_page)
            ) {
                composable(route = getString(R.string.route_coap_backend_list_page)) {
                    CoapBackendListPage(title = title, navController = navController)
                }
            }
        }
    }

    @ExperimentalMaterialApi
    @Composable
    fun CoapBackendListPage(title: String, navController: NavController) {
        IoTCoAPTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = title)
                        }
                    )
                },
                content = {
                    CoapBackendForm()
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Coap Backend")
                    }
                }
            )
        }
    }

    @ExperimentalMaterialApi
    @Preview
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewCoapBackendListPage() {
        val navController = rememberNavController()
        CoapBackendListPage(title = "TopAppBar", navController = navController)
    }
}
