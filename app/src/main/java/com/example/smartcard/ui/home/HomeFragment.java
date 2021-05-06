package com.example.smartcard.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcard.Home_background;
import com.example.smartcard.MainActivity;
import com.example.smartcard.R;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    static MainActivity activity;
    TextView pis; //personal info. status
    TextView vis; //vehicle info. status
    TextView vs;  //verification status
    TextView username;
    String user = "";

    private static final String TAG = "PROBLEM";

    public HomeFragment(MainActivity ma) {
        activity = (MainActivity) ma;
    }
    public HomeFragment() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
           //     textView.setText(s);
            }
        });
        Home_background hb = new Home_background(HomeFragment.this, pis, vis, vs);
        try {
            pis = root.findViewById(R.id.added_personal_info);
            vis = root.findViewById(R.id.added_vehicle_info);
            vs = root.findViewById(R.id.verification_status);
            username = root.findViewById(R.id.name);

            user = activity.getUsername();
            username.setText(user);
            activity.checkStatus();
        }
        catch (Exception e) {
            Log.v(TAG, "in Homefragent" + e);
        }

        return root;
    }

    public void setStatus(boolean bool) {
        if (bool == true) {
            pis.setText("Added");
            pis.setTextColor(0xFF0D6A34);
        }
        else {
            pis.setText("Required");
            pis.setTextColor(0xFFD50000);
        }
    }
}