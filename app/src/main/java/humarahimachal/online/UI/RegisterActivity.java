package humarahimachal.online.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import humarahimachal.online.R;
import humarahimachal.online.Utils.CreateSnackBar;
import humarahimachal.online.Utils.GoogleSignInUtils;
import humarahimachal.online.Utils.ValidateUserInput;
import humarahimachal.online.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private static final int REQUEST_CODE = 123;
    ActivityRegisterBinding registerBinding;
    ProgressDialog progressDialog;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.progrss));
        progressDialog.setCancelable(false);
        mGoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInUtils.getGsoGoogle(this));
        mAuth = FirebaseAuth.getInstance();
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerBinding.signUp.setOnClickListener(this);
        registerBinding.btnRegister.setOnClickListener(this);
        registerBinding.googleSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUp:
                openLoginActivity();
                break;
            case R.id.btnRegister:
                onRegisterButton();
                break;
            case R.id.googleSignUp:
                onGoogleSignUP();
        }
    }

    private void onGoogleSignUP() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_CODE);
    }

    private void onRegisterButton() {
        String name = registerBinding.tvName.getText().toString().trim();
        String email = registerBinding.tvEmail.getText().toString().trim();
        String password = registerBinding.tvPassword.getText().toString().trim();
        checkForValidUserInput(name, email, password);
    }

    private void checkForValidUserInput(String name, String email, String password) {

        if (!ValidateUserInput.isEmailCorrect(email)) {
            registerBinding.tvEmail.setBackground(getResources().getDrawable(R.drawable.errortextview));
            registerBinding.tvEmailError.setVisibility(View.VISIBLE);
        }
        if (!ValidateUserInput.isNameCorrect(name)) {
            registerBinding.tvName.setBackground(getResources().getDrawable(R.drawable.errortextview));
            registerBinding.tvNameError.setVisibility(View.VISIBLE);
        }
        if (!ValidateUserInput.isPasswordCorrect(password)) {
            registerBinding.tvPassword.setBackground(getResources().getDrawable(R.drawable.errortextview));
            registerBinding.tvPasswordError.setVisibility(View.VISIBLE);
        }
        if (ValidateUserInput.isEmailCorrect(email) && ValidateUserInput.isNameCorrect(name) && ValidateUserInput.isPasswordCorrect(password)) {
            registerBinding.tvEmail.setBackground(getResources().getDrawable(R.drawable.input_text_border));
            registerBinding.tvName.setBackground(getResources().getDrawable(R.drawable.input_text_border));
            registerBinding.tvPassword.setBackground(getResources().getDrawable(R.drawable.input_text_border));
            registerBinding.tvEmailError.setVisibility(View.GONE);
            registerBinding.tvNameError.setVisibility(View.GONE);
            registerBinding.tvPasswordError.setVisibility(View.GONE);
            registerUserithFirebase(name, email, password);
        }
    }

    private void registerUserithFirebase(final String name, String email, String password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    sendEmailVerificationLink(name);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                CreateSnackBar.createSnackBar(getApplicationContext(), registerBinding.registerParent, e.getMessage());
                progressDialog.dismiss();
            }
        });


    }

    private void sendEmailVerificationLink(final String name) {
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                FirebaseUser mUser = mAuth.getCurrentUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build();
                mUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        finish();
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        CreateSnackBar.createSnackBar(getApplicationContext(), registerBinding.registerParent, e.getMessage());
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                CreateSnackBar.createSnackBar(getApplicationContext(), registerBinding.registerParent, e.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    private void openLoginActivity() {

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    CreateSnackBar.createSnackBar(getApplicationContext(), registerBinding.registerParent, getString(R.string.registered));
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                CreateSnackBar.createSnackBar(getApplicationContext(), registerBinding.registerParent, e.getMessage());
            }
        });
    }
}
