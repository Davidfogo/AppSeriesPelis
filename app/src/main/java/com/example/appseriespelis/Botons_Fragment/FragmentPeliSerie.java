package com.example.appseriespelis.Botons_Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appseriespelis.R;
import com.example.appseriespelis.Objetos.PeliculaSerie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class FragmentPeliSerie extends Fragment {
    protected boolean algo = true;

    Boolean fav_check = false;
    public FragmentPeliSerie() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fpeliserie = inflater.inflate(R.layout.fragment_peli_serie, container, false);

        /*Lo que hacemos aqui es decir que en la variable peli de tipos de clase PeliculaSerie
        * me de los argumentos introducidos del bundle nombrado con la llave "peli"
        * y despues un log para ver si sale en contenido seleccionado.*/

        Log.i("recTest", "--- aquí");
        PeliculaSerie peli = (PeliculaSerie) this.getArguments().getSerializable("peli");
        Log.i("peli", peli.getNombrepeli());

        String url_img = "https://image.tmdb.org/t/p/w500/" + peli.getImg();

        TextView txt_peliserie = fpeliserie.findViewById(R.id.txt_nombrepeli);
        TextView txt_popularidad = fpeliserie.findViewById(R.id.txt_popularidad);
        TextView txt_genre = fpeliserie.findViewById(R.id.txt_genre);
        TextView txt_coment = fpeliserie.findViewById(R.id.txt_coment);
        ImageView imageView = fpeliserie.findViewById(R.id.IV_img);

        ImageView corimg = fpeliserie.findViewById(R.id.corimg);



        existePeliSerie(peli,corimg);
        corimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (algo == false) {
                    //BORRAR
                    corimg.setColorFilter(Color.parseColor("#8E8E8E"));
                    BorrarPeliSerie(peli);
                    algo = true;
                } else if (algo == true) {
                    //GUARDAR
                    corimg.setColorFilter(Color.parseColor("#ff0000"));
                    guardarPeliSerie(peli);
                    algo = false;
                }

            }
        });
        Picasso.get().load(url_img).into(imageView);

       // for(int i=0)
        //txt_genre.setText(peli.getGenreFronNum(peli.getGenre().get(0)));

        txt_coment.setText(peli.getOverview());

        txt_popularidad.setText(peli.getTipopeli());
        //String peliserie = txt_peliserie.getText().toString();

        txt_peliserie.setText(peli.getNombrepeli());



        return fpeliserie;
    }

    public void guardarPeliSerie(PeliculaSerie peliserie){

        //connectem a firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //i ho guardem
        DatabaseReference db = database.getReference("favourites");
        // Write a message to the database
        db.child(Integer.toString(peliserie.getId())).setValue(peliserie);


    }
    public void BorrarPeliSerie(PeliculaSerie peliserie){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference("favourites");
        db.child(Integer.toString(peliserie.getId())).removeValue();

    }


    public void existePeliSerie(PeliculaSerie peliculaSerie,ImageView imagen){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference("favourites");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(Integer.toString(peliculaSerie.getId()))) {
                    imagen.setColorFilter(Color.parseColor("#ff0000"));
                    algo= false;
                } else {
                    imagen.setColorFilter(Color.parseColor("#8E8E8E"));
                    algo= true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
