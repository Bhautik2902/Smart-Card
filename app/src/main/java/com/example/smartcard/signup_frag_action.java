package com.example.smartcard;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class signup_frag_action extends Fragment {
    EditText username;
    EditText create_password;
    EditText confirm_pass;
    EditText email;
    TextView passnotmatch;
    Button signup;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_frag, container, false);

        username = root.findViewById(R.id.username_sup);
        create_password = root.findViewById(R.id.create_password_sup);
        confirm_pass = root.findViewById(R.id.cnfm_password_sup);
        email = root.findViewById(R.id.email_sup);
        signup = root.findViewById(R.id.signup);
        passnotmatch = root.findViewById(R.id.pass_not_match);

//        username.setTranslationX(800);
//        create_password.setTranslationX(800);
//        confirm_pass.setTranslationX(800);
//        email.setTranslationX(800);
//
//        username.setAlpha(v);
//        create_password.setAlpha(v);
//        confirm_pass.setAlpha(v);
//        email.setAlpha(v);
//
//        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        create_password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        confirm_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String createpass = create_password.getText().toString();
                String confirmpass = confirm_pass.getText().toString();

                if (!createpass.equals(confirmpass)) {
                    passnotmatch.setText("Password didn't match. Try again!");
                }
                else {
                    passnotmatch.setText("");
                }


            }
        });
        return root;
    }


}
