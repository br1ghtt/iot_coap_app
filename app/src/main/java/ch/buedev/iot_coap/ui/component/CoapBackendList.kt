package ch.buedev.iot_coap.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.datasources.CoapBackendDatasource
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme

@ExperimentalMaterialApi
@Composable
fun CoapBackendList(coapBackends: List<CoapBackend>, navController: NavController) {

    LazyColumn {
        items(coapBackends) { coapBackend ->
            CoapBackendListItem(
                coapBackend = coapBackend,
                navController = navController
            )
            Divider()
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode Theme"
)
@Composable
fun PreviewCoapBackendList() {
    val navController = rememberNavController()
    IoTCoAPTheme {
        CoapBackendList(
            coapBackends = CoapBackendDatasource.loadCoapBackends(),
            navController = navController
        )
    }
}
