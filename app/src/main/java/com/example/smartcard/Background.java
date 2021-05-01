 package com.example.smartcard;

 import android.app.AlertDialog;
 import android.content.ContentValues;
 import android.content.Context;
 import android.os.AsyncTask;
 import android.util.Log;
 import android.widget.Toast;

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

 public class Background extends AsyncTask<String, Void, String> {
     AlertDialog dialog;
     Context context;
     public static final String TAG = "PROBLEM";

     public Background(Context context) {
         this.context = context;
     }

     @Override
     protected void onPreExecute() {
         dialog = new AlertDialog.Builder(context).create();
         dialog.setTitle("SignUp Status");
     }

     @Override
     protected void onPostExecute(String s) {
         super.onPostExecute(s);
         dialog.setMessage(s);
         dialog.show();
     }

     @Override
     protected String doInBackground(String... voids) {
         String result = "";
         String user = voids[0];
         String pass = voids[1];
         String email = voids[2];

         Log.v(TAG, "flag1: " + user + " " + pass + " " + email);
         // String connect = "http://192.168.43.95:8080/signup.php";
         String connect = "https://smartcardassociation.000webhostapp.com/signup.php";

         String URLparams = "user=" + user + "&" + "pass=" + pass + "&" + "email=" + email;
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
             result += e;
             Log.v(TAG, "" + e);
         }
         return result;
     }
 }
//        try {
//            URL url = new URL(connect);
//            Log.v(TAG, "flag1: url object done");
//
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            Log.v(TAG, "flag2: url connection done");
//            http.setRequestMethod("POST");
//            Log.v(TAG, "flag3: method set");
//            http.setDoInput(true);
//            Log.v(TAG, "flag4: set do input");
//            http.setDoOutput(true);
//            Log.v(TAG, "flag5: set do output");
//            Log.v(TAG, "flag5: set do output");
//            http.connect();
//            Log.v(TAG, "flag*: created connection");
//            OutputStream ops = null;
//            try {
//                ops = http.getOutputStream();
//            }
//            catch(Exception e) {
//                Log.v(TAG, "error:"+e);
//            }
//            Log.v(TAG, "flag6: ops object created");
//
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
//            Log.v(TAG, "flag7: writer is setted");
//            String data = URLEncoder.encode("user", "UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"+URLEncoder.encode("pass", "UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")
//                    +"&"+URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
//            System.out.println(data);
//            Log.v(TAG, "flag5: url data: " + data);
//
//            writer.write(data);
//            writer.flush();
//            writer.close();
//            ops.close();
//
//            InputStream ips = http.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
//            Log.v(TAG, "flag6: Input reader is ready to read");
//            result = reader.readLine();
////            String line = "";
////            while (reader.readLine() != null) {
////                line = reader.readLine();
////                result += line;
////            }
////            reader.close();
////            ips.close();
////            http.disconnect();
//
//
//        } catch (MalformedURLException e) {
//            result = e.getMessage();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.v(TAG, ""+result+"helloGoog");
//        return result;
 //}


