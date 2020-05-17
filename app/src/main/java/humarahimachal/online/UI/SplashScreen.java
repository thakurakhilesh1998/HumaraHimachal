package humarahimachal.online.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser mUser = mAuth.getCurrentUser();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mUser!=null)
                {
                    startActivity(new Intent(getApplicationContext(),About_Himachal_Activity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }


            }
        }, 1000);

    }
}
