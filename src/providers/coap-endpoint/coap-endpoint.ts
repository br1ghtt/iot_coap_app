import {Injectable} from '@angular/core';
import {CoapEndpoint} from "../../app/models/coap-endpoint";
import {Storage} from "@ionic/storage";
import uuid from "uuid";

/*
  Generated class for the CoapEndpointProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class CoapEndpointProvider {

  private endpointKey = 'endpointkey-';

  constructor(private storage: Storage) {
    console.log('Hello CoapEndpointProvider Provider');
  }

  saveEndpoint(endpoint: CoapEndpoint): Promise<CoapEndpoint> {
    return new Promise(((resolve, reject) => {
      if (endpoint.id === "")  {
        endpoint.id = this.newEndpointKey();
      }
      this.storage.set(endpoint.id, JSON.stringify(endpoint))
        .then(() => resolve(endpoint));
    }));
  }

  getEndpoints(): Promise<Array<CoapEndpoint>> {
    return new Promise((resolve, reject) => {
      let coapEndpoints: Array<CoapEndpoint> = [];
      this.storage.forEach((value, key) => {
        if(this.isEndpointKey(key)) {
          coapEndpoints.push(CoapEndpoint.Instance(JSON.parse(value)));
        } else {
          console.log('not and endpoint key', key);
        }
      }).then(() => {
        console.log(JSON.stringify(coapEndpoints));
        resolve(coapEndpoints);
      });
    })
  }

  deleteEndpoint(endpoint: CoapEndpoint): Promise<any> {
    return this.storage.remove(endpoint.id);
  }

  private newEndpointKey(): string {
    return this.endpointKey + uuid();
  }

  private isEndpointKey(key): boolean {
    return key.startsWith(this.endpointKey);
  }
}
