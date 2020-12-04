package com.emon.emonsassessment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.emon.emonsassessment.Fragments.SignInFragment;
import com.emon.emonsassessment.R;

public class AuthActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    private FragmentManager fm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        fm = getSupportFragmentManager();
        frameLayout = findViewById(R.id.AuthActivityFrame);
        setDefaultFragment(new SignInFragment());
    }

    private void setDefaultFragment(SignInFragment signInFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),signInFragment);
        fragmentTransaction.commit();
    }

}