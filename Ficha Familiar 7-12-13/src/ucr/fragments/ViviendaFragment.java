package ucr.fragments;

import ucr.ff.R;
import java.util.ArrayList;
import java.util.List;

import ucr.database.SQLiteAdapter;
import ucr.tools.Validation;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

@SuppressWarnings("unused")
public class ViviendaFragment extends Fragment {
	
	private View view;

	private TextView txtCodViv;
	private TextView txtM2;
	private TextView txtTenencia;
	private TextView txtEnerg;
	private TextView txtNumApos;
	private TextView txtNumDor;
	private TextView txtTipoCoc;
	private TextView txtCondCoc;
	private TextView txtEnergCoc;
	private TextView txtTipoBan;
	private TextView txtCondBan;
	private TextView txtTipoPiso;	
	private TextView txtCondPiso;
	private TextView txtTipoPared;
	private TextView txtCondPared;
	private TextView txtTipoTecho;
	private TextView txtCondTecho;
	private TextView txtVentilac;
	private TextView txtIluminac;
	private TextView txtExcretas;
	private TextView txtBasura;
	private TextView txtAnimales;
	private TextView txtRiesgo;
	private TextView txtObservaciones;
	
	private Spinner spinnerTen;
	private Spinner spinnerEnerg;
	private Spinner spinnerNumApos;
	private Spinner spinnerNumDor;
	private Spinner spinnerTipoCoc;
	private Spinner spinnerCondCoc;
	private Spinner spinnerEnergCoc;
	private Spinner spinnerTipoBan;
	private Spinner spinnerCondBan;
	private Spinner spinnerTipoPiso;
	private Spinner spinnerCondPiso;
	private Spinner spinnerTipoPared;
	private Spinner spinnerCondPared;
	private Spinner spinnerTipoTecho;
	private Spinner spinnerCondTecho;
	private Spinner spinnerVentilacion;
	private Spinner spinnerIluminacion;
	private Spinner spinnerFtesAguas;
	private Spinner spinnerCalAguas;
	private Spinner spinnerExcretas;
	private Spinner spinnerBasura;
	private Spinner spinnerAnimales;
	private Spinner spinnerRiesgo;
	
	private EditText editCodViv;
	private EditText editM2;
	private EditText editObservaciones;
	
	private Button updateButton;
	
	private static String tipo_Estados[] = {"B","R","M"};
	private static String tipo_Seleccion[] = {"S","N"};
	private static String tipo_Materiales[] = {"T", "C", "K", "S", "M", "#", "P", "Z"};
	private static String tipo_Tenencia[] = {"P","A","R"};
	private static String tipo_Energia[] = { "E", "Q", "B", "G", "L"};
	private static String tipo_Banio[] = {"I", "E"};
	private static String tipo_Cocina[] = {"I", "C"};
	private static String tipo_Riesgo[] = {"1", "2", "3"};
	private static String tipo_ftesAgua[] = {"L", "Q", "PB", "R", "Q", "P", "C"};
	private static String tipo_dispExc[] = {"R", "C", "TS", "M", "L", "CL"};
	private static String tipo_elimBas[] = {"R", "C", "Q", "RP", "M", "E", "Q", "RPr"};
	
	private int codViv;
	private ContentValues values;
	private SQLiteAdapter adapter;

	public ViviendaFragment() {
		super();		
	}

	public ViviendaFragment(int codViv) {
		super();		
		this.codViv = codViv;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Prueba", "PersonaFragment");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.vivienda_tab, null);
		
		createWidgets();
		spinnerFunctions();
		buttonFunctions();
		editTextFunctions();
		return view;
	}
			 
