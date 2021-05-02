package com.example.smartcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;

import java.util.Random;


public class loginActivity extends AppCompatActivity {

    EditText username;
    EditText create_password;
    EditText confirm_pass;
    EditText email;
    TextView passnotmatch;
    Button signup;

    TabLayout tabLayout;
    ViewPager viewPager;
    private static final String TAG = "azepq";
    float v=0;

    public Context getContext() {
        return loginActivity.this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //fragment elements
        tabLayout = findViewById(R.id.tab_viewer);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("Log in"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final LonginAdapter adapter = new LonginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        signup_frag_action sfa = new signup_frag_action(loginActivity.this);
        logintab_frag_action ltfa = new logintab_frag_action(loginActivity.this);


    }

    public void saveData(String uname, String pass, String email) {
        Background bg = new Background(loginActivity.this);
        bg.execute(uname, pass, email);
    }

    public void checkData(String uname, String pass) {
        Login_background lbg = new Login_background(loginActivity.this);
        lbg.execute(uname, pass);
    }

    public void openDashboard() {
        Intent intent = new Intent(loginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public int sendMail(String address) {
        try {
            Random r = new Random();
            int otp = r.nextInt(999999-100000) + 100000;
            String subject = "Verification mail";
            String message = "Welcome to Smart Card! \n Enjoy the fast process on the Go! \n Your OTP for Sign Up is: " + otp;

            JavaMailAPI javaMailAPI = new JavaMailAPI(loginActivity.this, address, subject, message);
            javaMailAPI.execute();
            return otp;
        }
        catch (Exception e) {
            Toast.makeText(loginActivity.this, "the problem is: "+e, Toast.LENGTH_LONG).show();
            return -1;
        }
    }


}
