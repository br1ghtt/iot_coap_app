import {Component} from '@angular/core';
import {AlertController, IonicPage, NavController, NavParams, ToastController} from 'ionic-angular';
import {CoapEndpointProvider} from "../../providers/coap-endpoint/coap-endpoint";
import {CoapEndpoint} from "../../app/models/coap-endpoint";
import {CoapEndpointListPage} from "../coap-endpoint-list/coap-endpoint-list";
import {AdProvider} from "../../providers/ad/ad";

/**
 * Generated class for the CoapEndpointPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-coap-endpoint',
  templateUrl: 'coap-endpoint.html',
})
export class CoapEndpointPage {

  isUpdate: boolean = false;

  endpoint: CoapEndpoint;

  constructor(private coapEndpointProvider: CoapEndpointProvider,
              private toastCtrl: ToastController,
              private alertCtrl: AlertController,
              private navCtrl: NavController,
              private  navParams: NavParams,
              private adProvicer: AdProvider) {
    let endpointParam = this.navParams.get('endpoint');
    if (endpointParam) {
      this.endpoint = CoapEndpoint.Instance(JSON.parse(endpointParam));
      this.isUpdate = true;
    } else {
      this.endpoint = new CoapEndpoint('coap', 'coap.me', 5683);
    }
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CoapEndpointPage');
  }

  onClickSaveEndpoint() {
    if(this.endpoint.title === "") {
      this.endpoint.title = this.endpoint.host;
    }
    this.coapEndpointProvider.saveEndpoint(this.endpoint)
      .then(endpoint => {
        if (!this.isUpdate) this.presentToast("Endpoint was saved successfully");
        if (this.isUpdate) this.presentToast("Endpoint was updated successfully");
        this.navCtrl.setRoot(CoapEndpointListPage);
      })
      .catch(error => this.showError(error));
    this.adProvicer.showIntersital();
  }

  presentToast(message: string): void {
    this.toastCtrl.create({
      message: message,
      duration: 2000,
      showCloseButton: true,
    }).present();
  }

  showError(error: string): void {
    this.alertCtrl.create({
      title: "Uups..",
      subTitle: error
    }).present();
  }

}
