package com.example.myapp20200629;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Content extends AppCompatActivity {

    private static final String TAG = Content.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //RecycleView
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //https://atm201605.appspot.com/h
        //https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String json = response.body().string();
                Log.d(TAG, "onResponse: " + json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJSON(json);
                    }
                });

            }
        });
    }

    private void parseJSON(String json) {
        animals = new ArrayList<>();
        try {
            JSONArray array =new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Animal animal = new Animal(object);
                animals.add(animal);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TransAdapter adapter = new TransAdapter();
        recyclerView.setAdapter(adapter);
    }

    public class TransAdapter extends RecyclerView.Adapter<TransAdapter.TransHolder> {

        @NonNull
        @Override
        public TransHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(
                    R.layout.item_transactions,parent,false);
            return new TransHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TransHolder holder, int position) {
            final Animal animal = animals.get(position);
            holder.bindTo(animal);
            String img = animal.getImg();
            if(img.length() == 0){
                Picasso.get().load("http://i.imgur.com/DvpvklR.png").resize(150,150).centerCrop().into(holder.imgText);
            }else{
                Picasso.get().load(img).resize(150,150).centerCrop().into(holder.imgText);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: "+animal.getSubid());
                    Intent intent = new Intent(Content.this ,Detail.class);
                    intent.setClass(Content.this,Detail.class);
                    intent.putExtra("Animal",(Serializable) animal);
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class TransHolder extends RecyclerView.ViewHolder {
            TextView idText ;
            TextView placeText;
            ImageView imgText;
            public TransHolder(@NonNull View itemView) {
                super(itemView);
                idText = itemView.findViewById(R.id.item_id);
                placeText = itemView.findViewById(R.id.item_place);
                imgText = itemView.findViewById(R.id.item_img);
            }

            public void bindTo(Animal animal) {
                idText.setText(animal.getSubid());
                placeText.setText(animal.getPlace());
            }


        }
    }
}
