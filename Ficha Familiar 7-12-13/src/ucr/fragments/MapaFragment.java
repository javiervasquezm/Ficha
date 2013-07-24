package ucr.fragments;

import ucr.ff.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapaFragment extends Fragment {


	private View view;
	
	public MapaFragment() {
		super();
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Prueba", "MapaFragment");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mapa_tab, null);
		
		createWidgets();
		buttonFunctions();
		return view;
	}
	
	public void createWidgets() {
		
	}
	
	public void buttonFunctions() {
		
	}
	
	public void store() {
		
	}
	
	public void load() {
		
	}

}
