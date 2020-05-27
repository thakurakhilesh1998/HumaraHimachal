package humarahimachal.online.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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

import humarahimachal.online.Adapter.TempleAdapter;
import humarahimachal.online.Modal.TempleModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityTempleBinding;

public class TempleActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TempleActivity";
    private static String loadData = "";
    ActivityTempleBinding templeBinding;
    ArrayList<TempleModal> templeList;
    TempleAdapter templeAdapter;
    FirebaseFirestore firebaseFirestore;
    Sprite doubleBounce;
    LinearLayout mNotConnectedInternet;
    Button btnTryAgain;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple);
        templeBinding = DataBindingUtil.setContentView(this, R.layout.activity_temple);
        mNotConnectedInternet = findViewById(R.id.notConnectedView);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(this);
        Intent i = getIntent();
        if (i != null) {
            loadData = i.getStringExtra(getResources().getString(R.string.geographicextra));
        }
        getSupportActionBar().setTitle(loadData);
        setUpAdd();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        templeBinding.rvTemple.setLayoutManager(layoutManager);
        firebaseFirestore = FirebaseFirestore.getInstance();
        templeList = new ArrayList<>();
        templeAdapter = new TempleAdapter(templeList, this);
        templeBinding.rvTemple.setAdapter(templeAdapter);
        doubleBounce = new Wave();
        templeBinding.progrssBar.setIndeterminateDrawable(doubleBounce);
        templeBinding.progrssBar.setVisibility(View.VISIBLE);
        fetchDataFromFireStore();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });
    }

    private void fetchDataFromFireStore() {

        firebaseFirestore.collection(loadData).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    templeBinding.progrssBar.setVisibility(View.GONE);
                    for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                        TempleModal templeModal = d.toObject(TempleModal.class);
                        templeList.add(templeModal);
                    }
                    templeAdapter.notifyDataSetChanged();
                } else {
                    mNotConnectedInternet.setVisibility(View.VISIBLE);
                    templeBinding.progrssBar.setVisibility(View.GONE);
                    templeBinding.rvTemple.setVisibility(View.GONE);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mNotConnectedInternet.setVisibility(View.VISIBLE);
                templeBinding.rvTemple.setVisibility(View.GONE);
                templeBinding.progrssBar.setVisibility(View.GONE);
            }
        });
    }

    private void setUpAdd() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        templeBinding.adView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstialid));
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
