import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListadoComponent } from './listado/listado.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { InicioComponent } from './inicio/inicio.component';
import { CapturaComponent } from './captura/captura.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {TableModule} from 'primeng/table';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessagesModule } from 'primeng/messages';
import {ConfirmationService} from 'primeng/api';
import {DropdownModule} from 'primeng/dropdown';

import { ModalModule } from 'ngx-bootstrap/modal';
import { BlockUIModule } from 'ng-block-ui';

import { ToastModule } from 'primeng/toast';

import { HttpClientModule  } from '@angular/common/http';
import {CalendarModule} from 'primeng/calendar';
@NgModule({
  declarations: [
    AppComponent,
    ListadoComponent,
    NavbarComponent,
    NotfoundComponent,
    InicioComponent,
    CapturaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    ToggleButtonModule,
    ConfirmDialogModule,
    MessagesModule,
    ToastModule,
    DropdownModule,
    BlockUIModule,
    ModalModule.forRoot(),
    HttpClientModule,
    CalendarModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
