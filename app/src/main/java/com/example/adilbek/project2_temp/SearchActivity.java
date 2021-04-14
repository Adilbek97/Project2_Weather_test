package com.example.adilbek.project2_temp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adilbek.project2_temp.api.ApiTemp;
import com.example.adilbek.project2_temp.models.Example;
import com.example.adilbek.project2_temp.models.newModels.Example2;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiTemp apiTemp;
    TextView celcius,farangate,city,coment,region,loctime,clouds,wind,lastupdate;
    ImageView icon;
    EditText eQuery;
    Call<Example2> callTemp;
    // tereze jasalganda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // menchiktoolor
        celcius=findViewById(R.id.textCelcius);
        farangate=findViewById(R.id.textFarangate);
        city=findViewById(R.id.textCity);
        icon=findViewById(R.id.imageIcon);
        coment=findViewById(R.id.textparagraf);
        region=findViewById(R.id.textRegion);
        loctime=findViewById(R.id.textLocalTime);
        clouds=findViewById(R.id.textclouds);
        wind=findViewById(R.id.textwind);
        lastupdate=findViewById(R.id.textUpdate);
        eQuery = findViewById(R.id.queryText);

        //internet menen ishtoo uchun retrofitti aluu
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit =new Retrofit.Builder()
                .baseUrl(ApiTemp.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiTemp = retrofit.create(ApiTemp.class);
    }
    public void  getTemp(View view){
        String queri = eQuery.getText().toString();
        Toast.makeText(this,queri,Toast.LENGTH_SHORT).show();
        // maalymattardy aluu
        if (!queri.isEmpty()){
            callTemp = apiTemp.getData(queri);
            callTemp.enqueue(new Callback<Example2>() {
                String eki;
                char[] bir=new char[41];
                @Override
                public void onResponse(Call<Example2> call, Response<Example2> response) {

                    Example2 examples=response.body();
                    //kelgen maalymattardy chygaruu
                    if(examples != null) {
                        if (examples.getCurrent() != null ) {
                            celcius.setText(String.valueOf(examples.getCurrent().getTemperature()) + " C");
                            farangate.setText("Абанын нымдуулугу: " + String.valueOf(examples.getCurrent().getHumidity()));
                            wind.setText(String.valueOf(examples.getCurrent().getWindSpeed()));
                            clouds.setText(String.valueOf(examples.getCurrent().getCloudcover()));
                            lastupdate.setText(examples.getCurrent().getObservationTime());
                            if (examples.getCurrent().getWeatherDescriptions() != null && !examples.getCurrent().getWeatherDescriptions().isEmpty())
                                coment.setText(examples.getCurrent().getWeatherDescriptions().get(0));
                            if (examples.getCurrent().getWeatherIcons() != null && !examples.getCurrent().getWeatherIcons().isEmpty())
                                Glide.with(SearchActivity.this).load(examples.getCurrent().getWeatherIcons().get(0)).into(icon);
                        }
                        if (examples.getLocation() != null ) {
                            city.setText(examples.getLocation().getCountry());
                            region.setText(examples.getLocation().getRegion());
                            loctime.setText(examples.getLocation().getLocaltime());
                            for (int i = 2; i < bir.length; i++) {
                                eki += bir[i];
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<Example2> call, Throwable t) {
                    // maalymattar kelbese bildiruu
                    Toast.makeText(SearchActivity.this,"Internetten kata ",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
