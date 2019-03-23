package com.cursoandroid.raone.retrofit;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cursoandroid.raone.retrofit.models.Course;
import com.cursoandroid.raone.retrofit.models.Instructor;
import com.cursoandroid.raone.retrofit.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AulaRetrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula_retrofit);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UdacityService service = retrofit.create(UdacityService.class);
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            private static final String TAG ="suemar" ;

            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if (!response.isSuccessful()){
                    Log.i("TAG","Erro " + response.code());
                }
                else{
                    //Requisição retornou com sucesso.
                    UdacityCatalog catalog = response.body();

                    for (Course c : catalog.courses){
                        Log.i(TAG, String.format("%s: %s:", c.title, c.subtitle));

                        for (Instructor i : c.instructors){
                            Log.i(TAG, i.name);
                        }

                        Log.i(TAG,"----------");

                    }
                }

            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e(TAG, "Erro " + t.getMessage());

            }
        });
    }
}
