package com.example.myapp20200629;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       Animal animal = (Animal)getIntent().getSerializableExtra("Animal");
       ImageView img = findViewById(R.id.detail_img);
        TextView placeText = findViewById(R.id.detail_place);
        TextView kindText = findViewById(R.id.detail_kind);
        TextView colour = findViewById(R.id.detail_colour);
        placeText.setText(animal.getPlace());
        kindText.setText(animal.getKind());
        colour.setText(animal.getColour());
        Picasso.get().load(animal.getImg()).into(img);

    }
}
