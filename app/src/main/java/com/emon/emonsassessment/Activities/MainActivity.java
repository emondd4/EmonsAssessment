package com.emon.emonsassessment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emon.emonsassessment.R;
import com.emon.emonsassessment.RoomModel.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText SearchEditText;
    private TextView username;
    private Button Search, Profile;
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

        username.setText(sharedPreferences.getString("KEY_USERNAME", null));

        OnClick();
    }

    private void OnClick() {

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String extra = SearchEditText.getText().toString();
                String url = "https://api.tvmaze.com/search/shows?q=" + extra;


                getJSON(url);

//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//
//                                Log.e( "onResponse:", response);
//
//                                try {
//                                    JSONObject jsonObject = new JSONObject(response);
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("onErrorResponse: ", error.getMessage());
//                                //Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//                requestQueue.add(stringRequest);
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

    private void getJSON(String url) {



    }
}