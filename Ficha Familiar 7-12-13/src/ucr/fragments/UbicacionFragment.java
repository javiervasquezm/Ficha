package ucr.fragments;

import ucr.ff.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ucr.database.SQLiteAdapter;
import ucr.tools.Response;
import ucr.tools.Validation;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

@SuppressWarnings("unused")
public class UbicacionFragment extends Fragment implements Response{

	private View view;
	private TextView txtTituloTab;
	private TextView txtCodViv;
	private TextView txtFechaVisita;
	private TextView txtRegionSalud;
	private TextView txtAreaSalud;	
	private TextView txtEBAIS;	
	private TextView txtProvincia;
	private TextView txtCanton;
	private TextView txtDistrito;
	private TextView txtDireccion;
	private TextView txtLatitud;
	private TextView txtLongitud;

	private Spinner spinnerDist;
	private Spinner spinnerBarr;
	private Spinner spinnerEBAIS;

	private EditText editCodViv;
	private EditText editFechaVis;
	private EditText editRegionSalud;
	private EditText editAreaSalud;
	private EditText editProv;
	private EditText editCant;
	private EditText editLat;
	private EditText editLong;

	private Button searchButton;
	private Button insertButton;
	private Button updateButton;

	private SQLiteAdapter adapter;

	protected ArrayList<Integer> codDistritos;
	protected ArrayList<Integer> codBarrios;
	protected ArrayList<Integer> codEBAIS;

	private static int regionSalud;
	private static int areaSalud;
	private static int prov;
	private static int cant;
	private static int dist;
	private static int barr;
	private static int arsa;
	private static int ebais;

	private Validation validation;
	private boolean preloaded_data;
	private ContentValues values;

	public UbicacionFragment() {
		super();
		validation = new Validation();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Iniciando...", "UbicacionFragment");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.ubicacion_tab, null);
		adapter = new SQLiteAdapter(view.getContext());
		codDistritos = new ArrayList<Integer>();
		codBarrios = new ArrayList<Integer>();
		codEBAIS = new ArrayList<Integer>();
		initialize();
		return view;
	}

	private void initialize() {
		prov = 0;
		cant = 0;
		dist = 0;
		barr = 0;
		regionSalud = 0;
		areaSalud = 0;
		ebais = 0;
		getConfigurationData();
		createWidgets();
		widgetsFunctions();	
	}

	public void createTextViews() {
		txtTituloTab = (TextView) view.findViewById(R.id.txtTituloTab);
		txtCodViv = (TextView) view.findViewById(R.id.txtCodViv);
		txtProvincia = (TextView) view.findViewById(R.id.txtProvincia);
		txtCanton = (TextView) view.findViewById(R.id.txtCanton);
		txtDistrito = (TextView) view.findViewById(R.id.txtDistrito);
		txtDireccion = (TextView) view.findViewById(R.id.txtDireccion);
		txtAreaSalud = (TextView) view.findViewById(R.id.txtAreaSalud);
		txtEBAIS = (TextView) view.findViewById(R.id.txtEBAIS);
		txtLatitud = (TextView) view.findViewById(R.id.txtLatitud);
		txtLongitud = (TextView) view.findViewById(R.id.txtLongitud);	
	}

	private void createSpinners() {
		String sqlQuery = "";				
		adapter.open();
		spinnerDist = (Spinner) view.findViewById(R.id.spinnerDist);
		sqlQuery = "SELECT CodDistrito, Nombre_Distrito FROM Distrito WHERE CodProvincia = " + prov +
				" AND CodCanton = " + cant;		
		List<ArrayList<String>> valores = new ArrayList<ArrayList<String>>();		
		valores = adapter.cursorToList(sqlQuery); 
		ArrayAdapter<String> lista_Dist = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item);
		for (int i = 0; i < valores.size(); ++i) {
			lista_Dist.add(valores.get(i).get(1));
			codDistritos.add(Integer.valueOf(valores.get(i).get(0)));
		}				
		spinnerDist.setAdapter(lista_Dist);  
		spinnerDist.setSelection(0, true);

		spinnerBarr = (Spinner) view.findViewById(R.id.spinnerBarr);		

