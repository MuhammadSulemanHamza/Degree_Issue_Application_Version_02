package com.example.degreeissueapplication.JSONhandler;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class SendJasonData extends AsyncTask <String, Void, String> {

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0) {

        try {

            URL url = new URL("http://192.168.10.7:8000/api/add"); // here is your URL path

            JSONObject jsonObject = new JSONObject();
            /*jsonObject.put("degree", "Muhammad");
            jsonObject.put("session", "CRYPT to MAD");
            jsonObject.put("rollNumber"=>"17271519-032",);
            jsonObject.put("batch"=>"2017",);
            jsonObject.put("department"=>"CS",);
            jsonObject.put("registrationNum"=>"123141213",);
            jsonObject.put("reason"=>"BSSSSSSSSSSSSS",);
            jsonObject.put("rev_from"=>"N/A",);
            jsonObject.put("rev_to"=>"N/A",);
            jsonObject.put("candidateName"=>"Muhammad",);
            jsonObject.put("cnic"=>"34201", );
            jsonObject.put("fatherName"=>"Muhammad", );
            jsonObject.put("cgpa"=>"Allhamdullilah! 3.5 ", );
            jsonObject.put("dob"=>"16-08-1998", );
            jsonObject.put("institute"=>"UOG", );
            jsonObject.put("address"=>"@gmail.com",);
            jsonObject.put("contact"=>"03044335338", );
            jsonObject.put("status"=>"Accepted", );
            jsonObject.put("coRemarks"=>"Good", );
            jsonObject.put("hodRemarkds"=>"Well");*/
            Log.e("params",jsonObject.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(jsonObject));

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            }
            else {
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {
        Log.e("response", result);
    }


    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}
