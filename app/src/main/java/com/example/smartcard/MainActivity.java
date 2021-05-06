
package com.example.smartcard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcard.ui.gallery.PersonalDetailsFragment;
import com.example.smartcard.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PROBLEM";
    private AppBarConfiguration mAppBarConfiguration;
    View hView;
    TextView user_name;
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // set user name in header.
        hView = navigationView.getHeaderView(0);
        user_name = (TextView) hView.findViewById(R.id.tv_username);
        if(getIntent().getExtras() != null)
        {
            username = getIntent().getStringExtra("User");
            user_name.setText(username);
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //create object of fragment class and passing activity.
        try {
            PersonalDetailsFragment pdf = new PersonalDetailsFragment(MainActivity.this);
            HomeFragment hf = new HomeFragment(MainActivity.this);
        }
        catch(Exception e) {
            Log.v(TAG, "in object creation "+e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void showData(String name, String email, String phone, String dob, String address, String State, String pin, String gender) {
        personalInfo_background pib = new personalInfo_background(MainActivity.this);
        pib.execute(username, name, email, phone, dob, address, State,pin, gender);
    }

    public void checkStatus() {
        Home_background hb = new Home_background(MainActivity.this);
        hb.execute(username);
    }

    public String getUsername() {
        return username;
    }
}