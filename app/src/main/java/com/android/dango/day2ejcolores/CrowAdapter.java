package com.android.dango.day2ejcolores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dango on 07/07/2015.
 */
public class CrowAdapter extends RecyclerView.Adapter<CrowAdapter.AdapterViewHolder>{

    //private AdapterViewHolder adapterViewHolder;
    //private int i;
    //DataBase cDatabase;
    ArrayList<String> Mails;
    ArrayList<String> Passws;

    CrowAdapter(ArrayList<String> mails, ArrayList<String> passws){
        Mails = mails;
        Passws = passws;
    }


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
        /*cDatabase.getWritableDatabase();
        if(cDatabase != null){
            Cursor c = cDatabase.getAllUsers();
            if(c.moveToPosition(i)){
                adapterViewHolder.icon.setImageDrawable(adapterViewHolder.v.getResources().getDrawable(R.drawable.ic_icon));
                adapterViewHolder.name.setText(c.getString(c.getColumnIndex("mail")));
            }
        }*/
        adapterViewHolder.icon.setImageDrawable(adapterViewHolder.v.getResources().getDrawable(R.drawable.ic_icon));
        adapterViewHolder.mail.setText(Mails.get(i));
        adapterViewHolder.password.setText(Passws.get(i));
    }


    @Override
    public int getItemCount() {
        //Debemos retornar el tamaño de todos los elementos contenidos en el viewholder
        //Por defecto es return 0 --> No se mostrará nada.
        //return contactos.size();
        /*int count = 0;
        cDatabase.getWritableDatabase();
        if(cDatabase != null){
            Cursor c = cDatabase.getAllUsers();
            if(c.moveToNext()){
                count++;
                Log.v("Counting", "counted " + i);
            }else
                Log.v("Counting", "Finished Counting");
        }
        return count;*/
        return Mails.size();
    }



    //Definimos una clase viewholder que funciona como adapter para
    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicación. Así que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cuál se realizaría únicamente la primera vez y el resto
        *  llamaríamos a la referencia en el ViewHolder, ahorrándonos procesamiento.
        */

        public ImageView icon;
        public TextView mail;
        public TextView password;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.mail = (TextView) itemView.findViewById(R.id.mail);
            this.password = (TextView) itemView.findViewById(R.id.password);
        }
    }
}