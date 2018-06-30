package com.example.adilbek.project2_temp;

import android.content.Context;
import android.net.Uri;
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
import com.example.adilbek.project2_temp.api.ApiNaryn;
import com.example.adilbek.project2_temp.api.ApiTalas;
import com.example.adilbek.project2_temp.api.ApiTemp;
import com.example.adilbek.project2_temp.models.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Talas extends Fragment {
    private static final String TAG = "TAG";
    TextView celcius,farangate,city,coment,region,loctime,clouds,wind,lastupdate;
    ImageView icon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_talas, container, false);
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
        ApiTalas apiTemp = retrofit.create(ApiTalas.class);
        Call<Example> listCall = apiTemp.getData();
        listCall.enqueue(new Callback<Example>() {
            String eki;
            char[] bir=new char[41];
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
               // Toast.makeText(view.getContext(),"onResponse ko keldi",Toast.LENGTH_SHORT).show();
                Example examples=response.body();
                celcius.setText(String.valueOf(examples.getCurrent().getTempC())+" C");
                farangate.setText(String.valueOf(examples.getCurrent().getTempF())+" F");
                city.setText(examples.getLocation().getName());
                region.setText(examples.getLocation().getRegion());
                coment.setText(examples.getCurrent().getCondition().getText());
                loctime.setText(examples.getLocation().getLocaltime());
                clouds.setText(String.valueOf(examples.getCurrent().getCloud()));
                wind.setText(String.valueOf(examples.getCurrent().getWindKph()));
                lastupdate.setText(examples.getCurrent().getLastUpdated());
                bir=examples.getCurrent().getCondition().getIcon().toCharArray();
                for(int i=2;i<bir.length;i++){
                    eki+=bir[i];
                }
                Glide.with(view.getContext()).load(eki).into(icon);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(view.getContext(),"kata",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: kata boldu");
            }
        });
        return view;// Inflate the layout for this fragment
    }


}
