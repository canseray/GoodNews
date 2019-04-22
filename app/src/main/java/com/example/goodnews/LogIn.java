package com.example.goodnews;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    EditText emailText,passwordText;


    public void init(){

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailText = findViewById(R.id.email_editText);
        passwordText = findViewById(R.id.password_editText);

        init();
    }

        protected void onStart(){

        if(user !=null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }

        super.onStart();

    }


    public void loginButton(View view){
        mAuth.signInWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogIn.this,"Successful!",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        }


                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LogIn.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void signupButton(View view){

           Intent intent = new Intent(getApplicationContext(),SignUp.class);
           startActivity(intent);

    }


}
