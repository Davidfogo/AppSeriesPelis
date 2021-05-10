package com.example.appseriespelis.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.appseriespelis.Objetos.ListasCreadas;
import com.example.appseriespelis.R;

import java.util.List;

public class ListAdapter_listas extends RecyclerView.Adapter<ListAdapter_listas.ViewHolder>{
    private List<ListasCreadas> mData;
    private LayoutInflater mInflater;
    private Context context;
    private int fav;

    //1 fav true
    //0 fav false

    public ListAdapter_listas(List<ListasCreadas> itemList, Context context, int fav){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.fav = fav;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ListAdapter_listas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.design_listas,null);
        return new ListAdapter_listas.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter_listas.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public void setItems(List<ListasCreadas> items){mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textolista;

        ViewHolder(View itemView){
            super(itemView);
            textolista = itemView.findViewById(R.id.texto_lista);
        }

        void bindData(final ListasCreadas item){

            textolista.setText((item.getNombreLista()));

        }
    }
}
