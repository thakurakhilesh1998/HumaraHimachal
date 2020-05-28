package humarahimachal.online.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import com.google.firebase.firestore.QuerySnapshot;
import com.rd.animation.type.AnimationType;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer;

import java.util.ArrayList;

import humarahimachal.online.Adapter.DistrictsAdapter;
import humarahimachal.online.Modal.DistrictModal;
import humarahimachal.online.R;
import humarahimachal.online.Utils.CreateSnackBar;
import humarahimachal.online.databinding.ActivityDistrictBinding;

public class DistrictActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DistrictActivity";
    ActivityDistrictBinding districtBinding;
    FirebaseFirestore firebaseFirestore;
    ArrayList<DistrictModal> districtList;
    Sprite doubleBounce;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        getSupportActionBar().setTitle(getResources().getString(R.string.districtDaTa));
        firebaseFirestore = FirebaseFirestore.getInstance();
        districtBinding = DataBindingUtil.setContentView(this, R.layout.activity_district);
        findViewById(R.id.btnTryAgain).setOnClickListener(this);
        districtList = new ArrayList<>();
        districtBinding.viewpagerindicator.setAnimationType(AnimationType.FILL);
        CardFlipPageTransformer cardFlipPageTransformer = new CardFlipPageTransformer();
        cardFlipPageTransformer.setScalable(false);
        cardFlipPageTransformer.setFlipOrientation(CardFlipPageTransformer.VERTICAL);
        districtBinding.viewPager.setPageTransformer(true, cardFlipPageTransformer);
        doubleBounce = new Wave();
        setUpAdd();
        districtBinding.progrssBar.setIndeterminateDrawable(doubleBounce);
        districtBinding.progrssBar.setVisibility(View.VISIBLE);
        firebaseFirestore.collection(getResources().getString(R.string.districtDaTa)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    districtBinding.progrssBar.setVisibility(View.GONE);
                    findViewById(R.id.notConnectedView).setVisibility(View.VISIBLE);


                } else {
                    for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                        districtBinding.progrssBar.setVisibility(View.GONE);
                        districtBinding.districtview.setVisibility(View.VISIBLE);
                        DistrictModal districtData = d.toObject(DistrictModal.class);
                        districtList.add(districtData);
                    }

                    setAdapter();
                }

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                CreateSnackBar.createSnackBar(getApplicationContext(), districtBinding.parentDistrictView, e.getMessage());
                districtBinding.progrssBar.setVisibility(View.GONE);

            }
        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.i(TAG, String.valueOf(i));
            }
        });
    }

    private void setUpAdd() {

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstialid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void setAdapter() {
        DistrictsAdapter districtsAdapter = new DistrictsAdapter(getSupportFragmentManager(), districtList, getApplicationContext());
        districtBinding.viewPager.setAdapter(districtsAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTryAgain) {
            finish();
            startActivity(getIntent());
        }
    }
}
