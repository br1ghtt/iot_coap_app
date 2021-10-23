package ch.buedev.iot_coap.ui.pages

import android.content.res.Configuration
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.R
import ch.buedev.iot_coap.TAG
import ch.buedev.iot_coap.datasources.CoapBackendDatasource
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.ui.components.CoapBackendForm
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme

@ExperimentalMaterialApi
@Composable
fun CoapBackendDetailPage(coapBackend: CoapBackend, isNew: Boolean = false, navController: NavController) {
    val context = LocalContext.current
    val backend by rememberSaveable { mutableStateOf(coapBackend) }
    IoTCoAPTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Create Coap Backends")
                    }
                )
            },
            content = {
                CoapBackendForm(backend)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Log.d(TAG, "Add coap backend $backend $isNew")
                    CoapBackendDatasource.addCoapBackend(backend = backend)
                    navController.navigate(context.getString(R.string.route_coap_backend_list_page))
                }) {
                    Icon(Icons.Filled.Create, contentDescription = "Add Coap Backend")
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
fun PreviewCoapBackendCreatePage() {
    val navController = rememberNavController()
    CoapBackendDetailPage(coapBackend = CoapBackend(), navController = navController)
}
