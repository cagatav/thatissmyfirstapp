package com.example.thatsmyfirstapp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText emailET, passwordET, confirmPasswordET;
    Button createAccBTN, loginBTN;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.registerEmail);
        passwordET = findViewById(R.id.registerPass);
        confirmPasswordET = findViewById(R.id.registerConfirmPass);

        createAccBTN = findViewById(R.id.registerCreateAccBtn);
        loginBTN = findViewById(R.id.registerLoginBtn);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User..");

        mAuth = FirebaseAuth.getInstance();

        createAccBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();
                String confirmPassword = confirmPasswordET.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailET.setError("Invalid Email!");
                    emailET.setFocusable(true);
                }
                else if (password.length()<6){
                    passwordET.setError("Password length at least 6 characters.");
                    passwordET.setFocusable(true);
                }
                else if (!password.equals(confirmPassword)){
                    confirmPasswordET.setError("Passwords are not matched.");
                    passwordET.setFocusable(true);
                }
                else {
                    registerUser (email,password);
                }
            }
        });
        loginBTN.setOnClickListener(v-> finish());

    }

    private void registerUser(String email, String password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this,"Registered..\n" +
                            user.getEmail(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this,"Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,""+e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

