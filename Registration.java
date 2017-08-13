package com.example.abhishek.restoran;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "yup";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        et1 = (EditText) findViewById(R.id.etname);
        et2 = (EditText) findViewById(R.id.etmail);
        et3 = (EditText) findViewById(R.id.etpass);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener()

        {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid()); // TAG PE CLICK Ctrl+Alt+C
                } else
                {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };


    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view)
    {

        String name=et1.getText().toString();
        String mail=et2.getText().toString();
        String pass=et3.getText().toString();

        if(name.isEmpty() && mail.isEmpty() && pass.isEmpty())
        {
            Toast.makeText(this, "Field Empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(mail,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            //Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Intent next=new Intent(Registration.this,Signin.class);
                            next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(next);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(Registration.this, "Failed",
                                        Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
        }

    }
}
