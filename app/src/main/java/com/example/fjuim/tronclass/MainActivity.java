package com.example.fjuim.tronclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FIRE_BASE";

    private Button button;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createItem();
        database();

    }

    private void database() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // Write a message to the database
                String value = editText.getText().toString();
                myRef.setValue(value);
            }
        });

        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                textView.setText("Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //showToast("获取异常");
                //Log.e(TAG, error.toException().toString());
            }
        });

    }

    private void createItem() {

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

    }

}

