package humarahimachal.online.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import humarahimachal.online.Adapter.GeographicAdapter;
import humarahimachal.online.Data.GeographicData;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityGeographicBinding;

public class GeographicActivity extends AppCompatActivity {
ActivityGeographicBinding geographicBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographic);
        getSupportActionBar().setTitle(getResources().getString(R.string.geography));
        geographicBinding= DataBindingUtil.setContentView(this,R.layout.activity_geographic);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        geographicBinding.rvGeographic.setLayoutManager(layoutManager);
        GeographicAdapter geographicAdapter=new GeographicAdapter(this, GeographicData.getGeographicData(this));
        geographicBinding.rvGeographic.setAdapter(geographicAdapter);
    }
}
