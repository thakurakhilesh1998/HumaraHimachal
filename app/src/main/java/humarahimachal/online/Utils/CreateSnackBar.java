package humarahimachal.online.Utils;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class CreateSnackBar {

    public static void createSnackBar(Context context, View view,String message)
    {
        Snackbar snackbar=Snackbar.make(view,message,Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
