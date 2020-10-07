import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TransportInfoComponent } from './transport/transport-info.component';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    TransportInfoComponent
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
