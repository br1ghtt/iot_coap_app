package ch.buedev.iot_coap.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.services.impl.CoapBackendServiceDatasource
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme
import ch.buedev.iot_coap.ui.viewmodel.CoapBackendFormViewModel

private const val TAG = "CoapBackendForm"


@ExperimentalMaterialApi
@Composable
fun CoapBackendForm(
    name: String, onNameChange: (String) -> Unit,
    hostname: String, onHostNameChange: (String) -> Unit,
    port: String, onPortChange: (String) -> Unit,
    protocol: String, onProtocolChange: (String) -> Unit,
    protocols: List<String>,
    onDeleteCoapBackend: () -> Unit,
    isNew: Boolean
) {
    var expanded by remember { mutableStateOf(false) }
    Surface {
        Column(Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text(text = "Backend Name") },
                singleLine = true,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = protocol,
                    onValueChange = { },
                    label = { Text("Protocol") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    protocols.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                onProtocolChange(selectionOption)
                                expanded = false
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            OutlinedTextField(
                value = hostname,
                onValueChange = onHostNameChange,
                label = { Text(text = "Hostname") },
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            OutlinedTextField(
                value = port,
                onValueChange = onPortChange,
                label = { Text(text = "Port") },
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            if (!isNew) {
                OutlinedButton(
                    onClick = onDeleteCoapBackend,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.onError)
                ) {
                    Text(text = "Delete")
                }
            }
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
fun PreviewCoapBackendForm() {
    val viewModel = CoapBackendFormViewModel(
        CoapBackend(), CoapBackendServiceDatasource(), rememberNavController(), LocalContext.current)
    IoTCoAPTheme {
        CoapBackendForm(
            "The Name",
            viewModel::onNameChange,
            "Hostname",
            viewModel::onHostnameChange,
            "9999",
            viewModel::onPortChange,
            "coap",
            viewModel::onProtocolChange,
            viewModel.getProtocols(),
            viewModel::onDeleteCoapBackend,
            false
        )
    }
}
