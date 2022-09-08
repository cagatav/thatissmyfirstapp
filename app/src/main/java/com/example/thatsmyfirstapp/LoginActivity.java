package com.example.thatsmyfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thatsmyfirstapp.fragment.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    EditText  emailET,passwordET;
    Button loginBTN, registerBTN,resetPassBTN;
    ProgressDialog pd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.loginEmail);
        passwordET = findViewById(R.id.loginPass);

        loginBTN = findViewById(R.id.loginBtn);
        registerBTN = findViewById(R.id.loginRegisterBtn);
        resetPassBTN = findViewById(R.id.loginResetPass);


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailET.setError("Invalid Email!");
                    emailET.setFocusable(true);
                }

                    loginUser(email,password);

            }
        });
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });

        pd = new ProgressDialog(this);
        pd.setMessage("Logging In..");
    }

    private void loginUser(String email, String password) {
        pd.show();
        mAuth.signInWithEmailAndPassword(email,password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    pd.dismiss();
                    FirebaseUser user = mAuth.getCurrentUser();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else{
                    pd.dismiss();
                    Toast.makeText(LoginActivity.this,"Authentication Failed!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();

                Toast.makeText(LoginActivity.this,""+e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}