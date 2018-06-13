package com.example.yuli.peliculas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePelicula extends AppCompatActivity {

    private String name;
    private int Id;
    ProgressDialog dialog;
    public DatoPelicula pelicula;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapser)
    CollapsingToolbarLayout collapser;

    @BindView(R.id.fondo)
    ImageView background;

    @BindView(R.id.txtSinopsis)
    TextView txtSinopsis;

    @BindView(R.id.txtcomentarios)
    TextView txtcomentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        Id = i.getIntExtra("id", -1);
        name = i.getStringExtra("name");
        collapser.setTitle(name);
        llamarRetrofit();

    }

    public void llamarRetrofit() {
        dialog=ProgressDialog.show(this, "","cargando mensaje", true);

        Retrofit retrofit= new Retrofit.Builder().baseUrl(interfaceWS.url).addConverterFactory
                (GsonConverterFactory.create()).build();

        interfaceWS interfaz =retrofit.create(interfaceWS.class);
        final DatoPelicula peliculaLocal = new DatoPelicula();
        peliculaLocal.setId(Id);
        Call<DatoPelicula> peticion=interfaz.getDetalle(peliculaLocal);
        peticion.enqueue(new Callback<DatoPelicula>() {
            @Override
            public void onResponse(Call<DatoPelicula> call, Response<DatoPelicula> response) {
             pelicula=response.body();
             dialog.dismiss();
             llenarVentana();
            }

            @Override
            public void onFailure(Call<DatoPelicula> call, Throwable t) {

            }
        });
    }


    //traer los datos y rrellenar la pantalla
    public  void  llenarVentana (){
        if (pelicula!=null)
        {
            txtcomentarios.setText(pelicula.getComments().replace("#", "\n"));
            txtSinopsis.setText(pelicula.getPlot());
            Picasso.with(this).load(interfaceWS.url+pelicula.getPathImg()).into(background);
        }
    }
//funciona flecha hacia atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
