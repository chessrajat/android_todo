package com.websbro.whattodo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.websbro.whattodo.Activity.EditTask;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.MyViewHolder> {


    Context context;
    ArrayList<MyTodo> myTodos;
    SQLiteDatabase todoDatabase;


    public todoAdapter(Context context, ArrayList<MyTodo> myTodos,SQLiteDatabase todoDatabase) {
        this.context = context;
        this.myTodos = myTodos;
        this.todoDatabase = todoDatabase;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {

        viewHolder.titledoes.setText(myTodos.get(i).getTitleToDo());
        viewHolder.descdoes.setText(myTodos.get(i).getDescriptionToDo());
        viewHolder.datedoes.setText(myTodos.get(i).getTimeToDo());

        final String getTitleDoes = myTodos.get(i).getTitleToDo();
        final String getDescDoes = myTodos.get(i).getDescriptionToDo();
        final String getDateDoes = myTodos.get(i).getTimeToDo();


        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(context);
                delete.setTitle("Delete")
                        .setMessage("Are you sure")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                todoDatabase.execSQL("DELETE FROM mytodo WHERE title = '"+getTitleDoes+"';");
                                Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
                                myTodos.remove(viewHolder.getPosition());
                                notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTask.class);
                intent.putExtra("title",getTitleDoes);
                intent.putExtra("description",getDescDoes);
                intent.putExtra("time",getDateDoes);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myTodos.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titledoes, descdoes, datedoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = itemView.findViewById(R.id.titledoes);
            descdoes =  itemView.findViewById(R.id.descdoes);
            datedoes =  itemView.findViewById(R.id.datedoes);
        }
    }
}
