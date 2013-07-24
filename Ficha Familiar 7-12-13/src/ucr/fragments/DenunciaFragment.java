package ucr.fragments;


import ucr.ff.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DenunciaFragment extends Fragment {


	private View view;
	private EditText id;
	private EditText descripcion;
	private EditText fecha;
	private EditText denunciante;
	private EditText telDenunciante;
	private EditText asignada;
	private EditText longitud;
	private EditText latitud;
	
	
	public DenunciaFragment() {
		super();
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Prueba", "DenunciaFragment");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.denuncias_tab, null);
		
		createWidgets();
		buttonFunctions();
		editTextFunctions();
		return view;
	}
	
	
	private void createEditTexts() {
		id = (EditText) view.findViewById(R.id.ID);
		descripcion = (EditText) view.findViewById(R.id.descripcion);
		fecha = (EditText) view.findViewById(R.id.fecha);
		denunciante = (EditText) view.findViewById(R.id.denunciante);
		telDenunciante = (EditText) view.findViewById(R.id.telDenunciante);
		asignada = (EditText) view.findViewById(R.id.asignada);
		longitud = (EditText) view.findViewById(R.id.longitud);
		latitud = (EditText) view.findViewById(R.id.latitud);
	}
	
	private void editTextFunctions() {
		id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					id.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (id.getText().toString().isEmpty()) {
						id.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						id.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});

		descripcion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					descripcion.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (descripcion.getText().toString().isEmpty()) {
						descripcion.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						descripcion.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});

		fecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					fecha.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (fecha.getText().toString().isEmpty()) {
						fecha.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						fecha.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});	    

		denunciante.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					denunciante.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (denunciante.getText().toString().isEmpty()) {
						denunciante.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						denunciante.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		telDenunciante.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					telDenunciante.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (telDenunciante.getText().toString().isEmpty()) {
						telDenunciante.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						telDenunciante.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		asignada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					asignada.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (asignada.getText().toString().isEmpty()) {
						asignada.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						asignada.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});	    

		longitud.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					longitud.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (longitud.getText().toString().isEmpty()) {
						longitud.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						longitud.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		latitud.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					latitud.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (latitud.getText().toString().isEmpty()) {
						latitud.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						latitud.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
	}
	
	public void createWidgets() {
		createEditTexts();
	}
	
	public void buttonFunctions() {
		
	}
	
	public void store() {
		
	}
	
	public void load() {
		
	}

}
