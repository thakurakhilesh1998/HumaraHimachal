package humarahimachal.online.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import humarahimachal.online.Adapter.RiverAdapter;
import humarahimachal.online.Data.RiverData;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityRiverBinding;

public class RiverActivity extends AppCompatActivity {
    ActivityRiverBinding riverBinding;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river);
        getSupportActionBar().setTitle(getResources().getString(R.string.riverstitle));
        riverBinding = DataBindingUtil.setContentView(this, R.layout.activity_river);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        riverBinding.rvRivers.setLayoutManager(layoutManager);
        RiverAdapter riverAdapter = new RiverAdapter(this, RiverData.getRiverData(this));
        riverBinding.rvRivers.setAdapter(riverAdapter);
        setUpAdd();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
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
        riverBinding.adView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstialid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
