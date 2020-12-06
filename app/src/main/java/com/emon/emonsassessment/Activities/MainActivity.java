package com.emon.emonsassessment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emon.emonsassessment.ApiAdapter;
import com.emon.emonsassessment.R;
import com.emon.emonsassessment.Post;
import com.emon.emonsassessment.RoomModel.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText SearchEditText;
    private TextView username;
    private Button Search, Profile;
    private RecyclerView recyclerView;
    private ApiAdapter apiAdapter;
    private User my_info;

    private SharedPreferences sharedPreferences;

    public static final String SHARED_PREF_NAME = "mypref";
    private RequestQueue requestQueue;
    private Gson gson;
    private String extra,url;


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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        username.setText(sharedPreferences.getString("KEY_USERNAME", null));

        OnClick();
    }

    private void OnClick() {

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                extra = SearchEditText.getText().toString();
                url = "https://api.tvmaze.com/search/shows?q=" + extra;
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                gson = gsonBuilder.create();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.i("PostActivity", response);

                                List<Post> posts = Arrays.asList(gson.fromJson(response, Post[].class));

                                ArrayList<Post> list = new ArrayList<>(posts);

                                apiAdapter = new ApiAdapter(list,MainActivity.this);
                                recyclerView.setAdapter(apiAdapter);
                                apiAdapter.notifyDataSetChanged();

                                Log.i("PostActivity", posts.size() + " posts loaded.");
                                for (Post apiModel : posts) {
                                    Log.i("PostActivity", apiModel.getScore() + ": " + apiModel.getShow().getId());
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("PostActivityError", error.toString());
                            }
                        });

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                i.putExtra("username", username.getText().toString());
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}