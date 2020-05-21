package humarahimachal.online.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import humarahimachal.online.Adapter.RiverAdapter;
import humarahimachal.online.Data.RiverData;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityRiverBinding;

public class RiverActivity extends AppCompatActivity {
    ActivityRiverBinding riverBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river);
        getSupportActionBar().setTitle(getResources().getString(R.string.riverstitle));
        riverBinding= DataBindingUtil.setContentView(this,R.layout.activity_river);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        riverBinding.rvRivers.setLayoutManager(layoutManager);
        RiverAdapter riverAdapter=new RiverAdapter(this, RiverData.getRiverData(this));
        riverBinding.rvRivers.setAdapter(riverAdapter);
    }
}
