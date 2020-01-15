package com.vidyo.vidyoconnector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StartingActivity extends AppCompatActivity implements ModalCall.ActionListener{

    DatabaseReference reference;

    String caller = "123456789";
    String receiver = "695556329";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        // show the first person
        ConstraintLayout start;
        start = (ConstraintLayout) findViewById(R.id.person1);

        // check whether there are parameters from previous activity
        Bundle extras = getIntent().getExtras();
        // If yes, show the message
        if (extras != null) {
            String message = extras.getString("message");
            Toast.makeText(StartingActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        // connect to ModalCall
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModalCall modalCall = new ModalCall();
                modalCall.setmActionListener(StartingActivity.this);
                modalCall.show(getSupportFragmentManager(), "modalMenu");
            }
        });
    }

    @Override
    public void onButtonClick(int id) {
        if (id == R.id.call) {
            // generate a token for this call
            String token = GenerateToken.generateProvisionToken("715b8b2142ee4385b37a8c0b4b752a75", "user1" + "@" + "696a11.vidyo.io", "300", "");
            String path = "Call_" + receiver;
            createCallInDB(caller, receiver, token, path);

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            // pass two parameters to next activity
            intent.putExtra("token", token);
            intent.putExtra("path", path);
            startActivity(intent);
        }
        else if (id == R.id.cancel) {
        // do nothing
        }
    }

    public void createCallInDB(String caller, String receiver, String token, String path) {
        reference = FirebaseDatabase.getInstance().getReference(path).child("call");

        // status = 0 means the connection has not built yet
        Call data = new Call(caller, receiver, token, 0);

        reference.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    String value = "Build connection failed";
                    Intent intent = new Intent(getBaseContext(), StartingActivity.class);
                    intent.putExtra("message", value);
                    startActivity(intent);
                }

            }
        });
    }

    // disable the back button
    @Override
    public void onBackPressed() {
        // do nothing
    }
}
