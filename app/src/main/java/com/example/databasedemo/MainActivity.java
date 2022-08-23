package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, year INT(4))");
            //sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Omkar', 19)");
            //sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Ashwin', 17)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users", null);
            //Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE age > 18 AND name = 'Omkar'", null);
            //Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE 'A%'", null); this will get all names starting from A
            //Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%r%'", null); this will get all names having letter r
            //Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%r%' LIMIT 1", null); this will get all names having letter r and limit letter to 1

            //sqLiteDatabase.execSQL("DELETE FROM users WHERE name = 'Omkar' LIMIT 1");

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            c.moveToFirst();

            while (!c.isAfterLast())
            {
                Log.i("UserResults - name", c.getString(nameIndex));
                Log.i("UserResults - age", Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        /* Mode Private indicates only this app can access this */

        /*SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))"); // 3 is nmax no of digits
        myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Monica', 30)");
        myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Chandler', 32)");

        Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

        int nameIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");

        c.moveToFirst();

        while (!c.isAfterLast())
        {
            Log.i("name", c.getString((nameIndex)));
            Log.i("age", c.getString((ageIndex)));

            c.moveToNext();
        }*/

    }
}