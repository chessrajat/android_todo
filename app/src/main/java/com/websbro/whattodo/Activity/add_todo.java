package com.websbro.whattodo.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.websbro.whattodo.R;

public class add_todo extends AppCompatActivity {

    EditText titleTodo,descTodo,dateTodo;
    Button saveTask,cancelTask;

    SQLiteDatabase todoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        titleTodo = findViewById(R.id.titletodo);
        descTodo = findViewById(R.id.desctodo);
        dateTodo = findViewById(R.id.datetodo);


        saveTask = findViewById(R.id.btnSaveTask);
        cancelTask = findViewById(R.id.btnCancel);

        todoDatabase = openOrCreateDatabase("yourtodo",MODE_PRIVATE,null);
        todoDatabase.execSQL("create table if not exists mytodo(title varchar,description varchar,time varchar);");








    }



    public void saveInDatabase(View view){
        String title = titleTodo.getText().toString();
        String description = descTodo.getText().toString();
        String date = dateTodo.getText().toString();

        if(title.length()>0 && description.length()>0 && date.length()>0){
            todoDatabase.execSQL("insert into mytodo values('"+title+"','"+description+"','"+date+"');");
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);


        }else {
            Toast.makeText(this,"Fields can not be empty",Toast.LENGTH_SHORT).show();
        }
    }

}
