import { IDepartamento } from './departamento';
export interface IEmpleado {
    Clave_Emp: string;
    Nombre:string;
    ApPaterno:string;
    ApMaterno:string;
    FecNac: string;
    Departamento: number;
    num_Departamento?: IDepartamento;
    Sueldo: string;
}
