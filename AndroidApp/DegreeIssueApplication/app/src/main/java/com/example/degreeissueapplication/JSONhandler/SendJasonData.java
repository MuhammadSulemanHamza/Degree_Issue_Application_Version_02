package com.example.degreeissueapplication.JSONhandler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.degreeissueapplication.MainActivity;
import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Model.UserModel;
import com.example.degreeissueapplication.Utils.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class SendJasonData extends AsyncTask <String, Void, String> {


    DegreeIssueModel app;
    String table;
    UserModel user;

    public SendJasonData(DegreeIssueModel app, String table) {
        this.app = app;
        this.table = table;
    }
    public SendJasonData(UserModel user, String table) {
        this.user = user;
        this.table = table;
    }


    protected void onPreExecute(){}

    protected String doInBackground(String... arg0) {

        try {
            JSONObject jsonObject = new JSONObject();

            URL url = new URL("http://192.168.10.7:8000/api/add"); // here is your URL path

            if (table.equals("applications")) {
                url = new URL("http://192.168.10.7:8000/api/add"); // here is your URL path
                jsonObject = new JSONObject();
                jsonObject.put("degree", app.getDegree());
                jsonObject.put("session", app.getSession());
                jsonObject.put("rollNumber", app.getRollNumber());
                jsonObject.put("batch", app.getBatch());
                jsonObject.put("department", app.getDepartment());
                jsonObject.put("registrationNum", app.getRegNum());
                jsonObject.put("reason", app.getReason());
                jsonObject.put("rev_from", app.getRev_from());
                jsonObject.put("rev_to", app.getRev_to());
                jsonObject.put("candidateName", app.getDegree());
                jsonObject.put("cnic", app.getCnic());
                jsonObject.put("fatherName", app.getFatherName());
                jsonObject.put("cgpa", app.getCgpa());
                jsonObject.put("dob", app.getDob());
                jsonObject.put("institute", app.getInstitute());
                jsonObject.put("address", app.getAddress());
                jsonObject.put("contact", app.getContact());
                jsonObject.put("status", app.getStatus());
                jsonObject.put("coRemarks", app.getCoordinator_remarks());
                jsonObject.put("hodRemarkds", app.getHod_remarks());
                Log.e("params", jsonObject.toString());
            }
            else if (table.equals("users")){
                url = new URL("http://192.168.10.7:8000/api/add_user"); // here is your URL path
                jsonObject = new JSONObject();
                jsonObject.put("user_name", user.getName());
                jsonObject.put("user_email", user.getEmail());
                jsonObject.put("user_password", user.getPassword());
                jsonObject.put("user_role", user.getRole());
            }
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
