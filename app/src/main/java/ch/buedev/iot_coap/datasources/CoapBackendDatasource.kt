package ch.buedev.iot_coap.datasources

import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.model.CoapProtocol

object CoapBackendDatasource {

    fun loadCoapServerData(): List<CoapBackend> {
        return listOf<CoapBackend>(
            CoapBackend("Name", CoapProtocol.COAP, "coap.me", 5683),
            CoapBackend("Name2", CoapProtocol.COAP_SECURE, "coaps.me", 5342),
            CoapBackend("Name3", CoapProtocol.HTTP, "coaphttp.me", 2312),
            CoapBackend("Name4", CoapProtocol.HTTP_SECURE, "coap.me", 1611),
            CoapBackend("Namefdsfasd", CoapProtocol.HTTP_SECURE, "coaphttps.me", 161),
            CoapBackend("Nameafdadsf", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Nameasdf", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Nameff", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend(
                "Nameahh",
                CoapProtocol.HTTP_SECURE,
                "coaphttpsdfsdafsdfsdfdasfsdffdfdsfsdaffffffffffffffffffffffffffffffffdfdsf.me",
                1611
            ),
            CoapBackend("Namerzz", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name dfgjhtj", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611),
            CoapBackend("Name fasf", CoapProtocol.HTTP_SECURE, "coaphttps.me", 1611)
        )
    }

}