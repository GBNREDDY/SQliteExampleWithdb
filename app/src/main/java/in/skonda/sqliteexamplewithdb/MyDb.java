package in.skonda.sqliteexamplewithdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GBNREDDY on 23-01-2017.
 */

public class MyDb {
    public SQLiteDatabase sqLiteDatabase;
    public Db db;
    MyDb(Context context){
        db=new Db(context);
    }
    public void open(){
        sqLiteDatabase=db.getWritableDatabase();
    }
    public Long insertData(String a, String b, String c, String d) {
        ContentValues con=new ContentValues();
        con.put("a",a);
        con.put("b",b);
        con.put("c",c);
        con.put("d",d);
        return sqLiteDatabase.insertOrThrow("test",null,con);
    }
    public Cursor select(){
        return sqLiteDatabase.rawQuery("select * from test",null);
    }
    public void close(){
        db.close();
    }

    class Db extends SQLiteOpenHelper{
        public static final String DbName="mydb";
        public static final int DbVersion=1;
        public Db(Context context) {
            super(context, DbName, null, DbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table test(a varchar(10),b varchar(10),c varchar(10),d varchar(10));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
