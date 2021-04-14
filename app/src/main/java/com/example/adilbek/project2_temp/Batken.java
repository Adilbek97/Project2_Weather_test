package com.example.adilbek.project2_temp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Batken extends Fragment {
    private static final String TAG = "TAG";
    TextView celcius,farangate,city,coment,region,loctime,clouds,wind,lastupdate;
    ImageView icon;

    // tereze jasalganda
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_batken, container, false);
        // menchiktoolor
        celcius=view.findViewById(R.id.textCelcius);
        farangate=view.findViewById(R.id.textFarangate);
        city=view.findViewById(R.id.textCity);
        icon=view.findViewById(R.id.imageIcon);
        coment=view.findViewById(R.id.textparagraf);
        region=view.findViewById(R.id.textRegion);
        loctime=view.findViewById(R.id.textLocalTime);
        clouds=view.findViewById(R.id.textclouds);
        wind=view.findViewById(R.id.textwind);
        lastupdate=view.findViewById(R.id.textUpdate);
        //internet menen ishtoo uchun retrofitti aluu
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(ApiTemp.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiTemp apiTemp = retrofit.create(ApiTemp.class);
        // maalymattardy aluu
        Call<Example2> listCall = apiTemp.getData("Batken%20Kyrgyzstan");
        listCall.enqueue(new Callback<Example2>() {
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
                         Glide.with(view.getContext()).load(examples.getCurrent().getWeatherIcons().get(0)).into(icon);
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
                Toast.makeText(view.getContext(),"kata",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: kata boldu");
            }
        });
        return view;
    }


}
