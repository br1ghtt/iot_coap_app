package ch.buedev.iot_coap.ui.nav

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavType
import ch.buedev.iot_coap.model.CoapBackend
import com.google.gson.Gson

class CoapBackendNavType : NavType<CoapBackend>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): CoapBackend? {
       return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CoapBackend {
        Log.d("ABC", value)
        return Gson().fromJson(value, CoapBackend::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CoapBackend) {
        bundle.putParcelable(key, value)
    }
}