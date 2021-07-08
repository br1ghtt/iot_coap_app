import {Component} from '@angular/core';
import {NavController} from 'ionic-angular';
import {CoapEndpointPage} from "../coap-endpoint/coap-endpoint";
import {CoapEndpointProvider} from "../../providers/coap-endpoint/coap-endpoint";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {


  constructor(private coapEndpointProvider: CoapEndpointProvider, private navCtrl: NavController) {

  }

  onClickCreateEndpoint() {
    this.navCtrl.push(CoapEndpointPage);
  }

}
