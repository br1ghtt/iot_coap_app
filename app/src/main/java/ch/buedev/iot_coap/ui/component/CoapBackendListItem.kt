package ch.buedev.iot_coap.ui.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ch.buedev.iot_coap.R
import ch.buedev.iot_coap.datasources.CoapBackendDatasource
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme

private const val TAG = "CoapBackendListItem"

@ExperimentalMaterialApi
@Composable
fun CoapBackendListItem(coapBackend: CoapBackend, onEditClick: (CoapBackend) -> Unit) {
    Surface {
        ListItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dns),
                    contentDescription = "Coap Backend List Item ${coapBackend.name}",
                    tint = MaterialTheme.colors.primaryVariant
                )
            },
            text = {
                Text(text = coapBackend.buildHost())
            },
            overlineText = {
                Text(
                    text = coapBackend.name,
                )
            },
            secondaryText = {
                Text(text = "TODO.172.31.20.20")
            },
            trailing = {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Edit Coap Backend",
                    modifier = Modifier.clickable {
                        onEditClick(coapBackend)
                    }
                )
            },
            modifier = Modifier.clickable { Log.d(TAG, "item clicked") }
        )
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
fun PreviewCoapBackendListItem() {
    val navController = rememberNavController()
    IoTCoAPTheme {
        CoapBackendListItem(
            coapBackend = CoapBackendDatasource.loadCoapBackends()[0],
            {}
        )
    }
}