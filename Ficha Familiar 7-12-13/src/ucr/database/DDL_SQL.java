package ucr.database;

import java.util.ArrayList;

//Clase contenedora de las definiciones
//en lenguaje SQL de la base de datos
public class DDL_SQL {
	private String tabla;
	private String trigger;
	private ArrayList<String> lista_tablas;
		
	//Constructor
	public DDL_SQL() {	
		inicializar();
	}
	
	//Ubica en memoria las definiciones de las tablas que utiliza
	//la Ficha Familiar en memoria, para el caso en que sea necesario
	//crear toda la base de datos
	public void inicializar() {
		tabla = "";
		trigger = "";
		lista_tablas = new ArrayList<String>();
		lista_tablas = inicializarLista();
	}
	
	//Crea un array con la lista de las tablas que contiene la base de datos
	public ArrayList<String> inicializarLista() {
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("Configuracion");
		lista.add("Provincia");
		lista.add("Canton");
		lista.add("Distrito");
		lista.add("Barrio");
		lista.add("RegionSalud");
		lista.add("AreaSalud");
		lista.add("EBAIS");		
		lista.add("Ubicacion");
		lista.add("Ocupacion");
		lista.add("CondSocioeconomica");
		lista.add("CondSalud");
		lista.add("CondSaludM");
		lista.add("Denuncia");
		lista.add("Vacunas");
		lista.add("ControlVacunas");
		lista.add("Escolaridad");
		lista.add("TipoAsegurado");		
		//lista.add("EstadoCivil");
		lista.add("Nacion");
		lista.add("Vivienda");
		lista.add("Persona");		
		return lista;
	}
	
	//Permite acceder a la lista de tablas
	public ArrayList<String> getListaTablas() {
		return lista_tablas;
	}
	
	public String getSQLConfiguracion() {
		tabla = "CREATE TABLE Configuracion (" + 
				" Version DEC(1,1) PRIMARY KEY," +
				" CodProvincia INT NOT NULL REFERENCES Provincia(CodProvincia)," +
				" CodCanton INT NOT NULL REFERENCES Canton(CodCanton)," +
				" CodRegion INT NOT NULL REFERENCES RegionSalud(CodRegion)," +
				" CodAS INT NOT NULL REFERENCES AreaSalud(CodAS)," +
				" FechaAct DATE NOT NULL," +
				" Email VARCHAR(40) NOT NULL," +
				" UltCodViv INT NOT NULL)";
						
		return tabla;
	}
	
	public String getSQLPadron() {
		tabla = "CREATE TABLE IF NOT EXISTS Padron" + 
				" (Cedula VARCHAR(9) PRIMARY KEY," + 
				" CodElec VARCHAR(6)," + 
				" Sexo VARCHAR(1)," + 
				" FechaCaduc DATE," + 
				" Nombre VARCHAR(30)," + 
				" Ape1 VARCHAR(26)," + 
				" Ape2 VARCHAR(26) NOT NULL)";
		return tabla;
	}
	
	//Retorna el lenguaje de definicion SQL para la tabla Ubicacion
	public String getSQLUbicacion() {
		tabla = "CREATE TABLE IF NOT EXISTS Ubicacion" +
				" (CodViv INTEGER PRIMARY KEY AUTOINCREMENT," + 
				" FechaVis VARCHAR(10) NOT NULL," + 
				" CodRS INT NOT NULL DEFAULT 0," +
				" CodAS INT NOT NULL DEFAULT 0," +
				" CodEBAIS INT NOT NULL DEFAULT 0," + 
				" CodProvincia INT NOT NULL DEFAULT 0," + 
				" CodCanton INT NOT NULL DEFAULT 0," + 
				" CodDistrito INT NOT NULL DEFAULT 0," +  
				" CodBarrio INT NOT NULL DEFAULT 0," + 
				" Latitud DEC(3,3) CHECK (Latitud > -1) NOT NULL DEFAULT 0," +
				" Longitud DEC(3,3) CHECK (Longitud > -1) NOT NULL DEFAULT 0," +
				" VisEfec VARCHAR(1) NOT NULL DEFAULT \"N\" )";
		return tabla;
	}
	
