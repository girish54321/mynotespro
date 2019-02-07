package com.example.girish.mynotespro.Modle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Page4Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "Page4.db";
    private static final String DB_TABLE = "Page4_Table";


    //colums
    private static final String ID = "ID";
    private static final String TITLE = "TITLE";
    private static final String NOTES = "NOTES";

    private static  final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE +" ("+
            ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TITLE + " TEXT ,"+
            NOTES + " TEXT "+ ")";

    public Page4Database(Context context){
        super(context, DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);

        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String title,String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,title);
        contentValues.put(NOTES,notes);
        long result = db.insert(DB_TABLE,null, contentValues);

        return result != -1;
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor seach(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Page4_Table WHERE TITLE='"+title+"'",null);
        return res;
    }

    public boolean updateData(String id,String name, String notes){

        SQLiteDatabase dp = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(TITLE,name);
        contentValues.put(NOTES,notes);

        dp.update(DB_TABLE, contentValues,"ID = ?",new String[] {id});
        return  true;


    }

    public boolean deletData(String id){
        SQLiteDatabase dp = this.getWritableDatabase();
        return dp.delete(DB_TABLE,ID + "=" + id,null)>0;
    }
}
