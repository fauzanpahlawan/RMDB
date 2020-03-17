package com.example.zmdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class AboutActivity extends AppCompatActivity {
    private ImageView imgMyPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About Me");
        setContentView(R.layout.activity_profile);
        imgMyPhoto = findViewById(R.id.img_my_picture);
        TextView tvMyName = findViewById(R.id.tv_my_name);
        TextView tvMyEmail = findViewById(R.id.tv_my_email);
        tvMyName.setText(R.string.my_name);
        tvMyEmail.setText(R.string.my_email);
        int myPicture = R.drawable.fauzan_pahlawan;
        Glide.with(this)
                .load(myPicture)
                .into(imgMyPhoto);
    }
}
