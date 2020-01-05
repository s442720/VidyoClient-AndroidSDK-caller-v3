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

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    String caller;
    String receiver = "GXWm6dpuKEZPZhTua2nLCf4rliB2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        String txt_email = "admin@gmail.com";
        String txt_password = "123456";
        auth = FirebaseAuth.getInstance();

        login(txt_email, txt_password);
        firebaseUser = auth.getCurrentUser();
//        caller = firebaseUser.getUid();

        ConstraintLayout start;
        start = (ConstraintLayout) findViewById(R.id.person1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String message = extras.getString("message");
            Toast.makeText(StartingActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModalCall modalCall = new ModalCall();
                modalCall.setmActionListener(StartingActivity.this);
                modalCall.show(getSupportFragmentManager(), "modalMenu");
            }
        });
    }

    public void login(String txt_email, String txt_password) {
        auth.signInWithEmailAndPassword(txt_email, txt_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(StartingActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
//                            Toast.makeText(StartingActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createCallInDB(String caller, String receiver, String token, String path) {
        reference = FirebaseDatabase.getInstance().getReference(path).child("call");

        // connected = 0 means the connection has not built yet
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

    @Override
    public void onButtonClick(int id) {
        if (id == R.id.call) {
            caller = firebaseUser.getUid();
            String token = GenerateToken.generateProvisionToken("715b8b2142ee4385b37a8c0b4b752a75", "user1" + "@" + "696a11.vidyo.io", "300", "");
            String path = "Call_" + receiver;
            createCallInDB(caller, receiver, token, path);

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("token", token);
            intent.putExtra("path", path);
            startActivity(intent);
        }
        else if (id == R.id.cancel) {
        // do nothing
        }
    }

    // disable the back button
    @Override
    public void onBackPressed() {
        // do nothing
    }
}
