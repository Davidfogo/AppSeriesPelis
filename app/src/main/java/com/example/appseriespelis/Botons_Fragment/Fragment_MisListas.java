package com.example.appseriespelis.Botons_Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appseriespelis.Objetos.ListasCreadas;
import com.example.appseriespelis.Objetos.PeliculaSerie;
import com.example.appseriespelis.R;
import com.example.appseriespelis.RecyclerViews.ListAdapter_listas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment_MisListas extends Fragment {
    ArrayList<ListasCreadas> llistes;
    protected FirebaseDatabase database;
    protected DatabaseReference ref;

    protected View fmilistas;
    public Fragment_MisListas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fmilistas = inflater.inflate(R.layout.fragment__mis_listas, container, false);
        llistes = new ArrayList<ListasCreadas>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("mislistas");

        FloatingActionButton botonañadirlista = fmilistas.findViewById(R.id.btn_flotante);
        botonañadirlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("AGREGAR LISTAS");
                alert.setMessage("¿Que lista quieres ver?");

                final EditText input = new EditText(getContext());
                input.setHint("Film");
                alert.setView(input);
                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        guardarLista(input);

                        Toast.makeText(getContext(),"Gracias",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Adios",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                llistes.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    ListasCreadas lista = postSnapshot.getValue(ListasCreadas.class);
                    llistes.add(lista);
                    Log.i("logTest","Peli = " + lista.getNombreLista());
                }

                showLists(llistes, fmilistas);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("prova", "Failed to read value.", error.toException());
            }
        });
       return  fmilistas;
    }
    public void guardarLista(EditText input){
        // Write a message to the database

        String key = ref.push().getKey();
        ref.child(key).setValue(new ListasCreadas(input.getText().toString()));



    }


    public void showLists( ArrayList<ListasCreadas> listas, View fmilistas){
       ListAdapter_listas listAdapterListas = new ListAdapter_listas(listas, getContext(), 0);
        RecyclerView recyclerView = fmilistas.findViewById(R.id.recyclermislistas);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapterListas);
    }


}