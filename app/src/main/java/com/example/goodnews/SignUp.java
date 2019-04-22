package com.example.goodnews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    Button signUp;
    EditText emailText,passwordText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.signup_button);
        emailText = findViewById(R.id.email_editText);
        passwordText = findViewById(R.id.password_editText);

    }

    public void signupButton (View view){

        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User Created!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }




                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(SignUp.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
