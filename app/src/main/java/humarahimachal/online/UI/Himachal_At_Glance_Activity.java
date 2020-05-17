package humarahimachal.online.UI;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import humarahimachal.online.Adapter.GeneralInfoAdapter;
import humarahimachal.online.Modal.GeneralInfoModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityHimachalAtGlanceBinding;

public class Himachal_At_Glance_Activity extends AppCompatActivity {
    private static String GENERAL_INFO = "generalinfo";
    private static String TAG = "atgalnce";
    ActivityHimachalAtGlanceBinding atGlanceBinding;
    FirebaseFirestore firebaseFirestore;
    ArrayList<GeneralInfoModal> generalInfoModals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himachal__at__glance_);
        atGlanceBinding = DataBindingUtil.setContentView(this, R.layout.activity_himachal__at__glance_);
        firebaseFirestore = FirebaseFirestore.getInstance();
        generalInfoModals = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        atGlanceBinding.rvGeneralInfo.setLayoutManager(layoutManager);
        GeneralInfoAdapter generalInfoAdapter = new GeneralInfoAdapter(this, generalInfoModals);
        atGlanceBinding.rvGeneralInfo.setAdapter(generalInfoAdapter);
        getDataFromFirestore();
    }

    private void getDataFromFirestore() {
        firebaseFirestore.collection(GENERAL_INFO).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot data : task.getResult()) {
                        Log.i(TAG, data.getData().toString());
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, e.getMessage());
            }
        });
    }
}