		spinnerEBAIS = (Spinner) view.findViewById(R.id.spinnerEBAIS);	
		sqlQuery = "SELECT CodEBAIS, Descripcion FROM EBAIS WHERE CodAS = " + areaSalud;
		valores = adapter.cursorToList(sqlQuery); 
		ArrayAdapter<String> lista_EBAIS = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item);								
		for (int i = 0; i < valores.size(); ++i) {
			lista_EBAIS.add(valores.get(i).get(1));
			codEBAIS.add(Integer.valueOf(valores.get(i).get(0)));
		}
		spinnerEBAIS.setAdapter(lista_EBAIS);  
		spinnerEBAIS.setSelection(0, true);

		adapter.close();
	}

	public void spinnerFunctions() {

		spinnerDist.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				dist = codDistritos.get(spinnerDist.getSelectedItemPosition());	
				adapter.open();
				String sqlQuery = "SELECT CodBarrio, Nombre_Barrio FROM Barrio WHERE CodProvincia = " + prov +
						" AND CodCanton = " + cant + " AND CodDistrito = " + dist;					
				spinnerBarr.invalidate();
				codBarrios.clear();
				List<ArrayList<String>> valores = new ArrayList<ArrayList<String>>();						
				valores = adapter.cursorToList(sqlQuery); 			
				ArrayAdapter<String> lista_Barr = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item);
				for (int i = 0; i < valores.size(); ++i) {
					lista_Barr.add(valores.get(i).get(1));
					codBarrios.add(Integer.valueOf(valores.get(i).get(0)));
				}				

				spinnerBarr.setAdapter(lista_Barr);  
				spinnerBarr.setSelection(0, true);
				adapter.close();
				spinnerDist.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		spinnerBarr.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				barr = codBarrios.get(spinnerBarr.getSelectedItemPosition());
				spinnerBarr.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});		

		spinnerEBAIS.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				ebais =  Integer.valueOf(codEBAIS.get(spinnerEBAIS.getSelectedItemPosition()));
				spinnerEBAIS.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});							
	}

	private void createEditTexts() {
		editCodViv = (EditText) view.findViewById(R.id.editCodViv);
		editFechaVis = (EditText) view.findViewById(R.id.editFechaVis);
		editRegionSalud = (EditText) view.findViewById(R.id.editRegionSalud);
		editAreaSalud = (EditText) view.findViewById(R.id.editAreaSalud);
		editProv = (EditText) view.findViewById(R.id.editProv);
		editCant = (EditText) view.findViewById(R.id.editCant);
		editLat =  (EditText) view.findViewById(R.id.editLat);
		editLong =  (EditText) view.findViewById(R.id.editLong);
	}

	private void getConfigurationData() {
		adapter.open();
		String sqlQuery = "SELECT CodProvincia, CodCanton, CodRegion, CodAS FROM Configuracion WHERE Version =  1";
		List<ArrayList<String>> valores = new ArrayList<ArrayList<String>>();						
		valores = adapter.cursorToList(sqlQuery); 		
		prov = Integer.valueOf(valores.get(0).get(0));
		cant = Integer.valueOf(valores.get(0).get(1));
		regionSalud = Integer.valueOf(valores.get(0).get(2));
		areaSalud = Integer.valueOf(valores.get(0).get(3));		
		adapter.close();
	}

	private String getData(String sqlQuery) {
		adapter.open();
		List<ArrayList<String>> valores = new ArrayList<ArrayList<String>>();	
		valores = adapter.cursorToList(sqlQuery); 
		adapter.close();
		return valores.get(0).get(0);
	}

	private void editTextFunctions() {
		editFechaVis.setText(validation.getDate());

		adapter.open();
		String sqlQuery = "SELECT Nombre_Provincia FROM Provincia WHERE CodProvincia = " + prov;
		editProv.setText(getData(sqlQuery));
		editProv.setEnabled(false);

		sqlQuery = "SELECT Nombre_Canton FROM Canton WHERE CodProvincia = " + prov +
				" AND CodCanton = " + cant;
		editCant.setText(getData(sqlQuery));
		editCant.setEnabled(false);

		sqlQuery = "SELECT NombreRegion FROM RegionSalud WHERE CodRegion = " + regionSalud;
		editRegionSalud.setText(getData(sqlQuery));
		editRegionSalud.setEnabled(false);

		sqlQuery = "SELECT Descripcion FROM AreaSalud WHERE CodAS = " + areaSalud;
		editAreaSalud.setText(getData(sqlQuery));
		editAreaSalud.setEnabled(false);	
		
		editLat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					editLat.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (editLat.getText().toString().isEmpty()) {
						editLat.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						editLat.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		editLong.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					editLong.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (editLong.getText().toString().isEmpty()) {
						editLong.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						editLong.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});		
	}

	private void createButtons() {
		searchButton =  (Button) view.findViewById(R.id.searchButton);
		insertButton =  (Button) view.findViewById(R.id.insertButton);
		updateButton =  (Button) view.findViewById(R.id.updateButton);
	}

	private void buttonFunctions() {
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Datos de ubicacion hallados", Toast.LENGTH_SHORT).show();		
			}			
		});

		insertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Validation validation = new Validation();
				if (prov != 0 && cant != 0 && dist != 0 && barr != 0 
						&& !validation.isEmpty(editLat.getText().toString()) && !validation.isEmpty(editLong.getText().toString())) {
					Toast.makeText(view.getContext(),  prov + " " + cant + " " + dist + " " + barr, Toast.LENGTH_SHORT).show();
					double latitud = Double.valueOf(editLat.getText().toString());
					double longitud = Double.valueOf(editLong.getText().toString());

				} else {
					Toast.makeText(view.getContext(),  "Datos de ubicacion insertados", Toast.LENGTH_SHORT).show();
				}
			}			
		});

		updateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Datos de ubicacion actualizados", Toast.LENGTH_SHORT).show();
			}			
		});

	}

	private void createWidgets() {
		createTextViews();
		createSpinners();
		createEditTexts();
		createButtons();
	}

	private void widgetsFunctions() {
		spinnerFunctions();
		editTextFunctions();
		buttonFunctions();
	}

	private void setValues() {
		values = new ContentValues();
		values.put("codViv", getCodViv());
		values.put("fechaVis", getFecha());
		values.put("codRS", Integer.parseInt(editRegionSalud.getText().toString()));
		values.put("codAS", Integer.parseInt(editAreaSalud.getText().toString()));
		//spinnerEBAIS
		values.put("codProvincia", Integer.parseInt(editProv.getText().toString()));
		values.put("codCanton", Integer.parseInt(editCant.getText().toString()));
		//spinnerDist
		//spinnerBarr
		values.put("latitud", Double.valueOf(editLat.getText().toString()));
		values.put("longitud", Double.valueOf(editLong.getText().toString()));
		//visitaEfectiva
	}

	public void store() {
		

	}

	public void load() {

	}

	@Override
	public int getCodViv() {
		int res = 0;
		try {
			res = Integer.parseInt(editCodViv.getText().toString());
		} catch (Exception e) {
			res = -1;
		}
		return res;
	}

	@Override
	public String getFecha() {
		return editFechaVis.getText().toString();
	}
}