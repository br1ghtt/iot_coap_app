package ch.buedev.iot_coap.ui.page

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ch.buedev.iot_coap.ui.viewmodel.CoapRequestViewModel

@Composable
fun CoapRequestPage(modelView: CoapRequestViewModel) {
    Surface {
        Column() {
            Button(onClick = modelView::onPingClick ) {
                Text(text = "Ping")
            }
        }
        Column() {
            Button(onClick = modelView::onDiscoverClick ) {
                Text(text = "Discover")
            }
        }
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
fun PreviewCoapRequestPage() {
    CoapRequestPage(
        CoapRequestViewModel()
    )
}