	//Retorna la definicion de la tabla Vivienda
	//Requerira variar la llave primaria?
	public String getSQLVivienda() {
		tabla = "CREATE TABLE IF NOT EXISTS Vivienda" +
				" (CodViv INTEGER PRIMARY KEY," + 
				" FechaVisita DATE," + 
				" M2 DEC(4, 2) CHECK (M2 > -1) DEFAULT 0," +   		
				" CodTenencia VARCHAR," + 
				" CodEnergia VARCHAR," + 
				" NApos SMALLINT CHECK (NApos > -1) DEFAULT 0," +  
				" NDorm SMALLINT CHECK (NDorm > -1) DEFAULT 0," + 		
				" TipoCocina VARCHAR," + 
				" CondCocina VARCHAR," +
				" EnergCocina VARCHAR," + 		
				" TipoBanio VARCHAR," + 
				" CondBanio VARCHAR," + 
				" TipoPiso VARCHAR," + 
				" CondPiso VARCHAR," + 
				" TipoPared VARCHAR," + 
				" CondPared VARCHAR," + 
				" TipoTecho VARCHAR," + 
				" CondTecho VARCHAR," + 
				" CondVent VARCHAR," + 
				" CondIlum VARCHAR," + 
				" FtesAgua VARCHAR," +
				" CondFtesAgua VARCHAR," + //Requiere ser eliminado
				" DispExcretas VARCHAR," +
				" DispBasura VARCHAR," +
				" AnimCondIns VARCHAR," +
				" Riesgo VARCHAR," +
				" Observaciones VARCHAR(20))";		
		return tabla;
	}
	
	public String getSQLPersona() {
		tabla = "CREATE TABLE IF NOT EXISTS Persona" +
				" (Consec INTEGER PRIMARY KEY AUTOINCREMENT," +
				" Cedula VARCHAR(9)," +
				" Nombre VARCHAR(30) NOT NULL," + 
				" Ape1 VARCHAR(30) NOT NULL," + 
				" Ape2 VARCHAR(30)," +	
				" FechaNac DATE NOT NULL," +
				" FechaDef DATE," +
				" Peso DEC(3,2) DEFAULT 0," +
				" Sexo VARCHAR(2) NOT NULL," +
				" RolFamilia VARCHAR(2) NOT NULL," +
				" CodEstadoCivil VARCHAR(2) NOT NULL," +
				" CodAsegurado VARCHAR(3) NOT NULL REFERENCES TipoAsegurado(CodAsegurado)," +
				" CodEscolaridad INT NOT NULL REFERENCES Escolaridad(CodEscolaridad)," +
				" CodEstadoNutri VARCHAR(1) NOT NULL," +
				" CodOcup INT NOT NULL REFERENCES Ocupacion(CodOcup)," +
				" CodCondLaboral INT," +
				" CodNacion INT NOT NULL REFERENCES Nacion(CodNacion)," +
				" CodRaza VARCHAR(2)," +
				" JefeFamilia INT NOT NULL," +
				" CodVivienda INT NOT NULL REFERENCES Vivienda(CodVivienda)" +
				" ON DELETE CASCADE ON UPDATE CASCADE)";	
		return tabla;
	}
	
	public String getSQLCondSocioeconomica() {
		tabla = "CREATE TABLE IF NOT EXISTS CondSoc" +
				" (CodViv INTEGER PRIMARY KEY," + 
				" CodCondSocioeco VARCHAR(2)," +
				" Refrigeracion VARCHAR(1)," +
				" Radio VARCHAR(1)," +
				" Telefono VARCHAR(1)," +
				" TV VARCHAR(1)," +
				" Lavadora VARCHAR(1)," +
				" Computadora VARCHAR(1)," +
				" Internet VARCHAR(1))"; 
		return tabla;
	}
	
