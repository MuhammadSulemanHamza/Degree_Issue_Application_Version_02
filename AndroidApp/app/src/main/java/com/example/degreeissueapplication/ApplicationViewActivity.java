package com.example.degreeissueapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.degreeissueapplication.Activities.UpdateActivity;
import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Services.MyInternetService;
import com.example.degreeissueapplication.Utils.DatabaseHandler;

public class ApplicationViewActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MY";
    private DatabaseHandler db;
    private static final int REQ_CODE = 124;
    private String role;
    DegreeIssueModel application;

    private AppCompatTextView textViewDegree;
    private AppCompatTextView textViewSession;
    private AppCompatTextView textViewRollNum;
    private AppCompatTextView textViewBatch;
    private AppCompatTextView textViewDeptt;
    private AppCompatTextView textViewRegNum;
    private AppCompatTextView textViewReason;
    private AppCompatTextView textViewFrom;
    private AppCompatTextView textViewTo;
    private AppCompatTextView textViewCandName;
    private AppCompatTextView textViewCNIC;
    private AppCompatTextView textViewFName;
    private AppCompatTextView textViewCGPA;
    private AppCompatTextView textViewDoB;
    private AppCompatTextView textViewInstitute;
    private AppCompatTextView textViewAddress;
    private AppCompatTextView textViewContact;
    private AppCompatTextView textViewStatus;
    private AppCompatTextView textViewCoRemarks;
    private AppCompatTextView textViewHoDRemarks;

    private AppCompatButton appCompatButtonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_view);

        db = new DatabaseHandler(this);
        initViews();
        setViews();
    }

    private void initViews()
    {
        role = "HoD";
        application = new DegreeIssueModel();
        textViewDegree = (AppCompatTextView) findViewById(R.id.textViewDegree);
        textViewSession = (AppCompatTextView) findViewById(R.id.textViewSession);
        textViewRollNum = (AppCompatTextView) findViewById(R.id.textViewRollNum);
        textViewBatch = (AppCompatTextView) findViewById(R.id.textViewBatch);
        textViewDeptt = (AppCompatTextView) findViewById(R.id.textViewDepartment);
        textViewRegNum = (AppCompatTextView) findViewById(R.id.textViewRegNum);
        textViewReason = (AppCompatTextView) findViewById(R.id.textViewReason);
        textViewFrom = (AppCompatTextView) findViewById(R.id.textViewRevFrom);
        textViewTo = (AppCompatTextView) findViewById(R.id.textViewRevTo);
        textViewCandName = (AppCompatTextView) findViewById(R.id.textViewCandidateName);
        textViewCNIC = (AppCompatTextView) findViewById(R.id.textViewCNIC);
        textViewFName = (AppCompatTextView) findViewById(R.id.textViewFatherName);
        textViewCGPA = (AppCompatTextView) findViewById(R.id.textViewCGPA);
        textViewDoB = (AppCompatTextView) findViewById(R.id.textViewDOB);
        textViewInstitute = (AppCompatTextView) findViewById(R.id.textViewInstitue);
        textViewAddress = (AppCompatTextView) findViewById(R.id.textViewAddress);
        textViewContact = (AppCompatTextView) findViewById(R.id.textViewContact);
        textViewStatus = (AppCompatTextView) findViewById(R.id.textViewStatus);
        textViewCoRemarks = (AppCompatTextView) findViewById(R.id.textViewCoordinatorRemarks);
        textViewHoDRemarks = (AppCompatTextView) findViewById(R.id.textViewHodRemarks);

        appCompatButtonUpdate = (AppCompatButton) findViewById(R.id.appCompatButtonUpdate);
    }

    private void setViews()
    {
        role = (String) getIntent().getSerializableExtra("ROLE");

        if (!role.equals(null))
        {
            if (role.equals("User"))
                appCompatButtonUpdate.setVisibility(View.INVISIBLE);
        }

        application = (DegreeIssueModel) getIntent().getSerializableExtra("FORM");

        if(application != null) {
            textViewDegree.setText(application.getDegree());
            textViewSession.setText(application.getSession());
            textViewRollNum.setText(application.getRollNumber());
            textViewBatch.setText(application.getBatch());
            textViewDeptt.setText(application.getDepartment());
            textViewRegNum.setText(application.getRegNum());
            textViewReason.setText(application.getReason());
            textViewFrom.setText(application.getRev_from());
            textViewTo.setText(application.getRev_to());
            textViewCandName.setText(application.getCandidateName());
            textViewCNIC.setText(application.getCnic());
            textViewFName.setText(application.getFatherName());
            textViewCGPA.setText(application.getCgpa());
            textViewDoB.setText(application.getDob());
            textViewInstitute.setText(application.getInstitute());
            textViewAddress.setText(application.getAddress());
            textViewContact.setText(application.getContact());
            textViewStatus.setText(application.getStatus());
            textViewCoRemarks.setText(application.getCoordinator_remarks());
            textViewHoDRemarks.setText(application.getHod_remarks());
        }

        appCompatButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ApplicationViewActivity.this, UpdateActivity.class);
                startActivityForResult(i,REQ_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK){
                String status = data.getStringExtra("STATUS");

                application.setStatus(status);
                db.updateStatus(application.getId(), status);
                textViewStatus.setText(application.getStatus());

                String remarks = data.getStringExtra("REMARKS");
                Log.i(TAG,"onActivityResult(Status): "+status);
                if (role.equals("HoD")){
                    application.setHod_remarks(remarks);
                    db.updateHodRemarks(application.getId(), remarks);
                    textViewHoDRemarks.setText(application.getHod_remarks());
                    Log.i(TAG,"onActivityResult(HOD): "+remarks);
                }
                else if(role.equals("Coordinator")){
                    application.setCoordinator_remarks(remarks);
                    db.updateCoordinatorRemarks(application.getId(), remarks);
                    textViewCoRemarks.setText(application.getCoordinator_remarks());
                    Log.i(TAG,"onActivityResult(Coordinator): "+remarks);
                }
                //startService(new Intent(getBaseContext(), MyInternetService.class));
            }
        }
    }
}