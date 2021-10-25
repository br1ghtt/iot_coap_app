package ch.buedev.iot_coap.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import ch.buedev.iot_coap.ui.theme.IoTCoAPTheme

@Composable
fun WelcomeMessage() {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = "IoT CoAP",
                    style = MaterialTheme.typography.h4,
                )
            }
            Row {
                Text(
                    text = "Create your first CoAP backend",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}


@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode Theme"
)
@Composable
fun PreviewWelcomeMessage() {
    IoTCoAPTheme {
        WelcomeMessage()
    }
}