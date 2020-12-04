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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.emon.emonsassessment.Interface.FragmentChangeListener;
import com.emon.emonsassessment.R;
import com.emon.emonsassessment.RoomData.UserDAO;
import com.emon.emonsassessment.RoomData.UserDataBase;
import com.emon.emonsassessment.RoomModel.User;

public class SignUpFragment extends Fragment implements FragmentChangeListener {

    private TextView SignIn;
    private EditText Name,UserName,Password,Phone;
    private Button Register;

    private UserDAO userDAO;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        SignIn = view.findViewById(R.id.signInTextView);
        Name = view.findViewById(R.id.signUpNameText);
        UserName = view.findViewById(R.id.signUpUserNameText);
        Password = view.findViewById(R.id.signUpPassText);
        Phone = view.findViewById(R.id.signUpPhone);
        Register = view.findViewById(R.id.SignUpButton);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);

        Toolbar toolbar = view.findViewById(R.id.signUpToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SignInFragment());
            }
        });

        userDAO = Room.databaseBuilder(getContext(), UserDataBase.class, "mi-database.db").allowMainThreadQueries()
                .build().getUserDao();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                String name = Name.getText().toString().trim();
                String username = UserName.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String phone = Phone.getText().toString().trim();

                if (!name.equals("")){
                    if (!username.equals("")){
                        if (!password.equals("")){
                            if (!phone.equals("")){

                                User user = new User(name,username,password,phone);
                                userDAO.insert(user);
                                Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                replaceFragment(new SignInFragment());

                            }else {
                                progressDialog.dismiss();
                                Phone.setError("Phone Is Empty");
                            }
                        }else {
                            progressDialog.dismiss();
                            Password.setError("Password Is Empty");
                        }
                    }else {
                        progressDialog.dismiss();
                        UserName.setError("UserName Is Empty");
                    }
                }else {
                    progressDialog.dismiss();
                    Name.setError("Name Is Empty");
                }
            }
        });


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new SignInFragment());
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
}