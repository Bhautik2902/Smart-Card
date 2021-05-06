package com.example.smartcard.ui.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcard.MainActivity;
import com.example.smartcard.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalDetailsFragment extends Fragment {

    private static final String TAG = "PROBLEM";
    private GalleryViewModel galleryViewModel;
    EditText fullname;
    EditText email;
    EditText phoneno;
    EditText DOB;
    EditText Address;
    EditText state;
    EditText pincode;
    Button submit;
    TextView warning;
    RadioGroup radioGroup;
    RadioButton radioButton;
    static MainActivity activity;

    public PersonalDetailsFragment(MainActivity ma) {
        activity = (MainActivity) ma;
    }
    public PersonalDetailsFragment() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personal_details, container, false);
       // final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        try {
            fullname = root.findViewById(R.id.full_name);
            email = root.findViewById(R.id.emailaddress);
            phoneno = root.findViewById(R.id.phone);
            submit = root.findViewById(R.id.submit);
            DOB = root.findViewById(R.id.birth_date);
            Address = root.findViewById(R.id.address);
            state = root.findViewById(R.id.state);
            pincode = root.findViewById(R.id.pincode);
            radioGroup = root.findViewById(R.id.radioGroup);
            warning = root.findViewById(R.id.warning);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = fullname.getText().toString().trim();
                    String mail = email.getText().toString().trim();
                    String phone_no = phoneno.getText().toString().trim();
                    String dob = DOB.getText().toString().trim();
                    String address = Address.getText().toString().trim();
                    String State = state.getText().toString().trim();
                    String pin = pincode.getText().toString().trim();

                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = root.findViewById(radioId);
                    String gender = (String) radioButton.getText();

                    if(name.length()==0 || mail.length()==0 || phone_no.length()==0 || dob.length()==0 || address.length()==0 || State.length()==0 || pin.length()==0) {
                        warning.setText("Each field must be filled!");
                    }
                    else {
                        activity.showData(name, mail, phone_no, dob, address, State, pin, gender);
                        Log.v(TAG, name + mail + phone_no + " " + gender);
                    }

                }
            });
        }
        catch (Exception e) {
            Log.v(TAG, "personaldetail"+ e);
        }
        return root;
    }


}