	public void createTextViews() {
		txtCodViv = (TextView) view.findViewById(R.id.txtCodViv);
		txtM2 = (TextView) view.findViewById(R.id.txtM2);
		txtTenencia = (TextView) view.findViewById(R.id.txtTenencia);
		txtEnerg = (TextView) view.findViewById(R.id.txtEnerg);
		txtNumApos = (TextView) view.findViewById(R.id.txtNumApos);
		txtNumDor = (TextView) view.findViewById(R.id.txtNumDor);
		txtTipoCoc = (TextView) view.findViewById(R.id.txtTipoCoc);
		txtCondCoc = (TextView) view.findViewById(R.id.txtCondCoc);
		txtEnergCoc = (TextView) view.findViewById(R.id.txtEnergCoc);
		txtTipoBan = (TextView) view.findViewById(R.id.txtTipoBan);
		txtCondBan = (TextView) view.findViewById(R.id.txtCondBan);
		txtTipoPiso = (TextView) view.findViewById(R.id.txtTipoPiso);
		txtCondPiso = (TextView) view.findViewById(R.id.txtCondPiso);
		txtTipoPared = (TextView) view.findViewById(R.id.txtTipoPared);
		txtCondPared = (TextView) view.findViewById(R.id.txtCondPared);
		txtTipoTecho = (TextView) view.findViewById(R.id.txtTipoTecho);
		txtCondTecho = (TextView) view.findViewById(R.id.txtCondTecho);
		txtVentilac = (TextView) view.findViewById(R.id.txtVentilac);
		txtIluminac = (TextView) view.findViewById(R.id.txtIluminac);
		txtExcretas = (TextView) view.findViewById(R.id.txtExcretas);
		txtBasura = (TextView) view.findViewById(R.id.txtBasura);
		txtAnimales = (TextView) view.findViewById(R.id.txtAnimales);
		txtRiesgo = (TextView) view.findViewById(R.id.txtRiesgo);
		txtObservaciones = (TextView) view.findViewById(R.id.txtObservaciones);
	}	
	
