import {NgModule} from '@angular/core';
import {IonicPageModule} from 'ionic-angular';
import {CoapEndpointPage} from './coap-endpoint';

@NgModule({
  declarations: [
    CoapEndpointPage,
  ],
  imports: [
    IonicPageModule.forChild(CoapEndpointPage),
  ],
})
export class CoapEndpointPageModule {}
