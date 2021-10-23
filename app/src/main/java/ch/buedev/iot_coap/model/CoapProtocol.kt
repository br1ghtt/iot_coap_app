package ch.buedev.iot_coap.model

enum class CoapProtocol(val value: String) {
    COAP("coap"),
    COAP_SECURE("coaps"),
    HTTP("http"),
    HTTP_SECURE("https")
}