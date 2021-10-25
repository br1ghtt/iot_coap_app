package ch.buedev.iot_coap.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import ch.buedev.iot_coap.R
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.model.CoapProtocol
import ch.buedev.iot_coap.services.CoapBackendService

private const val TAG = "CoapBackendFormVM"

class CoapBackendFormViewModel(
    val coapBackend: CoapBackend,
    private val isNew: Boolean,
    private val coapBackendService: CoapBackendService,
    private val navController: NavController,
    private val context: Context
) : ViewModel() {

    private val _name = mutableStateOf(coapBackend.name)
    val name: MutableState<String>
        get() = _name

    private val _hostname = mutableStateOf(coapBackend.hostname)
    val hostname: MutableState<String>
        get() = _hostname

    private val _port = mutableStateOf(coapBackend.port.toString())
    val port: MutableState<String>
        get() = _port


    private val _protocol = mutableStateOf(coapBackend.protocol.value)
    val protocol: MutableState<String>
        get() = _protocol

    fun onNameChange(newName: String) {
        _name.value = newName
        coapBackend.name = _name.value
    }

    fun onHostnameChange(newHostname: String) {
        _hostname.value = newHostname
        coapBackend.hostname = _hostname.value
    }

    fun onPortChange(newPort: String) {
        _port.value = newPort
        coapBackend.port = _port.value.toInt()
    }

    fun onProtocolChange(newProtocol: String) {
        Log.d(TAG, newProtocol)
        _protocol.value = newProtocol
        coapBackend.protocol = CoapProtocol.fromSymbol(_protocol.value)!!
    }

    fun getProtocols(): List<String> {
        return CoapProtocol.asValueList()
    }

    fun onDeleteCoapBackend() {
        if (!isNew) {
            Log.d(TAG, "delete backend $coapBackend $isNew")
            coapBackendService.delete(coapBackend) {
                if (it) {
                    navController.navigate(
                        context.getString(
                            R.string.route_coap_backend_list_page
                        )
                    )
                }
            }
        }
    }

    fun onSaveCoapBackend()  {
        Log.d(TAG, "Add coap backend $coapBackend")
        coapBackendService.save(coapBackend) {
            navController.navigate(context.getString(R.string.route_coap_backend_list_page))
        }
    }
}