	public String getSQLCondSalud() {
		tabla = "CREATE TABLE IF NOT EXISTS CondSalud" +
				" (Consec INTEGER PRIMARY KEY REFERENCES Persona(Consec)" +
				" ON DELETE CASCADE ON UPDATE CASCADE," +
				" Especialista VARCHAR(25) NOT NULL," +
				" SaludActual INT NOT NULL," + 
				" TipoViolencia VARCHAR(2) NOT NULL)";		
		return tabla;
	}
	
	public String getSQLCondSaludM() {
		tabla = "CREATE TABLE IF NOT EXISTS CondSaludM" +
				" (Consec INTEGER PRIMARY KEY REFERENCES ConSalud(Consec)" +
				" ON DELETE CASCADE ON UPDATE CASCADE," +
				" FechaPlan DATE NOT NULL," +
				" TipoPlan VARCHAR(3) NOT NULL," + 
				" FechaPap DATE NOT NULL," +
				" FechaEmb DATE NOT NULL," +
				" Observaciones VARCHAR(30))";		
		return tabla;		
	}
	
	public String getSQLDenuncia() {
		tabla = "CREATE TABLE IF NOT EXISTS Denuncia" +
				" (CodDenuncia INTEGER PRIMARY KEY," +
				" Descripcion VARCHAR(75) NOT NULL," +
				" FechaDenuncia DATE NOT NULL," +
				" NombreDenunciante VARCHAR(20) NOT NULL," +
				" TelDenunciante VARCHAR(8) NOT NULL," +
				" ResponsableDen INTEGER NOT NULL," +
				" Longitud DEC(3,3) CHECK (Longitud > -1) NOT NULL DEFAULT 0," +
				" Latitud DEC(3,3) CHECK (Latitud > -1) NOT NULL DEFAULT 0)";
		return tabla;
	}
	
	public String getSQLMapa() {
		return tabla;
	}
	
	public String getSQLProvincia() {
		tabla = "CREATE TABLE IF NOT EXISTS Provincia " +
				" (CodProvincia INT PRIMARY KEY," + 
				" Nombre_Provincia VARCHAR(30) NOT NULL)";
		return tabla;
	}
	
	public String getSQLCanton() {
		tabla = "CREATE TABLE IF NOT EXISTS Canton" +     			   
				" (CodProvincia INT NOT NULL REFERENCES Provincia(CodProvincia) ON DELETE RESTRICT," +
				" CodCanton INT NOT NULL," +
				" Nombre_Canton VARCHAR(30) NOT NULL," +
				" PRIMARY KEY(CodProvincia, CodCanton))";
		return tabla;
	}	
	
	public String getSQLDistrito() {
		tabla = "CREATE TABLE IF NOT EXISTS Distrito" +
				" (CodProvincia INTEGER NOT NULL," +
				" CodCanton INTEGER NOT NULL," +
				" CodDistrito INTEGER NOT NULL," +
				" Nombre_Distrito VARCHAR(30) NOT NULL," +
				" PRIMARY KEY(CodProvincia, CodCanton, CodDistrito)," +
				" FOREIGN KEY(CodProvincia, CodCanton) REFERENCES Canton(CodProvincia, CodCanton))";
		return tabla;
	}		
	
	public String getSQLBarrio() {
		tabla = "CREATE TABLE IF NOT EXISTS Barrio" + 
				" (CodProvincia INT NOT NULL REFERENCES Provincia(CodProvincia) ON DELETE RESTRICT," +
				" CodCanton INTEGER NOT NULL REFERENCES Canton(CodCanton) ON DELETE RESTRICT," +
				" CodDistrito INT NOT NULL REFERENCES Distrito(CodDistrito) ON DELETE RESTRICT," +
				" CodBarrio INT NOT NULL," +
				" Nombre_Barrio VARCHAR(50)," +
				" PRIMARY KEY(CodProvincia, CodCanton, CodDistrito, CodBarrio)," +
				" FOREIGN KEY(CodProvincia, CodCanton, CodDistrito)" +
				" REFERENCES Distrito(CodProvincia, CodCanton, CodDistrito))";
		return tabla;
	}	
	