	private void createSpinners() {
		
		spinnerTen = (Spinner) view.findViewById(R.id.spinnerTen);
        ArrayAdapter<CharSequence> lista_Ten = ArrayAdapter.createFromResource(view.getContext(),R.array.tenencia,android.R.layout.simple_spinner_item);
        lista_Ten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTen.setAdapter(lista_Ten);	  
        spinnerTen.setSelection(0, true);
		
		spinnerEnerg = (Spinner) view.findViewById(R.id.spinnerEnerg);
        ArrayAdapter<CharSequence> lista_Energ = ArrayAdapter.createFromResource(view.getContext(),R.array.energia,android.R.layout.simple_spinner_item);
        lista_Energ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnerg.setAdapter(lista_Energ);	  
        spinnerEnerg.setSelection(0, true);		
		
		spinnerNumApos = (Spinner) view.findViewById(R.id.spinnerNumApos);	
        ArrayAdapter<CharSequence> lista_NumApos = ArrayAdapter.createFromResource(view.getContext(),R.array.numeric,android.R.layout.simple_spinner_item);
        lista_NumApos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumApos.setAdapter(lista_NumApos);	  
        spinnerNumApos.setSelection(0, true);	
        
		spinnerNumDor = (Spinner) view.findViewById(R.id.spinnerNumDor);
		ArrayAdapter<CharSequence> lista_NumDor = ArrayAdapter.createFromResource(view.getContext(),R.array.numeric,android.R.layout.simple_spinner_item);
		lista_NumDor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerNumDor.setAdapter(lista_NumDor);	  
		spinnerNumDor.setSelection(0, true);			
		
		spinnerTipoCoc = (Spinner) view.findViewById(R.id.spinnerTipoCoc);
        ArrayAdapter<CharSequence> lista_TipoCoc = ArrayAdapter.createFromResource(view.getContext(),R.array.cocina,android.R.layout.simple_spinner_item);
        lista_TipoCoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoCoc.setAdapter(lista_TipoCoc);	  
        spinnerTipoCoc.setSelection(0, true);	
		
		spinnerCondCoc = (Spinner) view.findViewById(R.id.spinnerCondCoc);
        ArrayAdapter<CharSequence> lista_CondCoc = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_CondCoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondCoc.setAdapter(lista_CondCoc);	  
        spinnerCondCoc.setSelection(0, true);	
		
        spinnerEnergCoc = (Spinner) view.findViewById(R.id.spinnerEnergCoc);
        ArrayAdapter<CharSequence> lista_EnergCoc = ArrayAdapter.createFromResource(view.getContext(),R.array.energia,android.R.layout.simple_spinner_item);
        lista_EnergCoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnergCoc.setAdapter(lista_EnergCoc);	  
        spinnerEnergCoc.setSelection(0, true);	       
        
		spinnerTipoBan = (Spinner) view.findViewById(R.id.spinnerTipoBan);
        ArrayAdapter<CharSequence> lista_TipoBan = ArrayAdapter.createFromResource(view.getContext(),R.array.banio,android.R.layout.simple_spinner_item);
        lista_TipoBan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoBan.setAdapter(lista_TipoBan);	  
        spinnerTipoBan.setSelection(0, true);
		
		spinnerCondBan = (Spinner) view.findViewById(R.id.spinnerCondBan);
        ArrayAdapter<CharSequence> lista_CondBan = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_CondBan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondBan.setAdapter(lista_CondBan);	  
        spinnerCondBan.setSelection(0, true);		
		
		spinnerTipoPiso = (Spinner) view.findViewById(R.id.spinnerTipoPiso);
        ArrayAdapter<CharSequence> lista_TipoPiso = ArrayAdapter.createFromResource(view.getContext(),R.array.materiales,android.R.layout.simple_spinner_item);
        lista_TipoPiso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPiso.setAdapter(lista_TipoPiso);	  
        spinnerTipoPiso.setSelection(0, true);			
				
		spinnerCondPiso = (Spinner) view.findViewById(R.id.spinnerCondPiso);
        ArrayAdapter<CharSequence> lista_CondPiso = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_CondPiso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondPiso.setAdapter(lista_CondPiso);	  
        spinnerCondPiso.setSelection(0, true);	
		
		spinnerTipoPared = (Spinner) view.findViewById(R.id.spinnerTipoPared);
        ArrayAdapter<CharSequence> lista_TipoPared = ArrayAdapter.createFromResource(view.getContext(),R.array.materiales,android.R.layout.simple_spinner_item);
        lista_TipoPared.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPared.setAdapter(lista_TipoPared);	  
        spinnerTipoPared.setSelection(0, true);			
		
		spinnerCondPared = (Spinner) view.findViewById(R.id.spinnerCondPared);	
        ArrayAdapter<CharSequence> lista_CondPared = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_CondPared.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondPared.setAdapter(lista_CondPared);	  
        spinnerCondPared.setSelection(0, true);	
		
		spinnerTipoTecho = (Spinner) view.findViewById(R.id.spinnerTipoTecho);
        ArrayAdapter<CharSequence> lista_MatTec = ArrayAdapter.createFromResource(view.getContext(),R.array.materiales,android.R.layout.simple_spinner_item);
        lista_MatTec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoTecho.setAdapter(lista_MatTec);	  
        spinnerTipoTecho.setSelection(0, true);	
		
		spinnerCondTecho = (Spinner) view.findViewById(R.id.spinnerCondTecho);
        ArrayAdapter<CharSequence> lista_CondTecho = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_CondTecho.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondTecho.setAdapter(lista_CondTecho);	  
        spinnerCondTecho.setSelection(0, true);	
		
		spinnerVentilacion = (Spinner) view.findViewById(R.id.spinnerVentilacion);
        ArrayAdapter<CharSequence> lista_Vent = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_Vent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVentilacion.setAdapter(lista_Vent);	  
        spinnerVentilacion.setSelection(0, true);	
		
		spinnerIluminacion = (Spinner) view.findViewById(R.id.spinnerIluminacion);	
        ArrayAdapter<CharSequence> lista_Ilum = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_Ilum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIluminacion.setAdapter(lista_Ilum);	  
        spinnerIluminacion.setSelection(0, true);	
        
		spinnerFtesAguas = (Spinner) view.findViewById(R.id.spinnerFtesAguas);	
        ArrayAdapter<CharSequence> lista_Ftes = ArrayAdapter.createFromResource(view.getContext(),R.array.ftesAgua,android.R.layout.simple_spinner_item);
        lista_Ftes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFtesAguas.setAdapter(lista_Ftes);	  
        spinnerFtesAguas.setSelection(0, true);	        
        
		spinnerCalAguas = (Spinner) view.findViewById(R.id.spinnerCalAguas);	
        ArrayAdapter<CharSequence> lista_Cal = ArrayAdapter.createFromResource(view.getContext(),R.array.estados,android.R.layout.simple_spinner_item);
        lista_Cal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCalAguas.setAdapter(lista_Cal);	  
        spinnerCalAguas.setSelection(0, true);	  
		
		spinnerExcretas = (Spinner) view.findViewById(R.id.spinnerExcretas);
        ArrayAdapter<CharSequence> lista_Excretas = ArrayAdapter.createFromResource(view.getContext(),R.array.dispExcretas,android.R.layout.simple_spinner_item);
        lista_Excretas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExcretas.setAdapter(lista_Excretas);	  
        spinnerExcretas.setSelection(0, true);	
		
		spinnerBasura = (Spinner) view.findViewById(R.id.spinnerBasura);
        ArrayAdapter<CharSequence> lista_Bas = ArrayAdapter.createFromResource(view.getContext(),R.array.elimBasura,android.R.layout.simple_spinner_item);
        lista_Bas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBasura.setAdapter(lista_Bas);	  
        spinnerBasura.setSelection(0, true);	
		
		spinnerAnimales = (Spinner) view.findViewById(R.id.spinnerAnimales);
        ArrayAdapter<CharSequence> lista_Animales = ArrayAdapter.createFromResource(view.getContext(),R.array.seleccion,android.R.layout.simple_spinner_item);
        lista_Animales.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnimales.setAdapter(lista_Animales);	  
        spinnerAnimales.setSelection(0, true);	
		
		spinnerRiesgo = (Spinner) view.findViewById(R.id.spinnerRiesgo);
        ArrayAdapter<CharSequence> lista_Riesgo = ArrayAdapter.createFromResource(view.getContext(),R.array.riesgo,android.R.layout.simple_spinner_item);
        lista_Riesgo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRiesgo.setAdapter(lista_Riesgo);	  
        spinnerRiesgo.setSelection(0, true);	
	}
	
