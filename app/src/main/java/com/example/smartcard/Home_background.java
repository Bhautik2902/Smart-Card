package com.example.smartcard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.smartcard.ui.home.HomeFragment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static android.content.ContentValues.TAG;

public class Home_background extends AsyncTask<String, Void, String> {
    AlertDialog dialog;
    Context context;

    //store username in global variable to show specific data of user on dashboard. pass this data to openDashboard() method.
    static HomeFragment activity;
    TextView pi;
    TextView vi;
    TextView verification;

    public static final String TAG = "PROBLEM";
    private ProgressDialog mProgressDialog;

    public Home_background(Context context) {
        this.context = context;
    }
    public Home_background(HomeFragment activity, TextView pi, TextView vi, TextView verification) {
        this.activity = (HomeFragment) activity;
        this.pi = pi;
        this.vi = vi;
        this.verification = verification;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(context,"Processing", "Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressDialog.dismiss();
        s = s.trim();
        try {
            if (s.equals("P_found")){
               activity.setStatus(true);
            } else {
                activity.setStatus(false);
            }
        }
        catch (Exception e) {
            Log.v(TAG, "IN home bg" + e);
        }
        Toast.makeText(context, "Status: " + s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String user_name = voids[0];

        // String connect = "http://192.168.43.95:8080/signup.php";
        String connect = "https://smartcardassociation.000webhostapp.com/checkstatus.php";

        String URLparams = "user_name="+user_name;
        byte[] postData = URLparams.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        try {
            URL url = new URL(connect);
            Log.v(TAG, "url connected");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            Log.v(TAG, "http connected");
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setRequestProperty("charset", "utf-8");
            http.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            http.setUseCaches(false);

            OutputStreamWriter wr = new OutputStreamWriter(http.getOutputStream());
            Log.v(TAG, "writer connected");

            wr.write(URLparams);
            wr.flush();
            Log.v(TAG, "data sent.");

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            Log.v(TAG, "reader connected");
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            wr.close();
            reader.close();

        } catch (MalformedURLException e) {
            result += e;
            Log.v(TAG, "" + e);
        } catch (IOException e) {
            result += e;
            Log.v(TAG, "" + e);
        } catch (Exception e) {
            result += e ;
            Log.v(TAG, "" + e);
        }
        return result;
    }
}