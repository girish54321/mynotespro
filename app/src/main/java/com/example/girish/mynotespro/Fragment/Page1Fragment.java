package com.example.girish.mynotespro.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.girish.mynotespro.Modle.PageDatabaseHelper;
import com.example.girish.mynotespro.MyListView;
import com.example.girish.mynotespro.R;
import com.example.girish.mynotespro.ShowNotesActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Page1Fragment extends Fragment {

    PageDatabaseHelper db;

    ListView noteslist;

    ArrayList<String> listitem;
    //---------------------
    ArrayList<String> note;

    ArrayAdapter adapter;




    public Page1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page1, container, false);

        db = new PageDatabaseHelper(getContext());

        noteslist = view.findViewById(R.id.notes_list_1);

        SharedPreferences mSharedPreferences = getContext().getSharedPreferences("TitleName",getContext().MODE_PRIVATE);
        final String title1 = mSharedPreferences.getString("title1","One");

        final String pagenum ="page1";// IMPORTED FOR UPDATE AND DELETE

        listitem = new ArrayList<>();
        note = new ArrayList<>();

        //-----------------------------------



       // noteslist.setAdapter(adapter);


        noteslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {@Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             String id = noteslist.getItemAtPosition(i).toString();

             if (id.equals("")) {

             } else {
                 Cursor res = db.seach(id.toString());
                 if (res.getCount() == 0) {
                     return;

                 }
                 while (res.moveToNext()) {
                     Intent intent = new Intent(getActivity(), ShowNotesActivity.class);
                     intent.putExtra("id", res.getString(0));
                     intent.putExtra("title", res.getString(1));
                     intent.putExtra("notes", res.getString(2));
                     intent.putExtra("pagenum",pagenum);// PASSING PAGE NUMNER FOR REFRECNSE
                     intent.putExtra("title_name",title1);
                     startActivity(intent);
                 }
             }
         }
         });

        viewData();
        return view;




    }

    private  void viewData(){   // GET ALL DATA
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0){
            Toast.makeText(getContext(),"NO Data Found",Toast.LENGTH_LONG).show();

        }else {
            while (cursor.moveToNext()){
                listitem.add(cursor.getString(1)); // Coulemn NUMBER 1
                note.add(cursor.getString(2)); // Coulemn NUMBER 2


            }

            //adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1,listitem);
            //noteslist.setAdapter(adapter);

            MyListView myListView = new MyListView(getActivity(),listitem,note);
            noteslist.setAdapter(myListView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        listitem.clear();
        viewData();
    }
}
