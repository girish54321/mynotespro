package com.example.girish.mynotespro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.girish.mynotespro.Modle.Page2Database;
import com.example.girish.mynotespro.Modle.Page3Database;
import com.example.girish.mynotespro.Modle.Page4Database;
import com.example.girish.mynotespro.Modle.Page5Database;
import com.example.girish.mynotespro.Modle.PageDatabaseHelper;

import es.dmoral.toasty.Toasty;

public class UpdateActivity extends AppCompatActivity {

    EditText updatetitle;
    EditText updatenote;
    TextView textid;

    PageDatabaseHelper db;
    Page2Database page2Database;
    Page3Database page3Database;
    Page4Database page4Database;
    Page5Database page5Database;

    String pagenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Toolbar toolbar = findViewById(R.id.toolbar);  // TOOLBAR
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updatetitle = findViewById(R.id.updatetitle);
        updatenote = findViewById(R.id.updatenotes);
        textid = findViewById(R.id.updatetextid);


        //DATABASES
        db = new PageDatabaseHelper(this);
        page2Database = new Page2Database(this);
        page3Database = new Page3Database(this);
        page4Database = new Page4Database(this);
        page5Database = new Page5Database(this);

        Intent intent = getIntent();
        final String id  = intent.getStringExtra("id");
        final String title  = intent.getStringExtra("title");
        final String notes  = intent.getStringExtra("notes");
        pagenum = intent.getStringExtra("pagenum");

        updatetitle.setText(title);
        updatenote.setText(notes);
        textid.setText(id);
    }

    public void upDateNote(){
        boolean chking = db.updateData(textid.getText().toString(),
                updatetitle.getText().toString().toUpperCase(),
                updatenote.getText().toString());
        if (chking = true){
            Toasty.success(UpdateActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
            textid.setText("");
            updatetitle.setText("");
            updatenote.setText("");
            Intent main = new Intent(UpdateActivity.this,MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(main);
            finish();

        }
        else {

        }       Toasty.error(UpdateActivity.this,"Some Thing Went Wrong",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // THIS NEED TO APPLEY MENUS TO ACTIVETY
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  // WHAT SHOOUD HAPEEN WHEN LIKED ON ITEM
        switch (item.getItemId()) {

            case R.id.save:
               chak();
                return true;

            default: return false;
        }
    }
    private void chak(){


        if (pagenum.equals("page1")){
            boolean chking = db.updateData(textid.getText().toString(),
                    updatetitle.getText().toString().toUpperCase(),
                    updatenote.getText().toString());
            if (chking = true){
                Toasty.success(UpdateActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                doneAndGO();
            }
            else { Toasty.error(UpdateActivity.this,"Some Thing Went Wrong",Toast.LENGTH_SHORT).show();
        }

        }else if (pagenum.equals("page2")){
            boolean chking = page2Database.updateData(textid.getText().toString(),
                    updatetitle.getText().toString().toUpperCase(),
                    updatenote.getText().toString());
            if (chking = true){
                Toasty.success(UpdateActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                doneAndGO();
            }
            else { Toasty.error(UpdateActivity.this,"Some Thing Went Wrong",Toast.LENGTH_SHORT).show();
            }

        }else if (pagenum.equals("page3")){
            boolean chking = page3Database.updateData(textid.getText().toString(),
                    updatetitle.getText().toString().toUpperCase(),
                    updatenote.getText().toString());
            if (chking = true){
                Toasty.success(UpdateActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                doneAndGO();
            }
            else { Toasty.error(UpdateActivity.this,"Some Thing Went Wrong",Toast.LENGTH_SHORT).show();
            }

        }else if (pagenum.equals("page4")){
            boolean chking = page4Database.updateData(textid.getText().toString(),
                    updatetitle.getText().toString().toUpperCase(),
                    updatenote.getText().toString());
            if (chking = true){
                Toasty.success(UpdateActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                doneAndGO();

            }
            else { Toasty.error(UpdateActivity.this,"Some Thing Went Wrong",Toast.LENGTH_SHORT).show();
            }

        }else if (pagenum.equals("page5")){

            boolean chking = page5Database.updateData(textid.getText().toString(),
                    updatetitle.getText().toString().toUpperCase(),
                    updatenote.getText().toString());
            if (chking = true){
                Toasty.success(UpdateActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                doneAndGO();

            }
            else { Toasty.error(UpdateActivity.this,"Some Thing Went Wrong",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toasty.error(UpdateActivity.this,"SomeThing Went Worng pagenum",Toast.LENGTH_SHORT).show();
        }

    }

    private void doneAndGO(){
        textid.setText("");
        updatetitle.setText("");
        updatenote.setText("");
        finish();
    }


}

