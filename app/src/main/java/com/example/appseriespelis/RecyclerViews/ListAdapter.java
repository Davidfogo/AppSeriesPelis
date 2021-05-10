package com.example.appseriespelis.RecyclerViews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appseriespelis.Botons_Fragment.FragmentPeliSerie;
import com.example.appseriespelis.Objetos.PeliculaSerie;
import com.example.appseriespelis.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<PeliculaSerie> mData;
    private LayoutInflater mInflater;
    private Context context;
    private int fav;

    //1 fav true
    //0 fav false

    public ListAdapter(List<PeliculaSerie> itemList, Context context, int fav){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.fav = fav;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.item_list,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder,final int position){
        holder.bindData(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Aqui lo que hacemos es pasarle el fragmento al que queremos dirigirnos al hacer clic
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                FragmentPeliSerie fragmentPeliSerie =  new FragmentPeliSerie();

                //De la clase PeliculaSerie le decimos que recoja la informacion del array mData segun la posicion que a sido seleccionada
                PeliculaSerie peli = mData.get(position);
                Log.i("recTest", "--- aquí2");

                /*Aqui lo que hacemos es crear un bundle para guardar datos como si fuera un paquete
                y le pasamos la variable peli que hemos nombrado arriba y que almacene esa informacion
                con el nombre "peli" */
                Bundle bundle = new Bundle();
                bundle.putSerializable("peli",  peli);

                //aqui lo que pasamos es el contenido del bundle al fragment que queremos enviar los datos
                fragmentPeliSerie.setArguments(bundle);
                if(fav == 1){
                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.recFavs, fragmentPeliSerie);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.recSearch, fragmentPeliSerie);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });
    }

    public void setItems(List<PeliculaSerie> items){mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView nombrepeli,status,tipopeli;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            nombrepeli = itemView.findViewById(R.id.NombrePelis_tv);
            status = itemView.findViewById(R.id.StatusTextView);
            tipopeli = itemView.findViewById(R.id.tipoPeliTextView);
        }

        void bindData(final PeliculaSerie item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombrepeli.setText((item.getNombrepeli()));
            status.setText((item.getStatus()));
            tipopeli.setText((item.getTipopeli()));

        }
    }
}