	private void spinnerFunctions() {
		spinnerTen.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTen.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerEnerg.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerEnerg.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		
		spinnerNumApos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerNumApos.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
        
		spinnerNumDor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerNumDor.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});		
		
		spinnerTipoCoc.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTipoCoc.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerCondCoc.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerCondCoc.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
        spinnerEnergCoc.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerEnergCoc.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});	       
        
		spinnerTipoBan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTipoBan.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerCondBan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerCondBan.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});		
		
		spinnerTipoPiso.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTipoPiso.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});			
				
		spinnerCondPiso.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerCondPiso.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerTipoPared.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTipoPared.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});			
		
		spinnerCondPared.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerCondPared.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});	
		
		spinnerTipoTecho.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTipoTecho.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerCondTecho.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerCondTecho.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerVentilacion.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerVentilacion.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});	
		
		spinnerIluminacion.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerIluminacion.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});	
		
		spinnerFtesAguas.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerFtesAguas.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});	
		
		spinnerCalAguas.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerCalAguas.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});	
		
		spinnerExcretas.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerExcretas.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerBasura.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerBasura.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerAnimales.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerAnimales.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		
		spinnerRiesgo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerRiesgo.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
	}
	
	private void createEditTexts() {
		editCodViv =  (EditText) view.findViewById(R.id.editCodViv);
		editCodViv.setText(String.valueOf(codViv));
		editCodViv.setEnabled(false);
		editM2 = (EditText) view.findViewById(R.id.editM2); //validar que no sea <= 0
		editObservaciones = (EditText) view.findViewById(R.id.editObservaciones);
	}
	
	private void editTextFunctions() {
		editM2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					editM2.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (editM2.getText().toString().isEmpty()) {
						editM2.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						editM2.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		editObservaciones.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					editObservaciones.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (editObservaciones.getText().toString().isEmpty()) {
						editObservaciones.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						editObservaciones.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
	}
	
	
	private void createButtons() {
		updateButton =  (Button) view.findViewById(R.id.updateButton);
	}
	
	private void buttonFunctions() {

		updateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//String nombre = editNombre.getText().toString();
				//Toast.makeText(getActivity(), nombre, Toast.LENGTH_LONG).show();		
			}				
		});
		
	}
	
	private void createWidgets() {
		createTextViews();
		createSpinners();
		createEditTexts();
		createButtons();
	}
	
	private void setValues() {
		//Es necesario un codigo para la condicion socioeconomica, dado que
		//estos valores poseen un historico?
		Validation validation = new Validation();
		values = new ContentValues();
		values.put("codViv", codViv);	
		values.put("fechaVisita", validation.getDate());
		try {
			values.put("M2", Integer.parseInt(editM2.getText().toString()));
		} catch (Exception e) {
			values.put("M2", 0);
		}
		values.put("codTenencia",tipo_Tenencia[spinnerTen.getSelectedItemPosition()]);
		values.put("codEnergia",tipo_Energia[spinnerEnerg.getSelectedItemPosition()]);
		values.put("codTenencia",tipo_Tenencia[spinnerTen.getSelectedItemPosition()]);
		values.put("nApos",Integer.parseInt(spinnerNumApos.getSelectedItem().toString()));
		values.put("nDorm",Integer.parseInt(spinnerNumDor.getSelectedItem().toString()));
		values.put("tipoCocina",tipo_Cocina[spinnerTipoCoc.getSelectedItemPosition()]);
		values.put("condCocina",tipo_Estados[spinnerCondCoc.getSelectedItemPosition()]);
		values.put("energCocina",tipo_Energia[spinnerEnergCoc.getSelectedItemPosition()]);
		values.put("tipoBanio",tipo_Banio[spinnerTipoBan.getSelectedItemPosition()]);
		values.put("condBanio",tipo_Estados[spinnerCondBan.getSelectedItemPosition()]);
		values.put("tipoPiso",tipo_Materiales[spinnerTipoPiso.getSelectedItemPosition()]);
		values.put("condPiso",tipo_Estados[spinnerCondPiso.getSelectedItemPosition()]);
		values.put("tipoPared",tipo_Materiales[spinnerTipoPared.getSelectedItemPosition()]);
		values.put("condPared",tipo_Estados[spinnerCondPared.getSelectedItemPosition()]);	
		values.put("tipoTecho",tipo_Materiales[spinnerTipoTecho.getSelectedItemPosition()]);
		values.put("condTecho",tipo_Estados[spinnerCondTecho.getSelectedItemPosition()]);
		values.put("condVent",tipo_Estados[spinnerVentilacion.getSelectedItemPosition()]);
		values.put("condIlum",tipo_Estados[spinnerIluminacion.getSelectedItemPosition()]);
		values.put("ftesAgua",tipo_ftesAgua[spinnerFtesAguas.getSelectedItemPosition()]);
		values.put("CondFtesAgua",tipo_Estados[spinnerCalAguas.getSelectedItemPosition()]);
		values.put("dispExcretas",tipo_dispExc[spinnerExcretas.getSelectedItemPosition()]);
		values.put("dispBasura", tipo_elimBas[spinnerBasura.getSelectedItemPosition()]);
		values.put("animCondIns",tipo_Seleccion[spinnerAnimales.getSelectedItemPosition()]);
		values.put("riesgo",tipo_Riesgo[spinnerRiesgo.getSelectedItemPosition()]);
		values.put("observaciones",editObservaciones.getText().toString());		
	}
	
	public void store() {
		setValues();
		adapter.open();
		adapter.insert("Vivienda", values);
		adapter.close();
	}
	
	public void update() {
		setValues();
		adapter.open();
		adapter.update("Vivienda", values, "codViv", codViv);
		adapter.close();		
	}
	
	public void load() {
		
	}
}
