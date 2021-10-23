package ch.buedev.iot_coap.model

import com.google.gson.annotations.SerializedName

enum class CoapProtocol(@SerializedName("value") val value: String) {
    @SerializedName("0")
    COAP("coap"),
    @SerializedName("1")
    COAP_SECURE("coaps"),
    @SerializedName("2")
    HTTP("http"),
    @SerializedName("3")
    HTTP_SECURE("https")
}