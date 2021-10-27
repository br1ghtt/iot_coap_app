package ch.buedev.iot_coap.ui.page

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.services.impl.CoapBackendServiceDatasource
import ch.buedev.iot_coap.ui.component.CoapBackendList
import ch.buedev.iot_coap.ui.component.WelcomeMessage
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme
import ch.buedev.iot_coap.ui.viewmodel.CoapBackendListViewModel

@ExperimentalMaterialApi
@Composable
fun CoapBackendListPage(viewModel: CoapBackendListViewModel) {
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
                if (!viewModel.hasBackends()) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        WelcomeMessage()
                    }
                } else {
                    CoapBackendList(coapBackends = viewModel.coapBackends, viewModel::onEditClick)
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.onEditClick(CoapBackend())
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
        viewModel = CoapBackendListViewModel(
            CoapBackendServiceDatasource(),
            navController,
            LocalContext.current
        )
    )
}
