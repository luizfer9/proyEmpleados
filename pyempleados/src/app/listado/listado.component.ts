import { EmpleadoService } from './../services/empleado.service';
import { DepartamentoService } from './../services/departamento.service';
import { AbcService } from './../services/abc.service';
import { IParametro } from './../models/parametros';
import { IDepartamento } from './../models/departamento';
import { IEmpleadoEn } from './../models/empleadoEntity';
import { IEmpleado } from './../models/empleado';
import { IRespuesta } from './../models/respuesta';
import { Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { BlockUI, NgBlockUI } from 'ng-block-ui';

import { ConfirmationService, Message, MessageService } from 'primeng/api';

import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-listado',
  templateUrl: './listado.component.html',
  styleUrls: ['./listado.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [ConfirmationService, MessageService,BsModalService,DatePipe]
})
export class ListadoComponent implements OnInit {
  @BlockUI('empleado-data')
  blockUI: NgBlockUI;
  @ViewChild('template') template: TemplateRef<any>;
  public modalRef: BsModalRef;
  empleados: IEmpleado[] = [];
  empleadosEn: IEmpleadoEn[] = [];
  departamentos: IDepartamento[]=[];
  selectedDepartamento: IDepartamento=null;
  msgs: Message[] = [];
  empleado_eliminar: IEmpleado = null;
  title:string='';
  //Formulario
  public empleadoForm: FormGroup;
  parametro: IParametro = null;
  num_opcion_guardar: number;
  respuesta: IRespuesta = null;
  constructor(private confirmationService: ConfirmationService, 
    private messageService: MessageService,
    private fb: FormBuilder,
    private modalService: BsModalService,
    private empleadoService: EmpleadoService,
    private departamentoService: DepartamentoService,
    private abcService: AbcService,
    private datePipe: DatePipe) {
    
   }

  async ngOnInit(): Promise<void> {
    this.cargarListaDepartamentos();
    await Promise.all([
      this.cargarListaEmpleados()
    ]);
    this.llenarDepartamentosEmpleados();
    this.crearFormulario();
  }

  public crearFormulario(){
    this.empleadoForm = this.fb.group({
      Clave_Emp: new FormControl(null),
      Nombre: new FormControl('', Validators.required),
      ApPaterno: new FormControl('', Validators.required),
      ApMaterno: new FormControl('', Validators.required),
      FecNac: new FormControl('', Validators.required),
      num_Departamento: new FormControl('', Validators.required),
      Sueldo: new FormControl('', Validators.required)
    });
  }

