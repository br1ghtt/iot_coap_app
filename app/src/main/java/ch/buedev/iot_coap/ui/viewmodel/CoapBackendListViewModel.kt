package ch.buedev.iot_coap.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import ch.buedev.iot_coap.R
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.services.CoapBackendService
import com.google.gson.Gson

private const val TAG = "CoapBackendListVM"

class CoapBackendListViewModel(
    private val coapBackendService: CoapBackendService,
    private val navController: NavController,
    private val context: Context
) : ViewModel() {

    var coapBackends = listOf<CoapBackend>()

    init {
        coapBackendService.load {
            coapBackends = it
        }
    }

    fun onEditClick(coapBackend: CoapBackend) {
        Log.d(TAG, "edit clicked")
        navController.navigate(
            context.getString(R.string.route_coap_backend_detail_page) + "/" + Gson().toJson(
                coapBackend
            )
        )
    }

    fun hasBackends(): Boolean {
        return coapBackends.isNotEmpty()
    }

}