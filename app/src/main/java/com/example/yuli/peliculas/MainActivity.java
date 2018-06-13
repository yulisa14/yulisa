package com.example.yuli.peliculas;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<DatoPelicula>peliculas;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv)
    RecyclerView rv;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this );
        setSupportActionBar(toolbar);

      llamarRetrofit();
    }
private void llamarRetrofit(){
dialog=ProgressDialog.show(this, "", "Esperando Datos",
        true);
//crear retrofit
    Retrofit retrofit= new Retrofit.Builder().baseUrl(interfaceWS.url).
            addConverterFactory(GsonConverterFactory.create()).build();
//crear interfaz
    interfaceWS interfaz = retrofit.create(interfaceWS.class);
    Call<List<DatoPelicula>> llamada =interfaz.traerPeliculas();
    //CREAR PETICION

    llamada.enqueue(new Callback<List<DatoPelicula>>() {
        //CUANDO REALMENTE OCURRIO
        @Override
        public void onResponse(Call<List<DatoPelicula>> call, Response<List<DatoPelicula>> response) {
         peliculas=response.body();
         dialog.dismiss();
         llenarRecycler();


        }
//CUANDO FALLA
        @Override
        public void onFailure(Call<List<DatoPelicula>> call, Throwable t) {

        }
    });


    }
    private  void  llenarRecycler(){
      AdaptadorRecycler adaptador= new AdaptadorRecycler(this, peliculas);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adaptador);



    }
}

