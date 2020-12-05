package com.emon.emonsassessment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emon.emonsassessment.R;
import com.emon.emonsassessment.RoomModel.User;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText SearchEditText;
    private TextView username;
    private Button Search,Profile;
    private RecyclerView recyclerView;
    private User my_info;

    private SharedPreferences sharedPreferences;

    public static final String SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        my_info = (User) getIntent().getSerializableExtra("userInfo");

        SearchEditText = findViewById(R.id.SearchText);
        username = findViewById(R.id.MainUserName);
        Search = findViewById(R.id.SearchButton);
        Profile = findViewById(R.id.ProfileButton);
        recyclerView = findViewById(R.id.RecyclerView);

        username.setText(sharedPreferences.getString("KEY_USERNAME",null));

        OnClick();
    }

    private void OnClick() {

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ProfileActivity.class);
                i.putExtra("username",username.getText().toString());
                startActivity(i);
            }
        });

    }
}