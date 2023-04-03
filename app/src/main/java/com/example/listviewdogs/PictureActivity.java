package com.example.listviewdogs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ImageView picture = findViewById(R.id.picture);
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();

        picture.setImageResource(Integer.parseInt(bundle.getString("PICTURE_IMAGE")));
        name.setText(bundle.getString("PICTURE_NAME"));
        description.setText(bundle.getString("PICTURE_DESC"));
    }
}