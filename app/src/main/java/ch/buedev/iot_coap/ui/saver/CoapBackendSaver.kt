package ch.buedev.iot_coap.ui.saver

import androidx.compose.runtime.saveable.mapSaver
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.model.CoapProtocol

val CoapBackendSaver = run {
    val nameKey = "Name"
    val protocolKey = "Protocol"
    val hostnameKey = "Hostname"
    val portKey = "Port"
    mapSaver(
        save = {
            mapOf(
                nameKey to it.name,
                protocolKey to it.protocol,
                hostnameKey to it.hostname,
                portKey to it.port
            )
        },
        restore = {
            CoapBackend(
                it[nameKey] as String,
                it[protocolKey] as CoapProtocol,
                it[hostnameKey] as String,
                it[portKey] as Int
            )
        }
    )
}