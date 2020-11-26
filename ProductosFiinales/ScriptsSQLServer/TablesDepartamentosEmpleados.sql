CREATE TABLE [dbo].[Departamentos](
Puesto INT PRIMARY KEY IDENTITY NOT NULL,
Descripcion VARCHAR(50) NOT NULL DEFAULT ''
) ON [PRIMARY]
GO
CREATE TABLE [dbo].[Empleados](
Clave_Emp INT PRIMARY KEY NOT NULL,
Nombre VARCHAR(50) NOT NULL DEFAULT '',
ApPaterno VARCHAR(50) NOT NULL DEFAULT '',
ApMaterno VARCHAR(50) NOT NULL DEFAULT '',
FecNac Datetime NOT NULL DEFAULT '1900-01-01',
Departamento INT NOT NULL DEFAULT 0,
Sueldo MONEY NOT NULL DEFAULT 0,
FOREIGN KEY (Departamento) REFERENCES Departamentos(Puesto)
) ON [PRIMARY]

---
INSERT INTO Departamentos(Descripcion)
SELECT 'Sistemas'
UNION ALL
SELECT 'Comercial'

---
GO

INSERT INTO Empleados(Clave_Emp,Nombre,ApPaterno,ApMaterno,FecNac,Departamento,Sueldo)
SELECT 1,'Jassiel','Aguilar','Zazueta',CONVERT(DATETIME,'29/09/1979',103),1,40000
UNION ALL
SELECT 2,'Edwin','Gandarilla','Aguilar',CONVERT(DATETIME,'01/01/1992',103),2,60000
