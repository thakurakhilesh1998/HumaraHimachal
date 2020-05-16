package humarahimachal.online.Utils;

import android.util.Patterns;

public class ValidateUserInput {

    public static boolean isEmailCorrect(String email) {
        if (email.length() != 0) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    public static boolean isPasswordCorrect(String password) {
        if (!(password.length() < 6)) {
            return true;
        }
        return false;
    }

    public static boolean isNameCorrect(String name) {
        if (!(name.length() < 2)) {
            return true;
        }
        return false;
    }
}
