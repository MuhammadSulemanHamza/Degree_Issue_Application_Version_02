package com.example.degreeissueapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.degreeissueapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TAG_MY";
    private TextInputLayout textInputLayoutRemarks;

    private TextInputEditText textInputEditTextRemarks;

    private Spinner spinnerRemarks;

    private ArrayAdapter<CharSequence> adapterRemarks;

    private AppCompatButton appCompatButtonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        spinnerRemarks = (Spinner) findViewById(R.id.remarks_spinner);
        adapterRemarks = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterRemarks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerRemarks.setAdapter(adapterRemarks);

        textInputLayoutRemarks = (TextInputLayout) findViewById(R.id.textInputLayoutRemarks);

        textInputEditTextRemarks = (TextInputEditText) findViewById(R.id.textInputEditTextRemarks);

        appCompatButtonUpdate = (AppCompatButton) findViewById(R.id.appCompatButtonUpdate);

        appCompatButtonUpdate.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        String status = (spinnerRemarks.getSelectedItem().toString());
        String remarks = (textInputEditTextRemarks.getText().toString());

        Intent intent = getIntent();
        intent.putExtra("STATUS", status);
        intent.putExtra("REMARKS", remarks);
        setResult(RESULT_OK, intent);
        Log.i(TAG, "status: "+status);
        finish();

    }
}