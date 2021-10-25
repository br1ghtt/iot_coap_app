package ch.buedev.iot_coap.services.impl

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import ch.buedev.iot_coap.R
import ch.buedev.iot_coap.datasources.CoapBackendDatasource
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.services.CoapBackendService
import com.google.gson.Gson


private const val TAG = "CoapBackendService"

class CoapBackendServiceSharedPref(private val context: Context) : CoapBackendService {

    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.shared_preferences_coap_backends), Context.MODE_PRIVATE
    )

    init {
        with(sharedPref.edit()) {
            clear()
            apply()
        }
    }

    override fun save(coapBackend: CoapBackend, onSave: (coapBackend: CoapBackend) -> Unit) {
        Log.d(TAG, "save coap backend $coapBackend")
        with(sharedPref.edit()) {
            putString(
                buildKey(coapBackend), Gson().toJson(coapBackend)
            )
            apply()
        }
        onSave(coapBackend)
    }

    override fun load(onLoad: (coapBackends: List<CoapBackend>) -> Unit) {
        val coapBackends = mutableListOf<CoapBackend>()
        @Suppress("UNCHECKED_CAST")
        val coapBackendsJson: List<String> = sharedPref.all.values.toList() as List<String>
        coapBackendsJson.forEach {
            coapBackends.add(Gson().fromJson(it, CoapBackend::class.java))
        }
        onLoad(coapBackends)
    }

    override fun delete(coapBackend: CoapBackend, onDelete: (deleted: Boolean) -> Unit) {
        if (sharedPref.contains(buildKey(coapBackend))) {
            Log.d(TAG, "Shared pref contains ${buildKey(coapBackend)}")
            with(sharedPref.edit()) {
                remove(buildKey(coapBackend))
                apply()
                onDelete(true)
            }
        } else {
            onDelete(false)
        }
    }

    private fun buildKey(coapBackend: CoapBackend): String {
       return  context.getString(
           R.string.shared_preferences_coap_backends_key,
           coapBackend.hashCode()
       )
    }

}
