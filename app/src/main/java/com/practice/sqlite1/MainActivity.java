package com.practice.sqlite1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,contact,dob;
    Button insert,update,delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        dob=findViewById(R.id.dob);
        insert=findViewById(R.id.btninsert);
        update=findViewById(R.id.btnupdate);
        delete=findViewById(R.id.btndelete);
        view=findViewById(R.id.btnview);

        DB=new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt=name.getText().toString();
                String contacttxt=contact.getText().toString();
                String dobtxt=dob.getText().toString();

                Boolean checkinsertdata=DB.insertuserdata(nametxt,contacttxt,dobtxt);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "New entry Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Entry already existed, Not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt=name.getText().toString();
                String contacttxt=contact.getText().toString();
                String dobtxt=dob.getText().toString();

                Boolean checkupdatedata=DB.updateuserdata(nametxt,contacttxt,dobtxt);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "entry updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "entry not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt=name.getText().toString();

                Boolean checkdeletedata=DB.deletedata(nametxt);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No entry exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name"+res.getString(0)+"\n");
                    buffer.append("contact"+res.getString(1)+"\n");
                    buffer.append("DOB"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}