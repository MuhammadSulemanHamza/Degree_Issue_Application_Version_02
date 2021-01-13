package com.example.degreeissueapplication.JSONhandler;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser extends AsyncTask<Void, Void, Void> {

    private final String TAG = "MyTAG";
    @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG,"onPreExecute");

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://192.168.10.7:8000/api/list/";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray apps = jsonObj.getJSONArray("applications");

                    // looping through All Contacts
                    for (int i = 0; i < apps.length(); i++) {
                        JSONObject c = apps.getJSONObject(i);
                        String id = c.getString("id");
                        Log.e(TAG, id);
                        String name = c.getString("degree");
                        Log.e(TAG, name);
                        String detail = c.getString("session");
                        Log.e(TAG, detail);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
}