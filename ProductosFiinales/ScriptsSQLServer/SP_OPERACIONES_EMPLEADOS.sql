CREATE PROCEDURE SP_OPERACIONES_EMPLEADOS(@num_opcion INT,@Clave_Emp INT,  
@Nombre VARCHAR(50),@ApPaterno VARCHAR(50),@ApMaterno VARCHAR(50),  
@FecNac VARCHAR(50),@Departamento INT,@Sueldo VARCHAR(50))  
AS  
/*  
Descripcion: Procedimiento para realizar las operaciones Alta,Baja,Actualizacion y Consulta----Proc base a praticionar - logica general.  
*/  
BEGIN  
 DECLARE @nuevo_empleado INT
 DECLARE @clv_respuesta INT = 0
 SET @Nombre = LTRIM(RTRIM(@Nombre))
 SET @ApPaterno = LTRIM(RTRIM(@ApPaterno))
 SET @ApMaterno = LTRIM(RTRIM(@ApMaterno))
 SET @nuevo_empleado=((SELECT MAX(Clave_Emp) FROM Empleados)+1)  
 IF(@num_opcion=1)  
 BEGIN  
  --Mostrar Departamentos existentes  
  SELECT Puesto,  
      Descripcion   
  FROM Departamentos  
 END  
  IF(@num_opcion=2)  
  BEGIN  
   SELECT  Clave_Emp,  
     Nombre,  
     ApPaterno,  
     ApMaterno,  
     FecNac,  
     Departamento,  
     Sueldo  
   FROM Empleados  
  END  
	IF(@num_opcion=3)  
	BEGIN  
	--dar de alta un empleado
		IF(@Clave_Emp = -1)
		BEGIN
			IF NOT EXISTS(SELECT Clave_Emp FROM Empleados WHERE LTRIM(RTRIM(Nombre)) like @Nombre AND LTRIM(RTRIM(ApPaterno)) like @ApPaterno AND LTRIM(RTRIM(ApMaterno)) like @ApMaterno)
			BEGIN	
				SET @clv_respuesta = 1
				INSERT INTO Empleados(Clave_Emp,Nombre,ApPaterno,ApMaterno,FecNac,Departamento,Sueldo)  
				SELECT @nuevo_empleado,@Nombre,@ApPaterno,@ApMaterno,CONVERT(DATETIME,@FecNac,103),@Departamento,@Sueldo  
			END
			ELSE
			BEGIN
				SET @clv_respuesta = 0
			END
		END
		ELSE
		BEGIN
			SET @clv_respuesta = 0
		END

		Select @clv_respuesta
	END  

    IF(@num_opcion=4)  
    BEGIN  
		--eliminar empleado
		IF EXISTS(SELECT Clave_Emp FROM Empleados WHERE Clave_Emp=@Clave_Emp)
		BEGIN
			
			DELETE FROM Empleados WHERE Clave_Emp=@Clave_Emp 
			SET @Clv_respuesta = 1

			IF EXISTS(SELECT Clave_Emp FROM Empleados WHERE Clave_Emp=@Clave_Emp)
			BEGIN
				SET @Clv_respuesta = 0
			END
			ELSE 
			BEGIN
				SET @Clv_respuesta = 1
			END
			
		END
		ELSE
		BEGIN
			SET @Clv_respuesta = 0
		END
		
		SELECT @clv_respuesta
    END  

	IF(@num_opcion=5)  
	BEGIN  
	--actualizar empleado
		IF EXISTS(SELECT Clave_Emp FROM Empleados WHERE Clave_Emp = @Clave_Emp)
		BEGIN
				UPDATE Empleados  
				SET Nombre=@Nombre,  
					ApPaterno=@ApPaterno,  
					ApMaterno=@ApMaterno,  
					FecNac=CONVERT(DATETIME,@FecNac,103),  
					Departamento=@Departamento,  
					Sueldo=@Sueldo  
				WHERE Clave_Emp=@Clave_Emp
				
				SET @Clv_respuesta = 1
		END
		ELSE
		BEGIN

			SET @Clv_respuesta = 0

		END
			
		SELECT @clv_respuesta
	END  
END