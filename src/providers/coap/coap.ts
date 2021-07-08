import {Injectable} from '@angular/core';
import {CoapEndpoint} from "../../app/models/coap-endpoint";
import {CoapClient, CoapResponse, RequestOptions} from "@ionic-native/coap-client";


@Injectable()
export class CoapProvider {

  constructor(private coap: CoapClient) {
  }

  get(endpoint: CoapEndpoint): Promise<CoapResponse> {
    return this.coap.get(this.endpointDefaultOptions(endpoint));
  }

  post(endpoint: CoapEndpoint): Promise<CoapResponse> {
    return this.coap.post(this.endpointDefaultOptions(endpoint));
  }

  put(endpoint: CoapEndpoint): Promise<CoapResponse> {
    return this.coap.put(this.endpointDefaultOptions(endpoint));
  }

  delete(endpoint: CoapEndpoint): Promise<CoapResponse> {
    return this.coap.delete(this.endpointDefaultOptions(endpoint));
  }

  discover(endpoint: CoapEndpoint): Promise<string> {
    return this.coap.discover(this.endpointDefaultOptions(endpoint));
  }

  ping(endpoint: CoapEndpoint): Promise<string> {
    return this.coap.ping(this.endpointDefaultOptions(endpoint));
  }

  private endpointDefaultOptions(endpoint: CoapEndpoint): RequestOptions {
    let options: RequestOptions = {
      protocol: endpoint.protocol,
      host: endpoint.host,
      port: endpoint.port,
      path: endpoint.options.path,
      payload: endpoint.options.payload,
      query: endpoint.options.query,
      accept: endpoint.options.accept,
      useCons: endpoint.options.useCons,
      requestTimeout: 10000
    };
    return options
  }

}
