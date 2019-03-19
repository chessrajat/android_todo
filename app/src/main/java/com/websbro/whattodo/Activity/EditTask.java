package com.websbro.whattodo.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.websbro.whattodo.R;

public class EditTask extends AppCompatActivity {

    EditText title,description,timeline;

    SQLiteDatabase todoDatabase;
    String ti;

    Button update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        title = findViewById(R.id.titletodo);
        description = findViewById(R.id.desctodo);
        timeline = findViewById(R.id.datetodo);

        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);


        Intent intent = getIntent();
        ti = intent.getStringExtra("title");
        String dd = intent.getStringExtra("description");
        String tt = intent.getStringExtra("time");

        title.setText(ti);
        description.setText(dd);
        timeline.setText(tt);

        todoDatabase = openOrCreateDatabase("yourtodo", MODE_PRIVATE,null);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = title.getText().toString();
                String newDesc = description.getText().toString();
                String newTime = timeline.getText().toString();
                todoDatabase.execSQL("update mytodo set title ='"+newTitle+"', description='"+newDesc+"',time='"+newTime+"' where title = '"+ti+"';");
                changeActivity();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(EditTask.this);
                delete.setTitle("Delete")
                        .setMessage("Are you sure")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                todoDatabase.execSQL("DELETE FROM mytodo where title = '"+ti+"';");
                                changeActivity();



                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

            }
        });







    }


    public void changeActivity(){
        Intent intent1 = new Intent(EditTask.this,MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent1);
    }

}
