package com.emon.emonsassessment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emon.emonsassessment.R;

import java.util.function.Predicate;

public class ItemDetailsActivity extends AppCompatActivity {

    private TextView ID,URL,TYPE,LANGUAGE,STATUS,PREMIERED;
    private Button BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        init();

    }

    private void init() {
        ID = findViewById(R.id.details_id);
        URL = findViewById(R.id.details_url);
        TYPE = findViewById(R.id.details_type);
        LANGUAGE = findViewById(R.id.details_language);
        STATUS = findViewById(R.id.details_status);
        PREMIERED = findViewById(R.id.details_premiered);

        BACK = findViewById(R.id.DetailsBack);

        String id = getIntent().getStringExtra("id");
        String url = getIntent().getStringExtra("url");
        String type = getIntent().getStringExtra("type");
        String language = getIntent().getStringExtra("language");
        String status = getIntent().getStringExtra("status");
        String premiered = getIntent().getStringExtra("premiered");

        ID.setText("Id : " + id);
        URL.setText("Url : " + url);
        TYPE.setText("Type : " + type);
        LANGUAGE.setText("Language : " + language);
        STATUS.setText("Status : " + status);
        PREMIERED.setText("Premiered : "  + premiered);

        OnClick();
    }

    private void OnClick() {
        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemDetailsActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ItemDetailsActivity.this,MainActivity.class));
        finish();
    }
}