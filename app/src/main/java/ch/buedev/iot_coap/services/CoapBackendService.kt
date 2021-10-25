package ch.buedev.iot_coap.services

import ch.buedev.iot_coap.model.CoapBackend

/**
 * A CoAP backend crud service.
 * @author Cédric Bühler
 */
interface CoapBackendService {
    fun save(coapBackend: CoapBackend, onSave: (coapBackend: CoapBackend) -> Unit)
    fun load(onLoad: (coapBackends: List<CoapBackend>) -> Unit)
    fun delete(coapBackend: CoapBackend, onDelete: (deleted: Boolean) -> Unit)
}