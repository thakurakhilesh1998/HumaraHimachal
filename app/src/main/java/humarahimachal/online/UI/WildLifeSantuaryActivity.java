package humarahimachal.online.UI;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
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

import humarahimachal.online.Adapter.WildLifeAdapter;
import humarahimachal.online.Modal.WildLifeModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityWildLifeSantuaryBinding;

public class WildLifeSantuaryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "wildlife";
    private static final String TITLE = "Wild Life Sanctuary";
    private static final String FIRESTORE_WILDLIFE = "wildlifesanctuary";
    ArrayList<WildLifeModal> wildLifeList;
    FirebaseFirestore firebaseFirestore;
    ActivityWildLifeSantuaryBinding wildlifeBinding;
    WildLifeAdapter wildLifeAdapter;
    Sprite doubleBounce;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wild_life_santuary);
        wildlifeBinding = DataBindingUtil.setContentView(this, R.layout.activity_wild_life_santuary);
        getSupportActionBar().setTitle(TITLE);
        wildLifeList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        wildlifeBinding.rvWildLife.setLayoutManager(layoutManager);
        wildLifeAdapter = new WildLifeAdapter(this, wildLifeList);
        wildlifeBinding.rvWildLife.setAdapter(wildLifeAdapter);
        doubleBounce = new Wave();
        wildlifeBinding.progrssBar.setIndeterminateDrawable(doubleBounce);
        wildlifeBinding.progrssBar.setVisibility(View.VISIBLE);
        wildlifeBinding.btnTryAgain.setOnClickListener(this);
        addAds();
        getDataFromFireStore();
    }

    private void getDataFromFireStore() {
        firebaseFirestore.collection(FIRESTORE_WILDLIFE).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {
                    wildlifeBinding.progrssBar.setVisibility(View.GONE);
                    wildlifeBinding.rvWildLife.setVisibility(View.VISIBLE);
                    for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                        WildLifeModal wildLifeModal = d.toObject(WildLifeModal.class);
                        wildLifeList.add(wildLifeModal);
                    }
                    wildLifeAdapter.notifyDataSetChanged();
                } else {
                    wildlifeBinding.rvWildLife.setVisibility(View.GONE);
                    wildlifeBinding.progrssBar.setVisibility(View.GONE);
                    wildlifeBinding.notConnectedView.setVisibility(View.VISIBLE);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                wildlifeBinding.progrssBar.setVisibility(View.GONE);
                wildlifeBinding.rvWildLife.setVisibility(View.GONE);
                wildlifeBinding.notConnectedView.setVisibility(View.VISIBLE);
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
        wildlifeBinding.adView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstialid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnTryAgain) {
            finish();
            startActivity(getIntent());
        }
    }
}
