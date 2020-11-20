package com.example.itodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditTaskDesk extends AppCompatActivity {


    EditText titleDoes, descDoes, dateDoes;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleDoes = findViewById(R.id.titleDoes);
        descDoes = findViewById(R.id.descDoes);
        dateDoes = findViewById(R.id.dateDoes);

        btnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        btnDelete = findViewById(R.id.btnDelete);


        //Get Value
        titleDoes.setText(getIntent().getStringExtra("titledoes"));
        descDoes.setText(getIntent().getStringExtra("descdoes"));
        dateDoes.setText(getIntent().getStringExtra("datedoes"));

        final String keykeyDoes = getIntent().getStringExtra("keydoes");
        // insert data to database
        reference = FirebaseDatabase.getInstance().getReference().child("IToDo").
                child("ToDo" + keykeyDoes);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent a = new Intent(EditTaskDesk.this,MainActivity.class);
                                startActivity(a);


                            }
                            else
                            {
                                Toast.makeText(EditTaskDesk.this, "Error!", Toast.LENGTH_SHORT).show();
                            }

                     }

                 });
                 finish();
            }

        });


        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titledoes").setValue(titleDoes.getText().toString());
                        dataSnapshot.getRef().child("descdoes").setValue(descDoes.getText().toString());
                        dataSnapshot.getRef().child("datedoes").setValue(dateDoes.getText().toString());
                        dataSnapshot.getRef().child("keydoes").setValue(keykeyDoes);
                        Intent a = new Intent(EditTaskDesk.this,MainActivity.class);
                        startActivity(a);

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                finish();
            }
        });



    }
}