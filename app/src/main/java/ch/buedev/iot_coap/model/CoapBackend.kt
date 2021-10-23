package ch.buedev.iot_coap.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoapBackend(
    var name: String = "Coap Backend Name",
    var protocol: CoapProtocol = CoapProtocol.COAP,
    var hostname: String = "coap.me",
    var port: Int = 5683
) : Parcelable {

    fun buildHost(): String {
        return protocol.value + "://" + hostname + ":" + port
    }

    fun setProtocolByValue(value: String) {
        protocol = CoapProtocol.fromSymbol(value)!!
    }

}
