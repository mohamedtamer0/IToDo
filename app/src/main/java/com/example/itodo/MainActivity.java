package com.example.itodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, endpage;
    Button btnAddNew;

    DatabaseReference reference;
    RecyclerView ourdoes;
    ArrayList<MyTodo> list;
    TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        endpage = findViewById(R.id.endpage);

        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,NewTaskAct.class);
                startActivity(a);
                finish();
            }
        });



        // working with data
        ourdoes = (RecyclerView) findViewById(R.id.ourdoes);
        ourdoes.setHasFixedSize(true);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<MyTodo>();

        todoAdapter = new TodoAdapter(MainActivity.this,list);
        ourdoes.setAdapter(todoAdapter);



        //get Data from firebase
        reference = FirebaseDatabase.getInstance().getReference("IToDo");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                   for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                   {
                       MyTodo p = dataSnapshot1.getValue(MyTodo.class);
                       list.add(p);
                   }
                    ourdoes.setAdapter(todoAdapter);
                   todoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //set code
                Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
            }

        });














    }
}