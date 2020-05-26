package humarahimachal.online.UI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import humarahimachal.online.Adapter.HydroProjectAdapter;
import humarahimachal.online.Modal.HydroModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityHydroProjectBinding;

public class HydroProjectActivity extends AppCompatActivity {
    private static final String TAG = "hydroprojectactivity";
    private static final String DOCUMENT = "hydroelectricpojects";
    FirebaseFirestore firebaseFirestore;
    ArrayList<HydroModal> hydroList;
    ActivityHydroProjectBinding projectBinding;
    HydroProjectAdapter hydroProjectAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_project);
        getSupportActionBar().setTitle(getResources().getString(R.string.hydroTitle));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loadingData));
        progressDialog.setCancelable(false);
        progressDialog.show();
        hydroList = new ArrayList<>();
        projectBinding = DataBindingUtil.setContentView(this, R.layout.activity_hydro_project);
        firebaseFirestore = FirebaseFirestore.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        projectBinding.rvHydroProject.setLayoutManager(layoutManager);
        hydroProjectAdapter = new HydroProjectAdapter(this, hydroList);
        projectBinding.rvHydroProject.setAdapter(hydroProjectAdapter);
        addAds();
        getDataFromFireStore();
    }

    private void addAds() {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        projectBinding.adView.loadAd(adRequest);

    }

    private void getDataFromFireStore() {
        firebaseFirestore.collection(DOCUMENT).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                    progressDialog.dismiss();
                    HydroModal hydroModal = d.toObject(HydroModal.class);
                    hydroList.add(hydroModal);
                    projectBinding.rvHydroProject.setVisibility(View.VISIBLE);
                }
                hydroProjectAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                projectBinding.rvHydroProject.setVisibility(View.GONE);
                projectBinding.notConnectedView.setVisibility(View.VISIBLE);
                Log.i(TAG, e.getMessage());
            }
        });
    }
}
