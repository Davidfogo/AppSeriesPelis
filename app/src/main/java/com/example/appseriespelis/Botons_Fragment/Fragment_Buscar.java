package com.example.appseriespelis.Botons_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.appseriespelis.Objetos.PeliculaSerie;
import com.example.appseriespelis.R;
import com.example.appseriespelis.fetchData;

import java.util.ArrayList;


public class Fragment_Buscar extends Fragment {
    Button GuardarPeli;
    EditText et_buscar;
    View Fragment_Buscar;

    public ArrayList<PeliculaSerie> arraypelisseries;

    public Fragment_Buscar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fragment_Buscar = inflater.inflate(R.layout.fragment__buscar, container, false);

        et_buscar = Fragment_Buscar.findViewById(R.id.et_buscar);

        GuardarPeli= Fragment_Buscar.findViewById(R.id.btn_BuscarPeli);


        arraypelisseries= new ArrayList<PeliculaSerie>();

        GuardarPeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Le pasamos el nombre de la peli que queremos buscar al fetchData, este lo que hara es buscar
                dentro  de la API las series/pelis que se encentren con ese nombre*/
                String Peli_Serie = et_buscar.getText().toString();
                fetchData process = new fetchData(Peli_Serie, getActivity());
                process.execute();

            }
        });

        return Fragment_Buscar;
    }


}