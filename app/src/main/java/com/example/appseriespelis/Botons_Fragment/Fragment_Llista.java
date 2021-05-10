package com.example.appseriespelis.Botons_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appseriespelis.Objetos.PeliculaSerie;
import com.example.appseriespelis.R;
import com.example.appseriespelis.RecyclerViews.ListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Llista extends Fragment {
    //HashMap<String, Object> map2 = new HashMap<String, Object>();

    protected ArrayList<PeliculaSerie> favoritas;
    TextView texto;
    public Fragment_Llista() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fLista = inflater.inflate(R.layout.fragment__llista, container, false);
        //texto = fLista.findViewById(R.id.TEXTO);

        favoritas= new ArrayList<PeliculaSerie>();

        ObtenerDatos(fLista);




        return fLista;

    }

    public void ObtenerDatos(View fLista){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference("favourites");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("logTest " ,""+dataSnapshot.getChildrenCount());

                favoritas.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    PeliculaSerie peli = postSnapshot.getValue(PeliculaSerie.class);
                    favoritas.add(peli);
                    Log.i("logTest","Peli = " + peli.getNombrepeli());
                }



                ListAdapter listAdapter = new ListAdapter(favoritas, getContext(), 1);
                RecyclerView recyclerView = fLista.findViewById(R.id.recyclerFavoritos);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(listAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("logTest", "Failed to read value.", error.toException());
            }
        });
    }
}