  public agregarEmpleado(){
    //variable asignable para guardar
    this.num_opcion_guardar=3;
    this.title = 'Nuevo(+)';
    if(this.empleadoForm!=null){
      this.resetForm();
    }
    this.openModal(this.template);
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  public guardarEmpleado(){
    if(this.num_opcion_guardar!=null){
      if(this.num_opcion_guardar==3){
        // metodo de llamar el service para agregar nuevo
        this.guardarInformacionNuevoEmpleado(this.num_opcion_guardar);
      } else if(this.num_opcion_guardar==5){
        //metodo de llamar el service editar
        this.editarEmpleado(this.num_opcion_guardar);
      }
    }
  }

  public guardarInformacionNuevoEmpleado(num_opc:number){
    let nuevoEmpleado: any = this.empleadoForm.value;
    let parametros: IParametro = {
        num_opcion: num_opc,
        Clave_Emp: -1,
        Nombre: nuevoEmpleado.Nombre,
        ApPaterno: nuevoEmpleado.ApPaterno,
        ApMaterno: nuevoEmpleado.ApMaterno,
        FecNac: this.datePipe.transform( nuevoEmpleado.FecNac, 'dd-MM-yyyy'),
        Departamento: nuevoEmpleado.num_Departamento.clv_puesto,
        Sueldo: nuevoEmpleado.Sueldo
    };
    //se manda llamar el servicio
    this.abcService.guardarNuevoEmpleado(parametros).subscribe((resp) => {
      this.respuesta = resp;
      if(this.respuesta.clv_respuesta==0){
      
        this.modalRef.hide();  
        setTimeout(() => {
          this.messageService.add({key: 'msj', 
                                severity: 'success', 
                                summary: 'Confirmado', 
                                detail: 'Informacion se almaceno correctamente' });
         },400);
        

      }
      //volver a cargar empleados actualizados.
      this.llenarEmpleadosSinEspera();

    },
    (error) => {
      this.messageService.add({key: 'msj', 
      severity: 'error', 
      summary: 'ERROR', 
      detail: 'Ocurrio un error, no se almaceno la informacion.'});
    },
    () => {
      //cerrar modal
      this.modalRef.hide();
    });
  }

  public editarEmpleado(num_opc:number){
    let nuevoEmpleado: any = this.empleadoForm.value;
    let parametros: IParametro = {
        num_opcion: num_opc,
        Clave_Emp: Number(nuevoEmpleado.Clave_Emp),
        Nombre: nuevoEmpleado.Nombre,
        ApPaterno: nuevoEmpleado.ApPaterno,
        ApMaterno: nuevoEmpleado.ApMaterno,
        FecNac: this.datePipe.transform( nuevoEmpleado.FecNac, 'dd-MM-yyyy'),
        Departamento: nuevoEmpleado.num_Departamento.clv_puesto,
        Sueldo: nuevoEmpleado.Sueldo
    };
    //se manda llamar el servicio
    this.abcService.editarEmpleado(parametros).subscribe((resp) => {
      this.respuesta = resp;
      if(this.respuesta.clv_respuesta==0){
      this.modalRef.hide();
        setTimeout(() => {
          this.messageService.add({key: 'msj', 
                                severity: 'success', 
                                summary: 'Confirmado', 
                                detail: 'Informacion se almaceno correctamente' });
         },400);

      }
      //volver a cargar empleados actualizados.
      this.llenarEmpleadosSinEspera();
    },
    (error) => {
      setTimeout(() => {
        this.messageService.add({key: 'msj', 
                              severity: 'error', 
                              summary: 'Error', 
                              detail: 'Informacion no se almaceno' });
       },400);
    },
    () => {
      //cerrar modal
      this.modalRef.hide();
    });
  }



  public cargarListaEmpleados():Promise<any>{
    /*this.empleados = [{
      Clave_Emp: '0100',
      Nombre:'Jassiel',
      ApPaterno:'Aguilar',
      ApMaterno:'Zazueta',
      FecNac: '29/09/1979',
      num_Departamento:1,
      Departamento: null,
      Sueldo: '40000',
    },
    {
      Clave_Emp: '0200',
      Nombre:'Edwin',
      ApPaterno:'Gandarilla',
      ApMaterno:'Aguilar',
      FecNac: '01/01/1992',
      num_Departamento:2,
      Departamento: null,
      Sueldo: '60000',
    }];*/
    return new Promise((resolve, reject) => {
      let parametros: IParametro = {
        num_opcion: 2,
        Clave_Emp: 1,
        Nombre:'a',
        ApPaterno: 'b',
        ApMaterno: 'c',
        FecNac: 'd',
        Departamento: -1,
        Sueldo: '0'
      };
      this.empleadoService.getEmpleados(parametros).subscribe((resp) => {
        this.empleados = resp;
        this.empleadosEn = resp;
        resolve();
      },
      (error) => {
        console.log(error);
        reject();
      },
      () => {
      });
    });//fin promesa
  }

  llenarEmpleadosSinEspera(){
    let parametros: IParametro = {
      num_opcion: 2,
      Clave_Emp: 1,
      Nombre:'a',
      ApPaterno: 'b',
      ApMaterno: 'c',
      FecNac: 'd',
      Departamento: -1,
      Sueldo: '0'
    };
    this.empleadoService.getEmpleados(parametros).subscribe((resp) => {
      this.empleados = resp;
      if(this.empleados!=null){
        //recorrer los empleados dados de alta
        this.empleados.map((empleado) => {
          if(empleado.Departamento!=null){ 
            empleado.num_Departamento= {
              desc_descripcion: this.obtenerDescripcion(empleado.Departamento),
              clv_puesto: empleado.Departamento
            };
          }
        });
      }
    },
    (error) => {
      console.log(error);
    },
    () => {
    });
  }

  public llenarDepartamentosEmpleados(){

    if(this.empleados!=null){
      //recorrer los empleados dados de alta
      this.empleados.map((empleado) => {
        if(empleado.Departamento!=null){ 
           /*this.cargarDepartamentoExistenteEmpleado(empleado.Departamento);*/
          empleado.num_Departamento= {
            desc_descripcion: this.obtenerDescripcion(empleado.Departamento),
            clv_puesto: empleado.Departamento
          };
        }
      });
    }
  }

  public obtenerDescripcion(num_puesto: number):string{
    let descripcion: string = '';
    this.departamentos.map((valor) => {
      if(valor.clv_puesto==num_puesto){
        descripcion= valor.desc_descripcion;
      }
    });
    return descripcion;
  }

  public cargarListaDepartamentos(){
    let parametros: IParametro = {
      num_opcion: 1,
      Clave_Emp: 1,
      Nombre:'a',
      ApPaterno: 'b',
      ApMaterno: 'c',
      FecNac: 'd',
      Departamento: -1,
      Sueldo: '0'
    };
    this.departamentoService.getDepartamentos(parametros).subscribe((resp) => {
      this.departamentos = resp;
    },
    (error) => {
      console.log(error);
    });
  }

  public cargarDepartamentoExistenteEmpleado(num_puesto:number):IDepartamento{
    let departamentoAsignar: IDepartamento = null;
    this.departamentos.map((departamento) => {
      if(departamento.clv_puesto==num_puesto){
        departamentoAsignar=departamento;
      } /*else {
        departamentoAsignar = {
          Puesto: num_puesto,
          Descripcion: 'No existe en Catalogo'
        };
      }*/
    });
    return departamentoAsignar;
  }

  public agregarNuevoEmpleado(){
    this.agregarEmpleado();
  }

  public eliminarInformacionEmpleado(empleado:IEmpleado){
    this.empleado_eliminar=empleado;
    let mensaje='¿Desea eliminar el Empleado?';
    this.messageService.clear();
    this.messageService.add({key: 'c', 
                             sticky: true, 
                             severity:'warn', 
                             summary:mensaje, 
                             detail:''});
  }

  public editarInformacionEmpleado(empleado_editar:IEmpleado){
    this.num_opcion_guardar = 5;
    this.inicializarFormulario(empleado_editar);
    this.openModal(this.template);
  }

  public inicializarFormulario(empleado_editar:IEmpleado){
    this.title = empleado_editar.Clave_Emp ? 'Editar' : 'Nuevo(+)';
    this.empleadoForm.setValue({
      Clave_Emp: empleado_editar.Clave_Emp,
      Nombre: empleado_editar.Nombre ,
      ApPaterno: empleado_editar.ApPaterno,
      ApMaterno: empleado_editar.ApMaterno,
      /*FecNac: empleado_editar.FecNac,*/
      FecNac:new Date(empleado_editar.FecNac),
      num_Departamento: empleado_editar.num_Departamento,
      Sueldo: empleado_editar.Sueldo
    });
  }

  
  public Rechazar(){
    this.messageService.clear('c');
  }

  public Aceptar(){
    if(this.empleado_eliminar!=null){
      this.messageService.clear('c');
      this.mandarEliminarEmpleado(4,Number(this.empleado_eliminar.Clave_Emp));
    }
  }

  public mandarEliminarEmpleado(num_opc:number,clv_empleado_eliminar:number){
    let parametros: IParametro = {
      num_opcion: num_opc,
      Clave_Emp: clv_empleado_eliminar,
      Nombre:'a',
      ApPaterno: 'b',
      ApMaterno: 'c',
      FecNac: 'd',
      Departamento: -1,
      Sueldo: '0'
    };

    this.abcService.eliminarEmpleado(parametros).subscribe((resp) => {
      this.respuesta = resp;
      if(this.respuesta.clv_respuesta==0){
        setTimeout(() => {
          this.messageService.add({key: 'msj', 
                                severity: 'success', 
                                summary: 'Confirmado', 
                                detail: 'Informacion se elimino correctamente' });
         },400);

      }
      //volver a cargar empleados actualizados.
      this.llenarEmpleadosSinEspera();
    },
    (error) => {
      setTimeout(() => {
        this.messageService.add({key: 'msj', 
                              severity: 'error', 
                              summary: 'Error', 
                              detail: 'Informacion no se almaceno' });
       },400);
    },
    () => {
    });

  }

  public resetForm() {
    this.empleadoForm.reset();
  }

  public regresar() {
    //this.router.navigate(['/administrador/comunicacion']);
    this.modalRef.hide();
  }

}
