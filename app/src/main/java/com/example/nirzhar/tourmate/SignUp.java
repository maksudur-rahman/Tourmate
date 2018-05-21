package com.example.nirzhar.tourmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText etName, etPhone, etEmail,etAdd, etPass;
    Button btnSigntUp;
    TextView txtLogin;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,databaseReference2;
    LinearLayout donorlayout,volunteerlayout;
    String Address = null,Phone =null,Name=null,Email=null,Pass=null;
    int i=0;
    String keyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        etName = (EditText)findViewById(R.id.username);
        etEmail =(EditText)findViewById(R.id.email);
        etPass= (EditText)findViewById(R.id.password);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Donor");

    }

    public void signup(View view) {
            registration();

    }

    private void registration() {


        Name = etName.getText().toString();
        Email =etEmail.getText().toString();
        Pass = etPass.getText().toString();


        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Pass))
            Toast.makeText(this, "Make Sure You have filled up all the fields ", Toast.LENGTH_SHORT).show();
        else {

            firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {

                                keyValue = databaseReference.push().getKey();
                                    Donor donor = new Donor(Name, Phone, Address,Email);
                                    databaseReference.child(keyValue).setValue(donor);
                                Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_LONG).show();
                                moveToHome();
                            }
                            else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void moveToHome() {
        Intent intent=new Intent(SignUp.this,EventProfileActivity.class);
        intent.putExtra("keyValue",keyValue);
        startActivity(intent);
    }

    public void moveSignIn(View view) {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
    }
}