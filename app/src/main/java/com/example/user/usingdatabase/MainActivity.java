package com.example.user.usingdatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText ename,estream,eroll;
    private MyDatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new MyDatabaseHelper(MainActivity.this);
        ename=(EditText)findViewById(R.id.ename);
        estream=(EditText)findViewById(R.id.estream);
        eroll=(EditText)findViewById(R.id.eroll);
    }
    public void  onInsert(View v){
        if(mydb.insert(ename.getText().toString(),estream.getText().toString())){
            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
            ename.setText("");
            estream.setText("");
        }else{
            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
    }}
    public void onList(View v){
        Cursor cur;
        cur=mydb.list();
        if(cur.getCount()>0){
            StringBuffer buf=new StringBuffer();
            while (cur.moveToNext()){
                buf.append("\nROLL: "+cur.getString(0));
                buf.append("\nNAME: "+cur.getString(1));
                buf.append("\nSTREAM: "+cur.getString(2));
                buf.append("\n\n");

            }showAlert("Student Data",buf.toString());
        }else {
            showAlert("Database","NO Data Found!");
        }
    }

    private void showAlert(String title, String msg) {
        AlertDialog alert=new AlertDialog.Builder(this).setCancelable(true).create();
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.show();

    } public void onUpdate(View v){
        int rec;
      rec=mydb.update(eroll.getText().toString(),ename.getText().toString(),estream.getText().toString());
       /* if(rec<0){ Toast.makeText(MainActivity.this,rec+ "Record(s) updated", Toast.LENGTH_SHORT).show();}
  else{
            Toast.makeText(MainActivity.this, "No Record updated", Toast.LENGTH_SHORT).show();
        }*/
    } public void onDelete(View v){
        int rec;
        rec=mydb.delete(eroll.getText().toString());
       /* if(rec>0)
        {Toast.makeText(MainActivity.this,rec+ "Record(s) deleted", Toast.LENGTH_SHORT).show();}
        else
            Toast.makeText(MainActivity.this, "No Records deleted", Toast.LENGTH_SHORT).show();
*/
    }

}
