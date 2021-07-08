import {Component} from '@angular/core';
import {AlertController, IonicPage, NavController, ToastController} from 'ionic-angular';
import {CoapEndpoint} from "../../app/models/coap-endpoint";
import {CoapEndpointProvider} from "../../providers/coap-endpoint/coap-endpoint";
import {CoapEndpointPage} from "../coap-endpoint/coap-endpoint";
import {CoapActionPage} from "../coap-action/coap-action";
import {HomePage} from "../home/home";
import {DNS} from "@ionic-native/dns";
import {CoapProvider} from "../../providers/coap/coap";
import {AdProvider} from "../../providers/ad/ad";

/**
 * Generated class for the CoapEndpointListPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage({
  priority: 'high',
})
@Component({
  selector: 'page-coap-endpoint-list',
  templateUrl: 'coap-endpoint-list.html',
})
export class CoapEndpointListPage {

  endpoints: Array<CoapEndpoint> = [];

  constructor(private adProvider: AdProvider,
              private coapProvider: CoapProvider,
              private dns: DNS,
              private coapEndpointProvider: CoapEndpointProvider,
              private alertCtrl: AlertController,
              private toastCtrl: ToastController,
              public navCtrl: NavController) {
  }

  ionViewDidLoad() {
  }

  ionViewCanEnter() {
    this.loadEndpoints();
  }

  loadEndpoints(): void {
    console.log('load enpoints');
    this.coapEndpointProvider.getEndpoints()
      .then((endpoints: CoapEndpoint[]) => {
        if (endpoints.length > 0) {
          this.endpoints = endpoints.sort(this.endpointComparator);
        } else {
          this.navCtrl.setRoot(HomePage);
        }
        this.endpoints.forEach((endpoint: CoapEndpoint) => {
          this.resolveHostName(endpoint)
        });
      });
  }

  refreshEndpoints(event: any):void {
    this.coapEndpointProvider.getEndpoints()
      .then((endpoints: CoapEndpoint[]) => {
        this.endpoints = endpoints.sort(this.endpointComparator);
        this.endpoints.forEach((endpoint: CoapEndpoint) => {
          this.resolveHostName(endpoint)
          event.complete();
        });
      });
  }

  resolveHostName(endpoint: CoapEndpoint): void {
    this.dns.resolve(endpoint.host)
      .then(address => endpoint.ipAddress = address)
      .catch(error => endpoint.ipAddress = "Not resolved");
  }

  toggleFavorite(endpoint: CoapEndpoint) {
    endpoint.favorite = !endpoint.favorite;
    this.coapEndpointProvider.saveEndpoint(endpoint)
      .then((endpoint: CoapEndpoint) =>  {
        this.endpoints = this.endpoints.sort(this.endpointComparator)
        this.presentToast("Endpoint marked as favorite");
    });
  }


  onClickCreateEndpoint(): void {
    this.navCtrl.push(CoapEndpointPage);
  }

  onClickAction(endpoint: CoapEndpoint): void {
    this.adProvider.showIntersital();
    this.navCtrl.push(CoapActionPage, {endpoint: JSON.stringify(endpoint)});
  }

  onEditClick(endpoint: CoapEndpoint): void {
    this.navCtrl.push(CoapEndpointPage, {endpoint: JSON.stringify(endpoint)});
  }

  presentToast(message: string): void {
    this.toastCtrl.create({
      message: message,
      duration: 2000,
      showCloseButton: true,
      cssClass: 'toastClose',
    }).present();
  }

  showEndpointDeletePromt(endpoint: CoapEndpoint) {
    this.alertCtrl.create({
      title: 'Delete CoAP Endpoint',
      message: 'Do you want to delete the endpoint?\n' + endpoint.url(),
      buttons: [
        {
          text: 'Cancel',
          cssClass: 'alertCancel',
          handler: data => {
            console.log(data);
          }
        },
        {
          text: 'Delete',
          cssClass: 'alertDanger',
          handler: data => {
            this.coapEndpointProvider.deleteEndpoint(endpoint)
              .then(res => {
                this.loadEndpoints();
                this.presentToast("Endpoint was deleted successfully");
              }).catch(err => console.log(err));
          }
        }
      ]
    }).present();
  }

  private endpointComparator(a: CoapEndpoint, b: CoapEndpoint): number {
    if (a.favorite && b.favorite) {
      return a.title.localeCompare(b.title);
    } else if(a.favorite || b.favorite) {
      if (a.favorite) return 0;
      if (b.favorite) return 1;
    }
    return a.title.localeCompare(b.title);
  }
}
