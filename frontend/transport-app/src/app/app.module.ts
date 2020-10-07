import { DeleteConfirmComponent } from './deleteConfirm/delete-confirm.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TransportInfoComponent } from './transport/transport-info.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http' ;
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    TransportInfoComponent,
    DeleteConfirmComponent
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
     { path: 'transports/:id' , component: TransportInfoComponent
},
{
  path: 'deleteTransport/:id' ,component: DeleteConfirmComponent 
}
])
    
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
