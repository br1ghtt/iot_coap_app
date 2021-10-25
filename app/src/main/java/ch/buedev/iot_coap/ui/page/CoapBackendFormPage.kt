package ch.buedev.iot_coap.ui.page

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.services.impl.CoapBackendServiceDatasource
import ch.buedev.iot_coap.ui.component.CoapBackendForm
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme
import ch.buedev.iot_coap.ui.viewmodel.CoapBackendFormViewModel

private const val TAG = "CoapBackendFormPage"

@ExperimentalMaterialApi
@Composable
fun CoapBackendFormPage(
    viewModel: CoapBackendFormViewModel
) {
    val name by viewModel.name
    val hostname by viewModel.hostname
    val port by viewModel.port
    val protocol by viewModel.protocol
    val isNewBackend by viewModel.isNew

    IoTCoAPTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Create $name")
                    }
                )
            },
            content = {
                CoapBackendForm(
                    name, viewModel::onNameChange,
                    hostname, viewModel::onHostnameChange,
                    port, viewModel::onPortChange,
                    protocol, viewModel::onProtocolChange,
                    viewModel.getProtocols(),
                    viewModel::onDeleteCoapBackend,
                    isNewBackend

                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = viewModel::onSaveCoapBackend) {
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
    val coapBackendService = CoapBackendServiceDatasource()
    CoapBackendFormPage(
        CoapBackendFormViewModel(CoapBackend(), false, coapBackendService, navController, LocalContext.current)
    )
}
