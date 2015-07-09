package com.android.dango.day2ejcolores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dango on 07/07/2015.
 */
public class CrowAdapter extends RecyclerView.Adapter<CrowAdapter.AdapterViewHolder>{

    private AdapterViewHolder adapterViewHolder;
    private int i;

    @Override
    public CrowAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CrowAdapter.AdapterViewHolder adapterViewHolder, int i) {
        adapterViewHolder.icon.setImageDrawable(adapterViewHolder.v.getResources().getDrawable(R.drawable.ic_icon));
        adapterViewHolder.name.setText("Name");
    }


    @Override
    public int getItemCount() {
        //Debemos retornar el tama�o de todos los elementos contenidos en el viewholder
        //Por defecto es return 0 --> No se mostrar� nada.
        //return contactos.size();
        return 0;
    }



    //Definimos una clase viewholder que funciona como adapter para
    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicaci�n. As� que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cu�l se realizar�a �nicamente la primera vez y el resto
        *  llamar�amos a la referencia en el ViewHolder, ahorr�ndonos procesamiento.
        */

        public ImageView icon;
        public TextView name;
        public TextView phone;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.phone = (TextView) itemView.findViewById(R.id.phone);
        }
    }
}