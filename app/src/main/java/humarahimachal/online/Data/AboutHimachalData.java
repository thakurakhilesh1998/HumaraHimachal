package humarahimachal.online.Data;

import android.content.Context;

import java.util.ArrayList;

import humarahimachal.online.Modal.AboutModal;
import humarahimachal.online.R;

public class AboutHimachalData {

    public static ArrayList<AboutModal> getHimachalAboutData(Context context) {
        ArrayList<AboutModal> aboutModals = new ArrayList<>();
        aboutModals.add(new AboutModal(R.drawable.historry, context.getResources().getString(R.string.history)));
        aboutModals.add(new AboutModal(R.drawable.atgalance, context.getResources().getString(R.string.atglance)));
        aboutModals.add(new AboutModal(R.drawable.geography, context.getResources().getString(R.string.geography)));
        aboutModals.add(new AboutModal(R.drawable.temples, context.getString(R.string.temples)));
        aboutModals.add(new AboutModal(R.drawable.ic_fair, context.getResources().getString(R.string.fairandfestival)));
        return aboutModals;
    }
}
