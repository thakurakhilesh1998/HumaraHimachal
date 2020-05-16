package humarahimachal.online.UI;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.auth.GoogleAuthProvider;

import humarahimachal.online.R;
import humarahimachal.online.Utils.CreateSnackBar;
import humarahimachal.online.Utils.GoogleSignInUtils;
import humarahimachal.online.Utils.ValidateUserInput;
import humarahimachal.online.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static int REQUEST_CODE = 100;
    ActivityLoginBinding loginBinding;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
    Dialog dialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loginwait));
        progressDialog.setCancelable(false);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInUtils.getGsoGoogle(this));
        loginBinding.googleSignIN.setOnClickListener(this);
        loginBinding.btnLogin.setOnClickListener(this);
        loginBinding.signUp.setOnClickListener(this);
        loginBinding.tvResetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.googleSignIN:
                googleSignIN();
                break;
            case R.id.btnLogin:
                signInWithEmailAndPassword();
                break;
            case R.id.signUp:
                openRedgisterActivity();
                break;
            case R.id.tvResetPassword:
                onPasswordReset();
        }

    }

    private void onPasswordReset() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.resetpass);
        final TextView error = dialog.findViewById(R.id.error);
        final EditText editText = dialog.findViewById(R.id.etEmail);
        Button btnResetPass = dialog.findViewById(R.id.btnResetPass);
        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText.getText().toString().trim();
                if (!(ValidateUserInput.isEmailCorrect(email))) {
                    error.setText(getResources().getString(R.string.emailError));
                    error.setVisibility(View.VISIBLE);
                } else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            error.setVisibility(View.GONE);
                            dialog.dismiss();
                            CreateSnackBar.createSnackBar(getApplicationContext(), loginBinding.parentview, getString(R.string.emailsent));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            error.setVisibility(View.VISIBLE);
                            error.setText(e.getMessage());
                        }
                    });
                }
            }
        });


    }

    private void openRedgisterActivity() {

        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    }

    private void signInWithEmailAndPassword() {
        String email = loginBinding.tvEmail.getText().toString().trim();
        String password = loginBinding.tvPassword.getText().toString();
        if (!(ValidateUserInput.isEmailCorrect(email))) {
            loginBinding.tvEmail.setBackground(getResources().getDrawable(R.drawable.errortextview));
            loginBinding.tvEmailError.setVisibility(View.VISIBLE);
        } else {
            loginBinding.tvEmailError.setVisibility(View.GONE);
        }
        if (!(ValidateUserInput.isPasswordCorrect(password))) {
            loginBinding.tvPassError.setVisibility(View.VISIBLE);
            loginBinding.tvEmail.setBackground(getResources().getDrawable(R.drawable.errortextview));
        } else {
            loginBinding.tvPassError.setVisibility(View.GONE);
        }
        if (ValidateUserInput.isEmailCorrect(email) && ValidateUserInput.isPasswordCorrect(password)) {
            progressDialog.show();
            loginBinding.tvEmailError.setVisibility(View.GONE);
            loginBinding.tvPassError.setVisibility(View.GONE);
            loginBinding.tvEmail.setBackground(getResources().getDrawable(R.drawable.input_text_border));
            loginBinding.tvEmail.setBackground(getResources().getDrawable(R.drawable.input_text_border));
            signInWithFirebase(email, password);
        }


    }

    private void signInWithFirebase(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();

                    if (mAuth.getCurrentUser() != null) {
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                    }
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                CreateSnackBar.createSnackBar(getApplicationContext(), loginBinding.parentview, e.getMessage());
            }
        });

    }

    private void googleSignIN() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_CODE);
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
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                CreateSnackBar.createSnackBar(getApplicationContext(), loginBinding.parentview, e.getMessage());
            }
        });
    }
}
