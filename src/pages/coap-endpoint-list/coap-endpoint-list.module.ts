import {NgModule} from '@angular/core';
import {IonicPageModule} from 'ionic-angular';
import {CoapEndpointListPage} from './coap-endpoint-list';

@NgModule({
  declarations: [
    CoapEndpointListPage,
  ],
  imports: [
    IonicPageModule.forChild(CoapEndpointListPage),
  ],
})
export class CoapEndpointListPageModule {}
