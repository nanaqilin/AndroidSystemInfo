package com.systeminfo.xiaohe.datetime;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity implements DataCallBack{

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Button mExitButton;
    private Button mOKButton;
    private TextView textView;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.util.Log.i(TAG, "my test hello activity");

        textView = (TextView) findViewById(R.id.info_text);
        textView.setText("Product Model: " + android.os.Build.MODEL + "\n"
                + android.os.Build.VERSION.SDK + "\n"
                + android.os.Build.VERSION.RELEASE + "\n"
                + Build.BOARD + "\n"
                + Build.SERIAL + "\n"
                + Build.DEVICE + "\n"
                + Build.PRODUCT + "\n"
                + Build.DISPLAY + "\n"
                + Build.FINGERPRINT + "\n"
        );

        date = new Date();

        mExitButton = (Button) findViewById(R.id.exit_button);
        mOKButton = (Button) findViewById(R.id.ok_button);

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.util.Log.i(TAG, "Exit Button is clicked!");
                finish();
            }
        });

        mOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.util.Log.i(TAG, "OK Button is clicked!");
                FragmentManager manager;
                manager = getSupportFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(date);
//                dialog.setTargetFragment(MainActivity.this,REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        Context context = getApplicationContext();
        CharSequence text = "My Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            android.util.Log.i(TAG, "onActivityResult is no result OK");
            return;
        }
        if(resultCode == REQUEST_DATE){
            android.util.Log.i(TAG, "onActivityResult is REQUEST_DATE");
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            android.util.Log.i(TAG, "Select Date is "+date);
        }

    }

    @Override
    public void getDate(Date data){
        android.util.Log.i(TAG,"getDate "+data);
        date = data;
        textView.setText(data.toString());
    }
}
