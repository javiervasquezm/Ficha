package ucr.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Esta clase se usa para definir la estructura de la base de datos
//Ficha Familiar
public class AdminBD extends SQLiteOpenHelper {
	
	private DDL_SQL sql;

	public AdminBD(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		sql = new DDL_SQL();
	}
	
	public AdminBD(Context context, String databaseName, Object object, int databaseVersion) {
		super(context, databaseName, (CursorFactory) object, databaseVersion);
		sql = new DDL_SQL();
	}

	@Override
	//Este metodo se ejecuta cuando la base de datos es creada
	//o se indica expresamente que hay una modificacion
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(sql.getSQLConfiguracion());
			db.execSQL(sql.getSQLProvincia());
			db.execSQL(sql.getSQLCanton());
			db.execSQL(sql.getSQLDistrito());
			db.execSQL(sql.getSQLBarrio());
			db.execSQL(sql.getSQLUbicacion());
			db.execSQL(sql.getSQLRegionSalud());
			db.execSQL(sql.getSQLAreaSalud());
			db.execSQL(sql.getSQLEBAIS());
			db.execSQL(sql.getSQLCondSocioeconomica());
			db.execSQL(sql.getSQLDenuncia());
			//db.execSQL(sql.getSQLEstadoCivil());
			db.execSQL(sql.getSQLNacion());
			db.execSQL(sql.getTipoAsegurado());
			db.execSQL(sql.getSQLOcupacion());
			db.execSQL(sql.getSQLVacunas());
			db.execSQL(sql.getSQLVivienda());
			db.execSQL(sql.getSQLPersona());
			db.execSQL(sql.getSQLCondSalud());
			db.execSQL(sql.getSQLCondSaludM());
			db.execSQL(sql.getSQLControlVacunas());
		} catch (SQLiteException SQLEx) {
			Log.v("Create table exception", SQLEx.getMessage());			
		}		
	}

	@Override
	//Para cambios en el script de la base de datos
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		ArrayList<String> lista_tablas = new ArrayList<String>(sql.getListaTablas()); 
		
		for (int i = 0; i < lista_tablas.size(); ++i) {
			db.execSQL("DROP TABLE IF EXISTS " + lista_tablas.get(i));
			//Eliminacion de triggers requiere ser añadida.
		}
		onCreate(db);
	}
}