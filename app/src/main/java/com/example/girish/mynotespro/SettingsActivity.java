package com.example.girish.mynotespro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class SettingsActivity extends AppCompatActivity {

    private EditText page1_title,page2_title,page3_title,page4_title,page5_title;


    String title1,title2,title3,title4,title5;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);  // TOOLBAR
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");


        page1_title = findViewById(R.id.page1_title);
        page2_title = findViewById(R.id.page2_title);
        page3_title = findViewById(R.id.page3_title);
        page4_title = findViewById(R.id.page4_title);
        page5_title = findViewById(R.id.page5_title);
        aSwitch = findViewById(R.id.switch1);

        Boolean lockonoff = getSharedPreferences("APPLOCK", MODE_PRIVATE)
                .getBoolean("LOCK", true);  // False MIN OFF

        if (lockonoff == true){
            aSwitch.setChecked(true);
        }else {
            aSwitch.setChecked(false);
        }



        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                  //  aSwitch.setText("App lock Off");  //To change the text near to switch
                    //Log.d("You are :", "Checked");
                   // Boolean Lock = getSharedPreferences("APPLOCK", MODE_PRIVATE)
                         //   .getBoolean("LOCK", true);
                    getSharedPreferences("APPLOCK", MODE_PRIVATE).edit()
                            .putBoolean("LOCK", true).commit();
                    Toasty.info(SettingsActivity.this,"App Lock Is No",Toast.LENGTH_SHORT).show();
                }
                else {
                  //  aSwitch.setText("App Lock On");  //To change the text near to switch
                    //Log.d("You are :", " Not Checked");
                    getSharedPreferences("APPLOCK", MODE_PRIVATE).edit()
                            .putBoolean("LOCK", false).commit();
                    Toasty.info(SettingsActivity.this,"App Lock Is Now Off",Toast.LENGTH_SHORT).show();
                }
            }
        });

        lodeTitle();
        fillEditeText();


    }

    private void saveTitle(String title1,String title2,String title3,String title4,String title5){  // SAVEING TITLE NAMES IN SharedPreferences IMPORTEDT
        SharedPreferences mSharedPreferences = getSharedPreferences("TitleName",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("title1",title1);
        mEditor.putString("title2",title2);
        mEditor.putString("title3",title3);
        mEditor.putString("title4",title4);
        mEditor.putString("title5",title5);
        mEditor.apply();
    }

    private void lodeTitle(){  // GETING TITLE FORM SETING ACTVEYT
        SharedPreferences mSharedPreferences = getSharedPreferences("TitleName",MODE_PRIVATE);
        title1 = mSharedPreferences.getString("title1","One");
        title2 = mSharedPreferences.getString("title2","Two");
        title3 = mSharedPreferences.getString("title3","There");
        title4 = mSharedPreferences.getString("title4","Four");
        title5 = mSharedPreferences.getString("title5","Five");
    }

    private void fillEditeText(){
        page1_title.setText(title1);
        page2_title.setText(title2);
        page3_title.setText(title3);
        page4_title.setText(title4);
        page5_title.setText(title5);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // THIS NEED TO APPLEY MENUS TO ACTIVETY
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  // WHAT SHOOUD HAPEEN WHEN LIKED ON ITEM
        switch (item.getItemId()) {

            case R.id.save:
                saveTitle();
                return true;

            default:
                return false;
        }
    }

    public void saveTitle(){
        String title1 = page1_title.getText().toString();
        String title2 = page2_title.getText().toString();
        String title3 = page3_title.getText().toString();
        String title4 = page4_title.getText().toString();
        String title5 = page5_title.getText().toString();

        if (!TextUtils.isEmpty(title1) && !TextUtils.isEmpty(title2) && !TextUtils.isEmpty(title3) &&
                !TextUtils.isEmpty(title4) && !TextUtils.isEmpty(title5)){
            saveTitle(title1,title2,title3,title4,title5);
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(SettingsActivity.this,"YOU Cant",Toast.LENGTH_SHORT).show();
        }
    }
}
