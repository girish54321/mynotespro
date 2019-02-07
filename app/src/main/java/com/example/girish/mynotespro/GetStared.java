package com.example.girish.mynotespro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class GetStared extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Step1
        addFragment(new Step.Builder().setTitle("Welcome To My Notes Pro")
                .setContent("Peres next to continue")
                .setBackgroundColor(Color.parseColor("#708090"))
                .setDrawable(R.drawable.s1)
                .setSummary("Step1")
                .build());
        // Step2
        addFragment(new Step.Builder().setTitle("Save Your Notes")
                .setContent("Make Your Own Menu")
                .setBackgroundColor(Color.parseColor("#708090"))
                .setDrawable(R.drawable.s2)
                .setSummary("Step2")
                .build());
        // Step3
        addFragment(new Step.Builder().setTitle("Security*")
                .setContent("Fingerprint Scanner Will Be Added Automatically")
                .setBackgroundColor(Color.parseColor("#708090"))
                .setDrawable(R.drawable.s3)
                .setSummary("Finish And Go To Settings")
                .build());


    }

    @Override
    public void finishTutorial() {
        // Your implementation

        Intent i = new Intent(GetStared.this,SettingsActivity.class);
        startActivity(i);
        finish();
    }
}
