package com.example.itodo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    MainActivity mainActivity;
    ArrayList<MyTodo> myTodos;

    public TodoAdapter(MainActivity mainActivity, ArrayList<MyTodo> myTodos) {
        this.mainActivity = mainActivity;
        this.myTodos = myTodos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = inflater.inflate(R.layout.item_does,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.titledoes.setText(myTodos.get(i).getTitledoes());
        myViewHolder.descdoes.setText(myTodos.get(i).getDescdoes());
        myViewHolder.datedoes.setText(myTodos.get(i).getDatedoes());



        final String getTitleDoes = myTodos.get(i).getTitledoes();
        final String getDescDoes = myTodos.get(i).getDescdoes();
        final String getDateDoes = myTodos.get(i).getDatedoes();
        final String getKeyDoes = myTodos.get(i).getKeydoes();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(mainActivity,EditTaskDesk.class);
                aa.putExtra("titledoes", getTitleDoes);
                aa.putExtra("descdoes", getDescDoes);
                aa.putExtra("datedoes", getDateDoes);
                aa.putExtra("keydoes", getKeyDoes);
                mainActivity.startActivity(aa);
            }
        });


    }

    @Override
    public int getItemCount() {
        return myTodos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titledoes, descdoes, datedoes, keydoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = (TextView) itemView.findViewById(R.id.titledoes);
            descdoes = (TextView) itemView.findViewById(R.id.descdoes);
            datedoes = (TextView) itemView.findViewById(R.id.datedoes);

        }
    }
}
