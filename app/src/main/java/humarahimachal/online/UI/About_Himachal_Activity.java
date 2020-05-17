package humarahimachal.online.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import humarahimachal.online.Adapter.AboutHimachalAdapter;
import humarahimachal.online.Data.AboutHimachalData;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityAboutHimachalActivityBinding;

public class About_Himachal_Activity extends AppCompatActivity {
    ActivityAboutHimachalActivityBinding activityAboutHimachalActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_himachal_activity);
        getSupportActionBar().setTitle(getResources().getString(R.string.about));
        activityAboutHimachalActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_himachal_activity);
        AboutHimachalAdapter aboutHimachalAdapter = new AboutHimachalAdapter(this, AboutHimachalData.getHimachalAboutData(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        activityAboutHimachalActivityBinding.rvAboutHimachal.setLayoutManager(linearLayoutManager);
        activityAboutHimachalActivityBinding.rvAboutHimachal.setAdapter(aboutHimachalAdapter);
    }
}
