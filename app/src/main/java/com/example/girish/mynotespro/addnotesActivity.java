package com.example.girish.mynotespro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.girish.mynotespro.Modle.Page2Database;
import com.example.girish.mynotespro.Modle.Page3Database;
import com.example.girish.mynotespro.Modle.Page4Database;
import com.example.girish.mynotespro.Modle.Page5Database;
import com.example.girish.mynotespro.Modle.PageDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class addnotesActivity extends AppCompatActivity {

    EditText editTexttitle;
    EditText editTextnotes;


    //DATABASE REFRENSE
    PageDatabaseHelper db;
    Page2Database page2Database;
    Page3Database page3Database;
    Page4Database page4Database;
    Page5Database page5Database;

    Spinner spinnerSelect;
    String selectitem;
    String title1,title2,title3,title4,title5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);

        Toolbar toolbar = findViewById(R.id.toolbar);  // TOOLBAR
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titles();

        //DATABASES
        db = new PageDatabaseHelper(this);
        page2Database = new Page2Database(this);
        page3Database = new Page3Database(this);
        page4Database = new Page4Database(this);
        page5Database = new Page5Database(this);

        //Spener
        spinnerSelect = findViewById(R.id.spinner);
        List<String> list = new ArrayList<>();
        list.add(title1);
        list.add(title2);
        list.add(title3);
        list.add(title4);
        list.add(title5);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSelect.setAdapter(adapter);
        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectitem = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        editTexttitle = findViewById(R.id.addtitle_text);
        editTextnotes = findViewById(R.id.addnotes_text);



    }
    public void addNote() {

        String title = editTexttitle.getText().toString().toUpperCase();
        String notes = editTextnotes.getText().toString();
        if (selectitem == title1) {  // ADD NOTES TO PAGE 1

            if (!title.equals("") && db.insertData(title, notes)) {
                Toasty.success(addnotesActivity.this, "Notes Are Saved Into "+title1, Toast.LENGTH_LONG).show();
                editTexttitle.setText("");
                editTextnotes.setText("");
                finish();

            } else {
                Toasty.error(addnotesActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();

            }
        }else if (selectitem == title2){// ADD NOTES TO PAGE 2

            if (!title.equals("") && page2Database.insertData(title, notes)) {
                Toasty.success(addnotesActivity.this, "Notes Are Saved Into "+title2, Toast.LENGTH_LONG).show();
                editTexttitle.setText("");
                editTextnotes.setText("");
                finish();

            } else {
                Toasty.error(addnotesActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();

            }

        }else if (selectitem == title3){// ADD NOTES TO PAGE 3

            if (!title.equals("") && page3Database.insertData(title, notes)) {
                Toasty.success(addnotesActivity.this, "Notes Are Saved Into "+title3, Toast.LENGTH_LONG).show();
                editTexttitle.setText("");
                editTextnotes.setText("");
                finish();

            } else {
                Toasty.error(addnotesActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();

            }

        }else if (selectitem == title4){// ADD NOTES TO PAGE 4

            if (!title.equals("") && page4Database.insertData(title, notes)) {
                Toasty.success(addnotesActivity.this, "Notes Are Saved Into "+title4, Toast.LENGTH_LONG).show();
                editTexttitle.setText("");
                editTextnotes.setText("");
                finish();

            } else {
                Toasty.error(addnotesActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();

            }

        }else if (selectitem == title5){// ADD NOTES TO PAGE 5

            if (!title.equals("") && page5Database.insertData(title, notes)) {
                Toasty.success(addnotesActivity.this, "Notes Are Saved Into "+title5, Toast.LENGTH_LONG).show();
                editTexttitle.setText("");
                editTextnotes.setText("");
                finish();

            } else {
                Toasty.error(addnotesActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();

            }

        }
    }

    private void titles(){  // GETING TITLE FORM SETING ACTVEYT
        SharedPreferences mSharedPreferences = getSharedPreferences("TitleName",MODE_PRIVATE);
        title1 = mSharedPreferences.getString("title1","One");
        title2 = mSharedPreferences.getString("title2","Two");
        title3 = mSharedPreferences.getString("title3","There");
        title4 = mSharedPreferences.getString("title4","Four");
        title5 = mSharedPreferences.getString("title5","Five");
    }



    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                addNote();
                return true;

            default:
                return false;
        }
    }
}
