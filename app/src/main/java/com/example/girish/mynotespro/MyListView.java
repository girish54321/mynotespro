package com.example.girish.mynotespro;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListView extends ArrayAdapter<String> {

    private final Activity context;

    private final ArrayList<String> title;
    private final ArrayList<String> note;

    public MyListView(Activity context, ArrayList<String> title, ArrayList<String> note) {
        super(context, R.layout.mylist, title);
        // TODO Auto-generated constructor stub

        this.context=context;


        this.title = title;
        this.note = note;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(title.get(position));

        subtitleText.setText(note.get(position));

        return rowView;

    };
}