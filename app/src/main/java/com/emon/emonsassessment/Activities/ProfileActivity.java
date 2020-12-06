package com.emon.emonsassessment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.emon.emonsassessment.R;
import com.emon.emonsassessment.RoomData.UserDAO;
import com.emon.emonsassessment.RoomData.UserDataBase;
import com.emon.emonsassessment.RoomModel.User;
import com.google.gson.Gson;

import static java.security.AccessController.getContext;

public class ProfileActivity extends AppCompatActivity {

    private TextView Name,UserName,Phone;
    private Button Back;

    UserDAO dataBase;
    UserDataBase userDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
    }

    private void init() {

        Name = findViewById(R.id.ProfileName);
        UserName = findViewById(R.id.ProfileUserName);
        Phone = findViewById(R.id.ProfilePhone);
        Back = findViewById(R.id.ProfileBack);

        String dummy = getIntent().getStringExtra("username");

        System.out.println(dummy);

        userDataBase = Room.databaseBuilder(ProfileActivity.this, UserDataBase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();

        dataBase = userDataBase.getUserDao();
        User myInfo = dataBase.getUserInfo(dummy);

        Name.setText(myInfo.getName());
        UserName.setText(myInfo.getUserName());
        Phone.setText(myInfo.getPhone());

        OnCLick();
    }

    private void OnCLick() {
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}