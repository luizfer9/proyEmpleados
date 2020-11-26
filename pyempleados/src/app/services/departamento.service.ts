import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from './../../environments/environment.prod';
import { IParametro } from './../models/parametros';

import { HttpClient, HttpParams,HttpErrorResponse } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class DepartamentoService {
  private apiUrl: string;
  constructor(public http: HttpClient) { 
    this.apiUrl = environment.SUrlgApi + '/webresources/directorio';
  }

  public getDepartamentos(parametros:IParametro):Observable<any>{
    let num_opcion = parametros.num_opcion;
    const valores = {
      params: new HttpParams().
        set('Clave_Emp',parametros.Clave_Emp.toString()).
        set('Nombre',parametros.Nombre.toString()).
        set('ApPaterno',parametros.ApPaterno.toString()).
        set('ApMaterno',parametros.ApMaterno.toString()).
        set('FecNac',parametros.FecNac.toString()).
        set('Departamento',parametros.Departamento.toString()).
        set('Sueldo',parametros.Sueldo.toString())   
    };
    return  this.http.get(this.apiUrl+'/'+num_opcion, valores);
   }
}