	public String getSQLRegionSalud() {
		tabla = "CREATE TABLE IF NOT EXISTS RegionSalud "  +
				" (CodRegion INT PRIMARY KEY," +
				" NombreRegion VARCHAR(16) NOT NULL)";			
		return tabla;
	}		
	
	
	public String getSQLAreaSalud() {
		tabla = "CREATE TABLE IF NOT EXISTS AreaSalud "  +
				" (CodAS INT PRIMARY KEY," +
				" CodRegion INT NOT NULL REFERENCES RegionSalud(CodRegion)," +
				" Descripcion VARCHAR(50) NOT NULL)";			
		return tabla;
	}	
	
	public String getSQLEBAIS() {
		tabla = "CREATE TABLE IF NOT EXISTS EBAIS "  +
				" (CodEBAIS INT PRIMARY KEY," +
				" CodAS INT NOT NULL REFERENCES AreaSalud(CodAS)," + 
				" Descripcion VARCHAR(50) NOT NULL)";
		return tabla;
	}	
	
	public String getSQLVacunas() {
		tabla = "CREATE TABLE IF NOT EXISTS Vacuna " +
				" (IdVacuna INTEGER PRIMARY KEY, " +
				" Nombre_Vacuna VARCHAR(40) NOT NULL)"; 
		return tabla;
	}	
	
	public String getSQLControlVacunas() {
		tabla = "CREATE TABLE IF NOT EXISTS ControlVacunas" +
				" (Consec INTEGER NOT NULL REFERENCES Persona(Consec)" +
				" ON DELETE CASCADE ON UPDATE CASCADE," +
				" IdVacuna INT NOT NULL REFERENCES Vacuna(IdVacuna)," +
				" FechaVac DATE NOT NULL," +
				" PRIMARY KEY(Consec, IdVacuna))";	
		return tabla;
	}	
	
	public String getSQLNacion() {
		tabla = "CREATE TABLE IF NOT EXISTS Nacion" + 
				" (CodNacion VARCHAR(3) PRIMARY KEY," + 
				" Nombre_Nacion VARCHAR(30) NOT NULL)";
		return tabla;
	}	
	
	public String getSQLEscolaridad() {
		tabla = " CREATE TABLE IF NOT EXISTS Escolaridad" + 
				" (CodEscolaridad INT PRIMARY KEY," + 
				" Nivel_Esc VARCHAR(30) NOT NULL)";
		return tabla;
	}
	
	public String getTipoAsegurado() {
		tabla = " CREATE TABLE TipoAsegurado(" +
				" CodAsegurado INT PRIMARY KEY," +
				" Descripcion VARCHAR(50) NOT NULL)";
		return tabla;
	}
	
	public String getSQLOcupacion() {
		tabla = "CREATE TABLE IF NOT EXISTS Ocupacion" + 
				" (CodOcup VARCHAR(3) PRIMARY KEY, " +
				" Desc_Ocup VARCHAR(50) NOT NULL)";
		return tabla;
	}
	
	public String trigger_Insertar_Ubicacion() {
		trigger = "";
		return trigger;
	}
	
	public String trigger_Insertar_Persona() {
		trigger = "";
		return trigger;
	}	
	
	public String trigger_Insertar_Vivienda() {
		trigger = "";
		return trigger;
	}
	
	public String trigger_Insertar_CondSalud() {
		trigger = "";
		return trigger;
	}
	
	public String trigger_Insertar_CondSocioeconomica() {
		trigger = "";
		return trigger;
	}
	
	public String trigger_Insertar_EsqVacunacion() {
		trigger = "";
		return trigger;
	}
	
	public String trigger_Borrar_Persona() {
		trigger = "CREATE TRIGGER IF NOT EXISTS trigger_Borrar_Persona" +
				  " BEFORE DELETE ON Vivienda " +
				  " FOR EACH ROW BEGIN " +
				  " DELETE from persona WHERE codVivienda = OLD.codViv; " +
				  " END";
		return trigger;
	}	
}