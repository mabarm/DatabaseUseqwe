package com.example.user.usingdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 4/7/2017.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
   private static final String DATABASE="Institute.db";
    private static final String TABLE="Student";
    private static final String COL_1="ROLL";
    private static final String COL_2="NAME";
    private static final String COL_3="STREAM";
    public MyDatabaseHelper(Context context) {
        super(context,DATABASE, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE+"(ROLL INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,STREAM TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
    public boolean insert(String name,String stream)
    {SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_2,name);
        values.put(COL_3,stream);
        long res= db.insert(TABLE,null,values);
        if(res==-1) return false;
        else
            return true;
    }
    public Cursor list(){
       Cursor cur;
        SQLiteDatabase db=this.getWritableDatabase();
        cur=db.rawQuery("select * from "+TABLE,null);
        return cur;
    }
    public int update(String roll,String name,String stream)
    {
        int rec=0;
        ContentValues value=new ContentValues();
        value.put(COL_1,roll);
        value.put(COL_2,name);
        value.put(COL_3,stream);
        SQLiteDatabase db=this.getWritableDatabase();
        db.update(TABLE,value,"ROLL =?",new String[]{roll});
        return rec;
    }
    public int delete(String roll)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int rec=0;
        db.delete(TABLE,"ROLL =?", new String[]{roll});
        return rec;
    }
}
