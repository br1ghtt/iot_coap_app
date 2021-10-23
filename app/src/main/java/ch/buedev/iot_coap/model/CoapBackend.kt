package ch.buedev.iot_coap.model


data class CoapBackend(
    var name: String = "Coap Backend Name",
    var protocol: CoapProtocol = CoapProtocol.COAP,
    var hostname: String = "coap-me",
    var port: Int = 5683
) {

    fun buildHost(): String {
        return protocol.value + "://" + hostname + ":" + port
    }

}
