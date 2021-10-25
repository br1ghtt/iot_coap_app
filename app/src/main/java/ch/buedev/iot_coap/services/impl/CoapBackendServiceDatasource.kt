package ch.buedev.iot_coap.services.impl

import ch.buedev.iot_coap.datasources.CoapBackendDatasource
import ch.buedev.iot_coap.model.CoapBackend
import ch.buedev.iot_coap.services.CoapBackendService

private const val TAG = "CoapBackendService"

class CoapBackendServiceDatasource: CoapBackendService {
    override fun save(coapBackend: CoapBackend, onSave: (coapBackend: CoapBackend) -> Unit) {
        CoapBackendDatasource.addCoapBackend(coapBackend)
        onSave(coapBackend)
    }

    override fun load(onLoad: (coapBackends: List<CoapBackend>) -> Unit) {
        onLoad(CoapBackendDatasource.loadCoapBackends())
    }

    override fun delete(coapBackend: CoapBackend, onDelete: (deleted: Boolean) -> Unit) {
        onDelete(true)
    }

}