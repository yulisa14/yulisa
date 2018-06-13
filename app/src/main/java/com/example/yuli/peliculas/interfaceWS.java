package com.example.yuli.peliculas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by fundacion cuervo on 30/05/2018.
 */

public interface interfaceWS {
    public static final String url="http://devtequila.com/itny/services/";
@GET("lista.php")
Call<List<DatoPelicula>> traerPeliculas();
@POST("details.php")
    Call<DatoPelicula>getDetalle(@Body DatoPelicula idJson);
}