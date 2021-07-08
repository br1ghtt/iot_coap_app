import {RequestOptions} from "./request-options";

export class CoapEndpoint {

  id: string;
  title: string;
  options: RequestOptions;
  favorite: boolean;
  ipAddress: string;

  constructor(public protocol: string, public host: string, public port: number) {
    this.id = "";
    this.options = {};
    this.title = "";
    this.favorite = false;
  }

  url(): string {
    return this.protocol + '://' + this.host + ':' + this.port;
  }

  static Instance(object: any): CoapEndpoint {
    let endpoint = new this(object.protocol, object.host, object.port);
    endpoint.id = object.id;
    if (object.options) {
      endpoint.options.path = object.options.path || "";
      endpoint.options.useCons = object.options.useCons || false;
      endpoint.options.query = object.options.query || "";
      endpoint.options.payload = object.options.payload || "";
      endpoint.options.accept = object.options.accept || "";
      endpoint.options.useAccept = object.options.useAccept || false;
      endpoint.favorite = object.favorite || false;
      endpoint.title = object.title || object.host;
      endpoint.ipAddress = object.ipAddress || "";
    }
    return endpoint;
  }
}
