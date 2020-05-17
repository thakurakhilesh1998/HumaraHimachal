package humarahimachal.online.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import humarahimachal.online.R;

public class Himachal_History_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himachal__history_);
        getSupportActionBar().setTitle(getResources().getString(R.string.history));
    }
}
