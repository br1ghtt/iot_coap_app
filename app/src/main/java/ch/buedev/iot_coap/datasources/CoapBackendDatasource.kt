package ch.buedev.iot_coap.datasources

import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.model.CoapProtocol
import java.util.*

object CoapBackendDatasource {

    val coapBackends = mutableListOf<CoapBackend>(
        CoapBackend(UUID.randomUUID().toString(), "Name", CoapProtocol.COAP, "coap.me", 5683),
        CoapBackend(
            UUID.randomUUID().toString(),
            "Test",
            CoapProtocol.COAP_SECURE,
            "coaps.me",
            5342
        ),
        CoapBackend(UUID.randomUUID().toString(), "ABC", CoapProtocol.HTTP, "coaphttp.me", 2312),
        CoapBackend(
            UUID.randomUUID().toString(),
            "Noway",
            CoapProtocol.HTTP_SECURE,
            "coap.me",
            1611
        ),
        CoapBackend(
            UUID.randomUUID().toString(),
            "A Name",
            CoapProtocol.HTTP_SECURE,
            "coaphttps.me",
            161
        ),
        CoapBackend(
            UUID.randomUUID().toString(),
            "Coap Name",
            CoapProtocol.HTTP_SECURE,
            "coaphttps.me",
            1611
        ),
        CoapBackend(
            UUID.randomUUID().toString(),
            "Hello",
            CoapProtocol.HTTP_SECURE,
            "coaphttps.me",
            1611
        ),
        CoapBackend(
            UUID.randomUUID().toString(),
            "Nameff",
            CoapProtocol.HTTP_SECURE,
            "coaphttps.me",
            1611
        ),
        CoapBackend(
            UUID.randomUUID().toString(),
            "Nameahh",
            CoapProtocol.HTTP_SECURE,
            "coaphttpsdfsdafsdfsdfdasfsdffdfdsfsdaffffffffffffffffffffffffffffffffdfdsf.me",
            1611
        ),
        CoapBackend(UUID.randomUUID().toString(), "Name", CoapProtocol.COAP, "coap.me", 5683),
        CoapBackend(UUID.randomUUID().toString(), "Name", CoapProtocol.COAP, "coap.me", 5683),
        CoapBackend(UUID.randomUUID().toString(), "Name", CoapProtocol.COAP, "coap.me", 5683),
        CoapBackend(UUID.randomUUID().toString(), "Name", CoapProtocol.COAP, "coap.me", 5683),
        CoapBackend(UUID.randomUUID().toString(), "Name", CoapProtocol.COAP, "coap.me", 5683),
        CoapBackend(UUID.randomUUID().toString(), "Name", CoapProtocol.COAP, "coap.me", 5683)

    )

    fun loadCoapBackends(): List<CoapBackend> {
        return coapBackends
    }

    fun addCoapBackend(backend: CoapBackend) {
        coapBackends.add(backend)
    }

}