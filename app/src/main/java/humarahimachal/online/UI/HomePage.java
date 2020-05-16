package humarahimachal.online.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import humarahimachal.online.Adapter.TabAdapter;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityHomePageBinding;

public class HomePage extends AppCompatActivity {
    ActivityHomePageBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        homeBinding= DataBindingUtil.setContentView(this, R.layout.activity_home_page);
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        homeBinding.viewPager.setAdapter(tabAdapter);
        homeBinding.viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeBinding.tabLayout));
        homeBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homeBinding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                homeBinding.viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }
}
