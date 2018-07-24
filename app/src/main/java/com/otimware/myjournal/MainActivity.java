package com.otimware.myjournal;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button mTextSignup;
    protected static String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.editView_email);
        mPassword = (EditText) findViewById(R.id.editView_password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mTextSignup=(Button) findViewById(R.id.text_signup);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    //intent to user account
                    startActivity(new Intent(MainActivity.this, EntriesActivity.class));
                }
            }
        };
        mLoginButton.setOnClickListener(this);
        mTextSignup.setOnClickListener(this);
    }

    public void startSignIn() {
        String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "feel all the fields", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "login unsuccesfull", Toast.LENGTH_LONG).show();
                    }
                    else if(task.isSuccessful()){
                        Intent goEntries=new Intent(MainActivity.this,EntriesActivity.class);
                        startActivity(goEntries);

                    }



                }
            });

        }
    }

    @Override
    public void
    onStart() {
        super.onStart();
        //check if user is signed in and update ui accordingly
        mAuth.addAuthStateListener(mAuthListener);

    }

    //    sign up new users
    protected void createAccount(String Email, String Password) {
        String email=Email;
        String password=Password;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"account successfully created",Toast.LENGTH_LONG).show();
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this,EntriesActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.pls try again",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    //sign in a user
    protected void signIn(String Email,String Password) {
        String email=Email;
        String password=Password;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"successful login",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this,EntriesActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entries_java, menu);
        return true;


    }


    public void onClick(View v){
      if(v==mLoginButton){
          startSignIn();
      }
        else if(v==mTextSignup){
          createAccount(mEmail.getText().toString(),mPassword.getText().toString());

      }
    }
}
