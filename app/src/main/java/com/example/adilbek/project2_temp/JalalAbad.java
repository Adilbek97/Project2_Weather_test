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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class JalalAbad extends Fragment {
    private static final String TAG = "TAG";
    TextView celcius,farangate,city,coment,region,loctime,clouds,wind,lastupdate;
    ImageView icon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_jalal_abad, container, false);
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
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(ApiTemp.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiTemp apiTemp = retrofit.create(ApiTemp.class);
        Call<Example2> listCall = apiTemp.getData("Jalal-Abad%20Kyrgyzstan");
        listCall.enqueue(new Callback<Example2>() {
            String eki;
            char[] bir=new char[41];
            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
               // Toast.makeText(view.getContext(),"onResponse ko keldi",Toast.LENGTH_SHORT).show();
                Example2 examples=response.body();
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
                Toast.makeText(view.getContext(),"kata",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: kata boldu");
            }
        });
        return view;// Inflate the layout for this fragment

    }
public interface search{
    String BASE_URL = "http://api.apixu.com/";
    @GET("v1/current.json?key=775f00d9a2754b1e9c0130256182106&q="+"Ysyk-Kol"+"%20"+"Kyrgyzstan")
    Call<Example> getData();
}
}
