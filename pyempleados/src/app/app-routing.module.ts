import { ListadoComponent } from './listado/listado.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { InicioComponent } from './inicio/inicio.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    component: InicioComponent
  },
  {
    path: 'not-found',
    component: NotfoundComponent
  },
  {
    path: 'listado',
    component: ListadoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{ useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
