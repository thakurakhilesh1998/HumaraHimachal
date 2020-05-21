package humarahimachal.online.Utils;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import humarahimachal.online.Modal.GeneralInfoModal;

public class FirebaseDataSorting {
    public static String waiting="Wait...Loading Data..";

    public static void getDataFromFirebase(DocumentSnapshot d, ArrayList<GeneralInfoModal> generalInfoModals)
    {
        Map<String, Object> generalInfo = d.getData();
        Set keys = generalInfo.entrySet();
        Iterator i = keys.iterator();
        while (i.hasNext()) {
            String value = i.next().toString();
            for (int j = 0; j < value.length(); j++) {
                if ('=' == value.charAt(j)) {
                    String key = value.substring(0, j).trim();
                    String valueOf = value.substring(j + 1, value.length()).trim();
                    generalInfoModals.add(new GeneralInfoModal(key, valueOf));
                    break;
                }
            }

        }
    }
}
