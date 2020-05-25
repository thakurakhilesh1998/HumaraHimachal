package humarahimachal.online.UI;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
import humarahimachal.online.databinding.ActivityDistrictBinding;

public class DistrictActivity extends AppCompatActivity {
    private static final String TAG = "DistrictActivity";
    ActivityDistrictBinding districtBinding;
    FirebaseFirestore firebaseFirestore;
    ArrayList<DistrictModal> districtList;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loadingData));
        progressDialog.setCancelable(false);
        progressDialog.show();
        getSupportActionBar().setTitle(getResources().getString(R.string.districtDaTa));
        firebaseFirestore = FirebaseFirestore.getInstance();
        districtBinding = DataBindingUtil.setContentView(this, R.layout.activity_district);
        districtList = new ArrayList<>();
        districtBinding.viewpagerindicator.setAnimationType(AnimationType.FILL);
        CardFlipPageTransformer cardFlipPageTransformer = new CardFlipPageTransformer();
        cardFlipPageTransformer.setScalable(false);
        cardFlipPageTransformer.setFlipOrientation(CardFlipPageTransformer.VERTICAL);
        districtBinding.viewPager.setPageTransformer(true, cardFlipPageTransformer);
        firebaseFirestore.collection(getResources().getString(R.string.districtDaTa)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                progressDialog.dismiss();
                for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                    DistrictModal districtData = d.toObject(DistrictModal.class);
                    districtList.add(districtData);
                }

                setAdapter();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void setAdapter() {
        DistrictsAdapter districtsAdapter = new DistrictsAdapter(getSupportFragmentManager(), districtList, getApplicationContext());
        districtBinding.viewPager.setAdapter(districtsAdapter);
    }
}
