package com.emon.emonsassessment.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emon.emonsassessment.Activities.MainActivity;
import com.emon.emonsassessment.Interface.FragmentChangeListener;
import com.emon.emonsassessment.R;
import com.emon.emonsassessment.RoomData.UserDAO;
import com.emon.emonsassessment.RoomData.UserDataBase;
import com.emon.emonsassessment.RoomModel.User;

public class SignInFragment extends Fragment implements FragmentChangeListener {

    private TextView SignUp;
    private EditText UserName,Password;
    private Button Signin;
    private ProgressDialog progressDialog;

    UserDAO dataBase;
    UserDataBase userDataBase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        SignUp = view.findViewById(R.id.signUpTextView);
        UserName = view.findViewById(R.id.signInUserName);
        Password = view.findViewById(R.id.signInPassText);
        Signin = view.findViewById(R.id.signInButton);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);

        userDataBase = Room.databaseBuilder(getContext(), UserDataBase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();

        dataBase = userDataBase.getUserDao();

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = UserName.getText().toString().trim();
                String password = Password.getText().toString().trim();

                User user = dataBase.getUser(userName,password);
                if (user != null){
                    startActivity(new Intent(getContext(),MainActivity.class));
                }else {
                    Toast.makeText(getContext(), "User Not Found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new SignUpFragment());
            }
        });
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AuthActivityFrame,fragment);
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}