package ch.buedev.iot_coap.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import ch.buedev.iot_coap.model.CoapBackend
import org.eclipse.californium.core.CoapClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.coap.CoAP

private const val TIMEOUT = 2000L
private const val TAG = "CoapRequestVM"

class CoapRequestViewModel() : ViewModel() {

    fun onCoapRequest(requestCode: CoAP.Code, coapHandler: CoapHandler?) {
        when (requestCode) {
            CoAP.Code.GET -> get(coapHandler)
            else -> Log.d(TAG, "not a valid method $requestCode")
        }
    }

    fun onPingClick(): Boolean {
        return ping()
    }

    private fun ping(): Boolean {
        return buildCoapClient().ping(TIMEOUT)
    }

    fun onDiscoverClick() {
        discover()
    }

    private fun discover() {
        buildCoapClient().discover()
    }

    private fun get(coapHandler: CoapHandler?) {
        buildCoapClient().get(coapHandler)
    }

    private fun buildCoapClient(): CoapClient {
        val coapBackend = CoapBackend()
        val coapClient = CoapClient(
            coapBackend.protocol.value,
            coapBackend.hostname,
            coapBackend.port,
            "abc/cd",
            "abc"
        )
        Log.d(TAG, coapClient.uri)
        coapClient.timeout = TIMEOUT
        return coapClient
    }

}