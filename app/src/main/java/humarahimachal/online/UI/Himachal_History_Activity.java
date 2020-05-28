package humarahimachal.online.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.rd.animation.type.AnimationType;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer;

import java.util.ArrayList;

import humarahimachal.online.Adapter.HistoryAdapter;
import humarahimachal.online.Modal.HistoryData;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityHimachalHistoryBinding;

public class Himachal_History_Activity extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore firebaseFirestore;
    Button btnTryAgain;
    Sprite progressBar;
    LinearLayout notConnectedToInternet;
    private ArrayList<HistoryData> historyList;
    private ActivityHimachalHistoryBinding historyBinding;
    private InterstitialAd mInterstitialAd;
    private static final String SORT_PARA="name";
    private static final String SORT_TYPE="asc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himachal__history_);
        historyBinding = DataBindingUtil.setContentView(this, R.layout.activity_himachal__history_);
        getSupportActionBar().setTitle(getResources().getString(R.string.history));
        progressBar = new Wave();
        notConnectedToInternet = findViewById(R.id.notConnectedView);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(this);
        historyBinding.progrssBar.setIndeterminateDrawable(progressBar);
        historyBinding.progrssBar.setVisibility(View.VISIBLE);
        firebaseFirestore = FirebaseFirestore.getInstance();
        historyList = new ArrayList<>();
        setUpViewPager();
        setUpAdd();
        fetchDataFromFirestore();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.i("add", "add failed to laod" + i);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });

    }

    private void setUpViewPager() {

        historyBinding.viewpagerindicator.setAnimationType(AnimationType.FILL);
        CardFlipPageTransformer cardFlipPageTransformer = new CardFlipPageTransformer();
        cardFlipPageTransformer.setScalable(false);
        cardFlipPageTransformer.setFlipOrientation(CardFlipPageTransformer.VERTICAL);
        historyBinding.viewPager.setPageTransformer(true, cardFlipPageTransformer);
    }

    private void fetchDataFromFirestore() {
        firebaseFirestore.collection(getResources().getString(R.string.historydatafirebase)).orderBy(SORT_PARA, Query.Direction.ASCENDING)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    historyBinding.progrssBar.setVisibility(View.GONE);
                    for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                        historyBinding.districtview.setVisibility(View.VISIBLE);
                        HistoryData historyData = d.toObject(HistoryData.class);
                        historyList.add(historyData);
                    }
                    setAdaptet();
                } else {
                    notConnectedToInternet.setVisibility(View.VISIBLE);
                    historyBinding.progrssBar.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                notConnectedToInternet.setVisibility(View.VISIBLE);
                historyBinding.progrssBar.setVisibility(View.GONE);
            }
        });

    }

    private void setAdaptet() {

        HistoryAdapter historyAdapter = new HistoryAdapter(getSupportFragmentManager(), historyList, getApplicationContext());
        historyBinding.viewPager.setAdapter(historyAdapter);
    }

    private void setUpAdd() {

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
