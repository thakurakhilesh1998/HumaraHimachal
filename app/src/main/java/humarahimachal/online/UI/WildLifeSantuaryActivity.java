package humarahimachal.online.UI;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import humarahimachal.online.Adapter.WildLifeAdapter;
import humarahimachal.online.Modal.WildLifeModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityWildLifeSantuaryBinding;

public class WildLifeSantuaryActivity extends AppCompatActivity {
    private static final String TAG = "wildlife";
    private static final String TITLE="Wild Life Sanctuary";
    private static final String FIRESTORE_WILDLIFE = "wildlifesanctuary";
    ArrayList<WildLifeModal> wildLifeList;
    FirebaseFirestore firebaseFirestore;
    ActivityWildLifeSantuaryBinding wildlifeBinding;
    WildLifeAdapter wildLifeAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wild_life_santuary);
        wildlifeBinding = DataBindingUtil.setContentView(this, R.layout.activity_wild_life_santuary);
        getSupportActionBar().setTitle(TITLE);
        wildLifeList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loadingData));
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseFirestore = FirebaseFirestore.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        wildlifeBinding.rvWildLife.setLayoutManager(layoutManager);
        wildLifeAdapter = new WildLifeAdapter(this, wildLifeList);
        wildlifeBinding.rvWildLife.setAdapter(wildLifeAdapter);
        getDataFromFireStore();
    }

    private void getDataFromFireStore() {
        firebaseFirestore.collection(FIRESTORE_WILDLIFE).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                    progressDialog.dismiss();
                    WildLifeModal wildLifeModal = d.toObject(WildLifeModal.class);
                    wildLifeList.add(wildLifeModal);
                }
                wildLifeAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }
}
