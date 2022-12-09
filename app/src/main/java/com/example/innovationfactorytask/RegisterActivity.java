package com.example.innovationfactorytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.innovationfactorytask.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressDialog loadingDialog;
    private DatabaseReference rootReference;
    private ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding= DataBindingUtil. setContentView(this,R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        rootReference= FirebaseDatabase.getInstance().getReference();
        init();
        viewsAction();
    }


    private void init() {

        loadingDialog = new ProgressDialog(this);

    }


    private void viewsAction() {

        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewAccount();
            }
        });


    }


    private void createNewAccount() {
//        edEmail=(EditText) findViewById(R.id.et_email) ;
//        edEmail=(EditText) findViewById(R.id.et_password);
        String email = binding.etEmail.getText().toString();
        String password =binding.etPassword.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(getBaseContext(), "please enter your email", Toast.LENGTH_LONG).show();
        }

        if (password.isEmpty()) {
            Toast.makeText(getBaseContext(), "please enter your password", Toast.LENGTH_LONG).show();
        }

        if (!email.isEmpty() && !password.isEmpty()) {
            loadingDialog.setTitle("Creating New Account");
            loadingDialog.setMessage("please wait, while we are creating new account for you ...");
            loadingDialog.setCanceledOnTouchOutside(true);
            loadingDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String deviceToken= FirebaseMessaging.getInstance().getToken().toString();
                        String currentUsrId=mAuth.getCurrentUser().getUid();
                        rootReference.child("User").child(currentUsrId).setValue("");
                        rootReference.child("User").child(currentUsrId).child("device_token").setValue(deviceToken);

                        // sendUserToMainActivity();
                        Toast.makeText(getBaseContext(), " The Account created successfully", Toast.LENGTH_LONG).show();
                        loadingDialog.dismiss();
                    } else {
                        String errorMessage = task.getException().toString();
                        Toast.makeText(getBaseContext(), "Error : " + errorMessage, Toast.LENGTH_LONG).show();
                        Log.i("TAG_register", "onComplete: "+errorMessage);
                        loadingDialog.dismiss();

                    }
                }
            });
        }
        else{
            Toast.makeText(getBaseContext(), " Please Enter Your Data ", Toast.LENGTH_LONG).show();
        }


    }



}