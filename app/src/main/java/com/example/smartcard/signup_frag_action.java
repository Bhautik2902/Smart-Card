package com.example.smartcard;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Random;

import static android.content.ContentValues.TAG;

public class signup_frag_action extends Fragment {
    EditText username;
    EditText create_password;
    EditText confirm_pass;
    EditText email;
    EditText otpval;
    TextView passnotmatch;
    Button signup;
    Button sendOTP;
    int OTP=-1;
    Context context;
    static loginActivity activity;

    float v=0;
    private static final String TAG = "azepq";

    public signup_frag_action(loginActivity act) {
        activity = (loginActivity) act;
    }
    public signup_frag_action() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_frag, container, false);

        username = root.findViewById(R.id.username_sup);
        create_password = root.findViewById(R.id.create_password_sup);
        confirm_pass = root.findViewById(R.id.cnfm_password_sup);
        email = root.findViewById(R.id.email_sup);
        otpval = root.findViewById(R.id.otp);
        signup = root.findViewById(R.id.signup);
        sendOTP = root.findViewById(R.id.send_otp);
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
                String uname = username.getText().toString();
                String createpass = create_password.getText().toString();
                String confirmpass = confirm_pass.getText().toString();
                String email_add = email.getText().toString().trim();
                String otp_str = otpval.getText().toString().trim();


                try {
                    if (uname.length() == 0 || createpass.length() == 0 || confirmpass.length() == 0 || email_add.length() == 0 || otp_str.length() == 0 ) {
                        passnotmatch.setText("Each field must be filled");
                    } else {
                        if (!createpass.equals(confirmpass)) {
                            passnotmatch.setText("Password didn't match. Try again!");
                        } else {
                            passnotmatch.setText("");
                            // if password match, send verification mail.
                            int otp_number = Integer.parseInt(otp_str);
                            if (OTP > 0 && OTP == otp_number) {
                                int hashed_pass = createpass.hashCode();
                                createpass = hashed_pass+"";
                                activity.saveData(uname, createpass, email_add);
                            }
                            else if(OTP != otp_number) {
                                passnotmatch.setText("You have entered wrong OTP.");
                            }
                            else {
                                passnotmatch.setText("Click verify first!");
                            }
                        }
                    }
                }
                catch (Exception e) {
                    Log.v(TAG, ""+e);
                }

            }
        });

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_add = email.getText().toString().trim();
                if (email_add.length() > 8) {
                    OTP = activity.sendMail(email_add);
                }
            }
        });

        return root;
    }


}
