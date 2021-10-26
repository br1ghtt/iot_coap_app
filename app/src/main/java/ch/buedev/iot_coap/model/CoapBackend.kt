package ch.buedev.iot_coap.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class for the CoAP Backend model.
 * @author Cédric Bühler
 */
@Parcelize
data class CoapBackend(
    var id: String = "",
    var name: String = "Coap Backend Name",
    var protocol: CoapProtocol = CoapProtocol.COAP,
    var hostname: String = "coap.me",
    var port: Int = 5683
) : Parcelable {

    fun hasId(): Boolean {
        return id.isNotEmpty()
    }

    /**
     * Builds the CoAP hostname protocoll://hostname:port.
     * @return The full hostname
     */
    fun buildHost(): String {
        return protocol.value + "://" + hostname + ":" + port
    }

}
