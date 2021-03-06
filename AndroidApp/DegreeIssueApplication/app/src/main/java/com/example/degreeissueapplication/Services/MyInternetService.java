package com.example.degreeissueapplication.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.degreeissueapplication.JSONhandler.JSONParser;
import com.example.degreeissueapplication.JSONhandler.SendJasonData;
import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Model.UserModel;
import com.example.degreeissueapplication.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MyInternetService extends Service {

    static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String MYTAG = "MYTAG";

    private List<DegreeIssueModel> listApps;
    private List<UserModel> listUsers;
    DatabaseHandler db;

    NotificationManager manager ;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Log.i(MYTAG, "Service Started");
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (CONNECTIVITY_CHANGE_ACTION.equals(action)) {
                    //check internet connection
                    if (!ConnectionHelper.isConnectedOrConnecting(context)) {
                        if (context != null) {
                            boolean show = false;
                            if (ConnectionHelper.lastNoConnectionTs == -1) {//first time
                                show = true;
                                ConnectionHelper.lastNoConnectionTs = System.currentTimeMillis();
                            } else {
                                if (System.currentTimeMillis() - ConnectionHelper.lastNoConnectionTs > 1000) {
                                    show = true;
                                    ConnectionHelper.lastNoConnectionTs = System.currentTimeMillis();
                                }
                            }

                            if (show && ConnectionHelper.isOnline) {
                                ConnectionHelper.isOnline = false;
                                Log.i(MYTAG,"Connection lost");
                                //manager.cancelAll();
                            }
                        }
                    } else {
                        Log.i(MYTAG,"Connected");
                        // Perform your actions here
                        ConnectionHelper.isOnline = true;
                        //new JSONParser().execute();

                        db = new DatabaseHandler(context);

                        listApps = new ArrayList<>();
                        listApps.clear();
                        listApps.addAll(db.getAllApplications());

                        for (int i = 0; i<listApps.size();i++) {
                            if (listApps.get(i).getUpload_status().equals("pending")){
                                try {
                                    new SendJasonData(listApps.get(i), "applications").execute();
                                    listApps.get(i).setUpload_status("uploaded");
                                    db.updateAppUploadStatus(listApps.get(i).getId(), listApps.get(i).getUpload_status());
                                }
                                catch (Exception e){
                                }
                            }
                        }

                        listUsers = new ArrayList<>();
                        listUsers.clear();
                        listUsers.addAll(db.getAllUser());


                        for (int i = 0; i<listUsers.size();i++) {
                            if (listUsers.get(i).getUpload_status().equals("pending")){
                                try {
                                    new SendJasonData(listUsers.get(i), "users").execute();
                                    listUsers.get(i).setUpload_status("uploaded");
                                    db.updateUserUploadStatus(listUsers.get(i).getId(), listUsers.get(i).getUpload_status());
                                }
                                catch (Exception e){
                                }
                            }
                        }
                    }
                }
            }
        };
        registerReceiver(receiver,filter);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        Log.i(MYTAG, "Service DEstroyed");
        startService(new Intent(this, MyInternetService.class));
    }
}