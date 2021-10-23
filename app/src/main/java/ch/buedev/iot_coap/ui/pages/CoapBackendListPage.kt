package ch.buedev.iot_coap.ui.pages

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.R
import ch.buedev.iot_coap.TAG
import ch.buedev.iot_coap.datasources.CoapBackendDatasource
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.ui.components.CoapBackendList
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme
import com.google.gson.Gson


@ExperimentalMaterialApi
@Composable
fun CoapBackendListPage(coapBackends: List<CoapBackend>, navController: NavController) {
    val context = LocalContext.current
    IoTCoAPTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Your Coap Backends")
                    }
                )
            },
            content = {
                CoapBackendList(coapBackends = coapBackends, navController = navController)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Log.d(TAG, "navigate to coap backend create clicked")
                    navController.navigate(
                        route = context.getString(R.string.route_coap_backend_detail_page)
                                + "/" +
                                Gson().toJson(CoapBackend())
                                + "?isNew=true"
                    )
                }) {
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
    CoapBackendListPage(
        coapBackends = CoapBackendDatasource.loadCoapBackends(),
        navController = navController
    )
}
