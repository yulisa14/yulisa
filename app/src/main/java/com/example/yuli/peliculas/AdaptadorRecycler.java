package com.example.yuli.peliculas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by fundacion cuervo on 18/05/2018.
 */

public class AdaptadorRecycler extends  RecyclerView.Adapter<AdaptadorRecycler.HolderAdaptador>
implements  ClickListenerPeliculas{

    private  final Context context;
    private List<DatoPelicula>peliculas;
    public AdaptadorRecycler(Context context, List<DatoPelicula>peliculas)
    {
        this.context=context;
        this.peliculas=peliculas;
    }

    @NonNull
    @Override
    public AdaptadorRecycler.HolderAdaptador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return  new HolderAdaptador(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycler.HolderAdaptador holder, int position) {
        DatoPelicula pelicula=peliculas.get(position);
        holder.txtTitulo.setText(pelicula.getName());
        holder.txtSinopsis.setText(pelicula.getPlot());
        holder.rating.setRating((float)Math.random()*5);
        Picasso.with(context).load(interfaceWS.url+pelicula.getPathImg()).
                into(holder.silencio);

    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    @Override
    public void onItemClick(View v, int posicion) {
        Log.e("clic en pelicula", "posicion:"+posicion);
        DatoPelicula pelicula=peliculas.get(posicion);
        Intent i=new Intent(context, DetallePelicula.class);
        i.putExtra("id",pelicula.getId());
        i.putExtra("name",pelicula.getName());
context.startActivity(i);
    }
    //clase interna
    public static class  HolderAdaptador extends RecyclerView.ViewHolder
          implements  View.OnClickListener{

        @BindView(R.id.avatar)
        CircleImageView silencio;
        @BindView(R.id.txtTitulo)
        TextView txtTitulo;
        @BindView(R.id.rating)
        RatingBar rating;
        @BindView(R.id.sinopsis)
        TextView txtSinopsis;

        //variable que sea de la interface click
                 ClickListenerPeliculas listener;



        public HolderAdaptador(View itemView, ClickListenerPeliculas listener) {
            super(itemView);
            this.listener=listener;
        ButterKnife.bind(this,itemView );
        //esta LINEA CUIDA CUANDO LE DAS UN CLICK A LA VISTA  Y LO CONTROLA CON EL THIS
        itemView.setOnClickListener(this);

        }

    @Override
    public void onClick(View view) {
            listener.onItemClick(view, getAdapterPosition());

    }
}
}
