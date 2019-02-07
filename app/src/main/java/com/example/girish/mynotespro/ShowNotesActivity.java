package com.example.girish.mynotespro;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.girish.mynotespro.Modle.Page2Database;
import com.example.girish.mynotespro.Modle.Page3Database;
import com.example.girish.mynotespro.Modle.Page4Database;
import com.example.girish.mynotespro.Modle.Page5Database;
import com.example.girish.mynotespro.Modle.PageDatabaseHelper;

import es.dmoral.toasty.Toasty;

public class ShowNotesActivity extends AppCompatActivity {

    TextView dtitle;
    TextView dnotes;
    TextView textid;
    String notes_name;
    String pagenum;



    // DATATBASE REFRENSE
    PageDatabaseHelper db;
    Page2Database page2Database;
    Page3Database page3Database;
    Page4Database page4Database;
    Page5Database page5Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        Toolbar toolbar = findViewById(R.id.toolbar2);  // TOOLBAR
        setSupportActionBar(toolbar);

        dtitle = findViewById(R.id.dtitle);
        dnotes = findViewById(R.id.dnotes);
        textid = findViewById(R.id.notesid);


        //DATABSE REFRENSE
        db = new PageDatabaseHelper(this);
        page2Database = new Page2Database(this);
        page3Database = new Page3Database(this);
        page4Database = new Page4Database(this);
        page5Database = new Page5Database(this);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String title = intent.getStringExtra("title");
        final String notes = intent.getStringExtra("notes");
        pagenum = intent.getStringExtra("pagenum");
        notes_name = intent.getStringExtra("title_name");
        getSupportActionBar().setTitle(notes_name);


        dtitle.setText(title);
        dnotes.setText(notes);
        textid.setText(id);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    public void sendToUpdate() {
        Intent intent = new Intent(ShowNotesActivity.this, UpdateActivity.class);
        intent.putExtra("id", textid.getText().toString());
        intent.putExtra("title", dtitle.getText().toString());
        intent.putExtra("notes", dnotes.getText().toString());
        intent.putExtra("pagenum",pagenum);
        startActivity(intent);
        finish();
    }

    //      DIALOG YES OR NO

    public void alter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete ?")
                .setCancelable(false)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chake();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Delete Note - " + dtitle.getText().toString());
        alertDialog.show();
    }

    // DELETE CODE HERE



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // THIS NEED TO APPLEY MENUS TO ACTIVETY
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shownote_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  // WHAT SHOOUD HAPEEN WHEN LIKED ON ITEM
        switch (item.getItemId()) {

            case R.id.action_delete:
                alter();

                return true;

            case R.id.action_edite:
                sendToUpdate();
                finish();
                return true;
            case R.id.size:
                dnotes.setTextSize(20);
                return true;

            case R.id.action_shar:
                share();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
        //Intent home = new Intent(shownotesActivity.this,MainActivity.class);
        // startActivity(home);
        //finish();
        super.onBackPressed();
    }

    public void clerText(){
        dtitle.setText("Nothing to show");
        dnotes.setText("Nothing to show");
        textid.setText("Nothing to show");
        finish();
    }

    public void deleteNoteForPage1() {
        boolean chek = db.deletData(textid.getText().toString());
        if (chek = true) {
            Toasty.success(ShowNotesActivity.this, "Done", Toast.LENGTH_LONG).show();
            clerText();
        } else {
            Toasty.error(ShowNotesActivity.this, "No", Toast.LENGTH_LONG).show();
        }
    }
    public void deleteNoteForPage2() {
        boolean chek = page2Database.deletData(textid.getText().toString());
        if (chek = true) {
            Toasty.success(ShowNotesActivity.this, "Done", Toast.LENGTH_LONG).show();
            clerText();
        } else {
            Toasty.error(ShowNotesActivity.this, "No", Toast.LENGTH_LONG).show();
        }
    }
    public void deleteNoteForPage3() {
        boolean chek = page3Database.deletData(textid.getText().toString());
        if (chek = true) {
            Toasty.success(ShowNotesActivity.this, "Done", Toast.LENGTH_LONG).show();
            clerText();
        } else {
            Toasty.error(ShowNotesActivity.this, "No", Toast.LENGTH_LONG).show();
        }
    }
    public void deleteNoteForPage4() {
        boolean chek = page4Database.deletData(textid.getText().toString());
        if (chek = true) {
            Toasty.success(ShowNotesActivity.this, "Done", Toast.LENGTH_LONG).show();
            clerText();
        } else {
            Toasty.error(ShowNotesActivity.this, "No", Toast.LENGTH_LONG).show();
        }
    }
    public void deleteNoteForPage5() {
        boolean chek = page5Database.deletData(textid.getText().toString());
        if (chek = true) {
            Toasty.success(ShowNotesActivity.this, "Done", Toast.LENGTH_LONG).show();
            clerText();
        } else {
            Toasty.error(ShowNotesActivity.this, "No", Toast.LENGTH_LONG).show();
        }
    }

    public void chake(){
        //String number = pagenum;

        if (pagenum.equals("page1")){
            deleteNoteForPage1();
        }else if (pagenum.equals("page2")){
            deleteNoteForPage2();
        }else if (pagenum.equals("page3")){
            deleteNoteForPage3();
        }else if (pagenum.equals("page4")){
            deleteNoteForPage4();
        }else if (pagenum.equals("page5")){
            deleteNoteForPage5();
        }else {
            Toasty.error(ShowNotesActivity.this, "If ELSE NOT WORKING", Toast.LENGTH_LONG).show();
        }
    }

    public void share(){
        String main = dnotes.getText().toString();
        String title = dtitle.getText().toString();
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody = main;// THIS WILL BE THE MESSAGE OR TITLE SOME APP WILL SHOW THIS SOME MAY NOTIMPORTET
        String shareSub = title; // THIS IS A SUBJET AND + WHIT YOUR STRING
        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity((Intent.createChooser(myIntent,"Share Vai")));
    }

}

