package com.example.nlpc06.firechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvMessage;
    private Button btnSend;
    private EditText etMessage;

    DatabaseReference chatRef;

    private String name ="Sohel";

    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ChatAdapter(getApplicationContext());

        etMessage = (EditText) findViewById(R.id.et_message);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
        rvMessage = (RecyclerView) findViewById(R.id.rv_message);
        rvMessage.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMessage.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        chatRef = database.getReference("Chat");

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Message message = dataSnapshot.getValue(Message.class);
                adapter.addMessage(message);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
                adapter.removeMessage(message);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        String text = etMessage.getText().toString();

        Message message = new Message(name,text);

        chatRef.push().setValue(message);

        etMessage.setText("");

    }
}
