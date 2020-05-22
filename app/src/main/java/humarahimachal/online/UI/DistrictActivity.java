package humarahimachal.online.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.rd.animation.type.AnimationType;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer;

import humarahimachal.online.Adapter.TabAdapter;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityDistrictBinding;

public class DistrictActivity extends AppCompatActivity {
    ActivityDistrictBinding districtBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        districtBinding = DataBindingUtil.setContentView(this, R.layout.activity_district);
        districtBinding.viewpagerindicator.setAnimationType(AnimationType.FILL);
        TabAdapter fairAndFestivalAdapter = new TabAdapter(getSupportFragmentManager());
        districtBinding.viewPager.setAdapter(fairAndFestivalAdapter);
        CardFlipPageTransformer cardFlipPageTransformer = new CardFlipPageTransformer();
        cardFlipPageTransformer.setScalable(false);
        cardFlipPageTransformer.setFlipOrientation(CardFlipPageTransformer.VERTICAL);
        districtBinding.viewPager.setPageTransformer(true, cardFlipPageTransformer);
    }
}
