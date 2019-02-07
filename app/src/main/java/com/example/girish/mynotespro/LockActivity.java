package com.example.girish.mynotespro;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LockActivity extends AppCompatActivity {

    private TextView mHadingLable;
    private ImageView mFingerprintImage;
    private TextView mParaLable;
    private EditText pin;
    private Button go;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        mHadingLable = (TextView) findViewById(R.id.headingLable);
        mFingerprintImage = (ImageView) findViewById(R.id.fingerprintimage);
        mParaLable = (TextView) findViewById(R.id.paralable);

        pin = (EditText) findViewById(R.id.pin);
        go = (Button) findViewById(R.id.go);



        //---------------------------------------------------------------------------

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity
            Intent i = new Intent(LockActivity.this,GetStared.class);
            startActivity(i);
            finish();

            Toast.makeText(LockActivity.this, "Welcome", Toast.LENGTH_LONG)
                    .show();
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


        //---------------------------------------------------------------------------

        Boolean lockonoff = getSharedPreferences("APPLOCK", MODE_PRIVATE)
           .getBoolean("LOCK", true);

        if (lockonoff == false){
            Intent i = new Intent(LockActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pinpass = pin.getText().toString();

                if (!pinpass.isEmpty()) {

                    Intent i = new Intent(LockActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(LockActivity.this, "Enter Your Pin", Toast.LENGTH_LONG).show();
                }
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {    // THIS NEEDED TO CHEK THAT USERS PHONE WIL SAPPOEERT THIS OR NOT

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {

               // mParaLable.setText("Use PIN to Login");
               // pin.setVisibility(View.VISIBLE);
               // go.setVisibility(View.VISIBLE);
               // mFingerprintImage.setVisibility(View.VISIBLE);
                Intent i = new Intent(LockActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

                mParaLable.setText("Permission Given for Fingerprint Scaner");

            } else if (!keyguardManager.isKeyguardSecure()) {

               // mParaLable.setText("PLz lock you phone or mobile");
                //pin.setVisibility(View.VISIBLE);
               // go.setVisibility(View.VISIBLE);
               // mFingerprintImage.setVisibility(View.VISIBLE);
                Intent i = new Intent(LockActivity.this,MainActivity.class);
                startActivity(i);
                finish();


            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
               // mParaLable.setText("you shoud add 1 finrse go you App");
               // pin.setVisibility(View.VISIBLE);
                //go.setVisibility(View.VISIBLE);
                //mFingerprintImage.setVisibility(View.VISIBLE);
                Intent i = new Intent(LockActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            } else {
                mParaLable.setText("Place Your Finger");

                FigerprintHadler figerprintHadler = new FigerprintHadler(this);
                figerprintHadler.startAuth(fingerprintManager, null);
            }
        }
    }
}



