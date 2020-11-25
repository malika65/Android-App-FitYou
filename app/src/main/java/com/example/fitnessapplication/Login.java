package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText login,password;
    private Button login_btn;
    private TextView create_user_btn_text_view,forgot_password;
    private ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.log_in);
        create_user_btn_text_view = findViewById(R.id.create_account);
        progressBar = findViewById(R.id.progressBar_login);
        forgot_password = findViewById(R.id.forgot_password);

        fAuth = FirebaseAuth.getInstance();

        create_user_btn_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialod = new AlertDialog.Builder(v.getContext());
                passwordResetDialod.setTitle("Reset Password?");
                passwordResetDialod.setMessage("Enter Your Email To Received Reset Link");
                passwordResetDialod.setView(resetMail);

                passwordResetDialod.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Extract email and send reset link to user
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Link Sent To Your Email",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Error! Reset Link Is Not Sent" + e.getMessage() ,Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

                passwordResetDialod.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the dialog
                    }
                });
                passwordResetDialod.create().show();
            }
        });


    }

    public void log_in(View v){
        String login_str = login.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(TextUtils.isEmpty(login_str)){
            login.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(pass)){
            password.setError("Password is required");
            return;
        }
        if (pass.length() < 6){
            password.setError("Password must be greater than 6 character");
        }

        progressBar.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(login_str,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this,"Logged in successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else{
                    Toast.makeText(Login.this,"Error! "+task.getException().getMessage() ,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}