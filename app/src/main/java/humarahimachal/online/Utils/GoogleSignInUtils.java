package humarahimachal.online.Utils;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import humarahimachal.online.R;

public class GoogleSignInUtils {

    public static GoogleSignInOptions getGsoGoogle(Context context)
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getResources().getString(R.string.googleSignINClientId))
                .requestEmail()
                .requestProfile()
                .build();
        return gso;
    }
}
