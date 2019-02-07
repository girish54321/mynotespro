package com.example.girish.mynotespro;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)

class FigerprintHadler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    public FigerprintHadler(Context context){

        this.context = context;

    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();

        fingerprintManager.authenticate(cryptoObject, cancellationSignal,0,this,null );
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was Auth wala Error"+ errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("No Match :-|",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update(""+helpString, false);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Loding..",true);
    }

    private void update(String s, boolean b) {

        TextView paraLable = (TextView) ((Activity)context).findViewById(R.id.paralable);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.fingerprintimage);

        paraLable.setText(s);

        if (b == false){
            paraLable.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {
            paraLable.setTextColor(ContextCompat.getColor(context, R.color.black));
           // imageView.setImageResource(R.drawable.ic_fingerprint_white);


            Intent i = new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            ((Activity) context).finish();

        }
    }
}