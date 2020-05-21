package humarahimachal.online.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayout;

import humarahimachal.online.Adapter.GlanceTabAdapter;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityHimachalAtGlanceBinding;

public class Himachal_At_Glance_Activity extends AppCompatActivity {

    private static String TAG = "atgalnce";
    ActivityHimachalAtGlanceBinding atGlanceBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himachal__at__glance_);
        getSupportActionBar().hide();
        atGlanceBinding = DataBindingUtil.setContentView(this, R.layout.activity_himachal__at__glance_);
        GlanceTabAdapter glanceTabAdapter = new GlanceTabAdapter(getSupportFragmentManager());
        atGlanceBinding.viewPager.setAdapter(glanceTabAdapter);
        atGlanceBinding.viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(atGlanceBinding.tabLayout));
        atGlanceBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                atGlanceBinding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                atGlanceBinding.viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }
}
