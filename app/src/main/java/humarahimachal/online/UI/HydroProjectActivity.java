package humarahimachal.online.UI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
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

public class HydroProjectActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "hydroprojectactivity";
    private static final String DOCUMENT = "hydroelectricpojects";
    FirebaseFirestore firebaseFirestore;
    ArrayList<HydroModal> hydroList;
    ActivityHydroProjectBinding projectBinding;
    HydroProjectAdapter hydroProjectAdapter;
    Sprite doubleBounce;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_project);
        getSupportActionBar().setTitle(getResources().getString(R.string.hydroTitle));
        hydroList = new ArrayList<>();
        projectBinding = DataBindingUtil.setContentView(this, R.layout.activity_hydro_project);
        doubleBounce = new Wave();
        projectBinding.progrssBar.setIndeterminateDrawable(doubleBounce);
        projectBinding.progrssBar.setVisibility(View.VISIBLE);
        firebaseFirestore = FirebaseFirestore.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        projectBinding.rvHydroProject.setLayoutManager(layoutManager);
        hydroProjectAdapter = new HydroProjectAdapter(this, hydroList);
        projectBinding.rvHydroProject.setAdapter(hydroProjectAdapter);
        projectBinding.btnTryAgain.setOnClickListener(this);
        addAds();
        getDataFromFireStore();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });
    }

    private void addAds() {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        projectBinding.adView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstialid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    private void getDataFromFireStore() {
        firebaseFirestore.collection(DOCUMENT).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    projectBinding.progrssBar.setVisibility(View.GONE);
                    projectBinding.rvHydroProject.setVisibility(View.VISIBLE);
                    for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                        HydroModal hydroModal = d.toObject(HydroModal.class);
                        hydroList.add(hydroModal);
                        projectBinding.rvHydroProject.setVisibility(View.VISIBLE);
                    }
                    hydroProjectAdapter.notifyDataSetChanged();
                } else {
                    projectBinding.progrssBar.setVisibility(View.GONE);
                    projectBinding.rvHydroProject.setVisibility(View.GONE);
                    projectBinding.notConnectedView.setVisibility(View.VISIBLE);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                projectBinding.progrssBar.setVisibility(View.GONE);
                projectBinding.rvHydroProject.setVisibility(View.GONE);
                projectBinding.notConnectedView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnTryAgain)
        {
            finish();
            startActivity(getIntent());
        }
    }
}
