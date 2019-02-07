package com.example.girish.mynotespro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.girish.mynotespro.Fragment.Page1Fragment;
import com.example.girish.mynotespro.Fragment.Page2Fragment;
import com.example.girish.mynotespro.Fragment.Page3Fragment;
import com.example.girish.mynotespro.Fragment.Page4Fragment;
import com.example.girish.mynotespro.Fragment.Page5Fragment;

import java.io.File;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    Menu menu;

    String title1,title2,title3,title4,title5;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);  // TOOLBAR
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        drawer = findViewById(R.id.drawer_layout);

        // MAN ACTYVEY ID NAME
        NavigationView navigationView = findViewById(R.id.nav_view);
        // nav view is real desinern
        // get menu from navigationView
        Menu menu = navigationView.getMenu();

        titles();

        // find MenuItem you want to change
        MenuItem page1 = menu.findItem(R.id.nav_page1);
        MenuItem page2 = menu.findItem(R.id.nav_page2);
        MenuItem page3 = menu.findItem(R.id.nav_page3);
        MenuItem page4 = menu.findItem(R.id.nav_page4);
        MenuItem page5 = menu.findItem(R.id.nav_page5);
        // set new title to the MenuItem
        page1.setTitle(title1);
        page2.setTitle(title2);
        page3.setTitle(title3);
        page4.setTitle(title4);
        page5.setTitle(title5);
        // do the same for other MenuItems


        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);  // TO STRING ITEMS IN NOTPADE TO USE
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {  // KEEP THE ONE FRAGMERT OR DEFULT FRAGEMERT SELECTED
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Page1Fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_page1);
            getSupportActionBar().setTitle(title1);
        }




    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_page1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,   // HERE SELET OR ADD CODE WHAT YOU WANT TO DO
                        new Page1Fragment()).commit();
                getSupportActionBar().setTitle(title1);

                break;
            case R.id.nav_page2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Page2Fragment()).commit();
                getSupportActionBar().setTitle(title2);
                break;
            case R.id.nav_page3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Page3Fragment()).commit();
                getSupportActionBar().setTitle(title3);
                break;
            case R.id.nav_page4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Page4Fragment()).commit();
                getSupportActionBar().setTitle(title4);
                break;
            case R.id.nav_page5:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Page5Fragment()).commit();
                getSupportActionBar().setTitle(title5);
                break;
            case R.id.nav_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_add:
                Intent addnote = new Intent(MainActivity.this, addnotesActivity.class);
                startActivity(addnote);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }

        if (backPressedTime + 2000> System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toasty.info(MainActivity.this,"Press Again To Exit ",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void titles(){  // GETING TITLE FORM SETING ACTVEYT
        SharedPreferences mSharedPreferences = getSharedPreferences("TitleName",MODE_PRIVATE);
        title1 = mSharedPreferences.getString("title1","One");
        title2 = mSharedPreferences.getString("title2","Two");
        title3 = mSharedPreferences.getString("title3","There");
        title4 = mSharedPreferences.getString("title4","Four");
        title5 = mSharedPreferences.getString("title5","Five");
    }




}