package ch.buedev.iot_coap.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.model.CoapProtocol
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme

@ExperimentalMaterialApi
@Composable
fun CoapBackendForm(coapBackend: CoapBackend) {
    var name by rememberSaveable { mutableStateOf(coapBackend.name) }
    var hostname by rememberSaveable { mutableStateOf(coapBackend.hostname) }
    var port by rememberSaveable { mutableStateOf(coapBackend.port.toString()) }
    val protocols = listOf(CoapProtocol.COAP.value, coapBackend.protocol.value)
    var protocolsExpanded by remember { mutableStateOf(false) }
    var selectedProtocol by rememberSaveable { mutableStateOf(protocols[0]) }
    Surface {
        Column(Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    coapBackend.name = name
                },
                label = { Text(text = "Backend Name") },
                singleLine = true,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ExposedDropdownMenuBox(
                expanded = protocolsExpanded,
                onExpandedChange = {
                    protocolsExpanded = !protocolsExpanded
                }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedProtocol,
                    onValueChange = {
                        selectedProtocol = it
                        coapBackend.protocol = CoapProtocol.valueOf(selectedProtocol)

                    },
                    label = { Text("Protocol") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = protocolsExpanded
                        )
                    }
                )
                ExposedDropdownMenu(
                    expanded = protocolsExpanded,
                    onDismissRequest = {
                        protocolsExpanded = false
                    }
                ) {
                    protocols.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedProtocol = selectionOption
                                protocolsExpanded = false
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
                onValueChange = {
                    hostname = it
                    coapBackend.hostname = hostname
                },
                label = { Text(text = "Backend Hostname") },
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            OutlinedTextField(
                value = port,
                onValueChange = {
                    port = it
                    coapBackend.port = port.toInt()
                },
                label = { Text(text = "Backend Port") },
                singleLine = true
            )
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
    IoTCoAPTheme {
        CoapBackendForm(CoapBackend())
    }
}
