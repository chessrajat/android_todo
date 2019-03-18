package com.websbro.whattodo.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.websbro.whattodo.MyTodo;
import com.websbro.whattodo.R;
import com.websbro.whattodo.todoAdapter;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlePage,subTitlePage;
    RecyclerView ourTodo;
    com.websbro.whattodo.todoAdapter todoAdapter;
    ArrayList<MyTodo> myTodosList;

    Button addTodo;
    SQLiteDatabase todoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = findViewById(R.id.title_page);
        subTitlePage = findViewById(R.id.subtitle_page);

        addTodo = findViewById(R.id.add_todo);

        ourTodo = findViewById(R.id.our_todo);
        myTodosList = new ArrayList<>();


        //database
        todoDatabase = openOrCreateDatabase("yourtodo",MODE_PRIVATE,null);
        todoDatabase.execSQL("create table if not exists mytodo(title varchar,description varchar,time varchar);");

        Cursor resultSet = todoDatabase.rawQuery("select * from mytodo",null);

        if(resultSet.moveToLast()) {

            String title = resultSet.getString(0);
            String description = resultSet.getString(1);
            String time = resultSet.getString(2);

            MyTodo temp = new MyTodo(title,description,time);
            myTodosList.add(temp);
            while (resultSet.moveToPrevious()){
                title = resultSet.getString(0);
                description = resultSet.getString(1);
                time = resultSet.getString(2);

                temp = new MyTodo(title,description,time);
                myTodosList.add(temp);
            }




        }





        //importing fonts;
        Typeface mLight = Typeface.createFromAsset(getAssets(),"fonts/ML.ttf");
        Typeface mMedium = Typeface.createFromAsset(getAssets(),"fonts/MM.ttf");

        //customize font

        titlePage.setTypeface(mMedium);
        subTitlePage.setTypeface(mLight);

        ourTodo.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new todoAdapter(this,myTodosList,todoDatabase);
        ourTodo.setAdapter(todoAdapter);
        todoAdapter.notifyDataSetChanged();


        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,add_todo.class);
                startActivity(intent);
            }
        });




    }




}
