package com.example.smartcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.smartcard.R;

import static android.content.ContentValues.TAG;

public class logintab_frag_action extends Fragment {
    EditText username;
    EditText password;
    TextView forgotpass;
    TextView warning;
    Button signin;
    static loginActivity activity;
    float v=0;
    public static final String TAG = "azepq";

    logintab_frag_action(loginActivity la) {
        activity = (loginActivity) la;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.logintab_frag, container, false);

        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);
        forgotpass = root.findViewById(R.id.forgot_pass);
        warning = root.findViewById(R.id.incorrect_cred);
        signin = root.findViewById(R.id.signin);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (uname.length() == 0 || pass.length() == 0) {
                    warning.setText("Each field must be filled");
                }
                else {


                }
            }
        });

//        username.setTranslationX(800);
//        password.setTranslationX(800);
//        forgotpass.setTranslationX(800);
//
//
//        username.setAlpha(v);
//        password.setAlpha(v);
//        forgotpass.setAlpha(v);
//
//        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        forgotpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        return root;
    }

}
