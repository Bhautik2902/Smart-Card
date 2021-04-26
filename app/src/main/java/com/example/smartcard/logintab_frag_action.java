package com.example.smartcard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.smartcard.R;

public class logintab_frag_action extends Fragment {
    EditText username;
    EditText password;
    TextView forgotpass;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.logintab_frag, container, false);

        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);
        forgotpass = root.findViewById(R.id.forgot_pass);

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
