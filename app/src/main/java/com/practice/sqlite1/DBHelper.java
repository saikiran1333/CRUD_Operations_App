package com.practice.sqlite1;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table Userdetails(name Text primary key,contact text,dob text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists Userdetails");
    }
    public Boolean insertuserdata(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put("name",name);
        contentvalues.put("contact",contact);
        contentvalues.put("dob",dob);
        long result=DB.insert("Userdetails",null,contentvalues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String name,String contact,String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("contact", contact);
        contentvalues.put("dob", dob);
        Cursor cursor = DB.rawQuery("select *from Userdetails where name=?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentvalues, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

        public Boolean deletedata(String name){
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("select *from Userdetails where name=?", new String[]{name});
            if (cursor.getCount() > 0) {
                long result = DB.delete("Userdetails", "name=?", new String[]{name});

                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
        public Cursor getdata(){
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("select *from Userdetails", null);
            return cursor;
        }
}