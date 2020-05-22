package humarahimachal.online.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import humarahimachal.online.Adapter.TempleAdapter;
import humarahimachal.online.Modal.TempleModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.ActivityTempleBinding;

public class TempleActivity extends AppCompatActivity {
    private static String loadData="";
    private static final String TAG = "TempleActivity";
    ActivityTempleBinding templeBinding;
    ArrayList<TempleModal> templeList;
    TempleAdapter templeAdapter;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple);
        templeBinding = DataBindingUtil.setContentView(this, R.layout.activity_temple);
        Intent i=getIntent();
        if(i!=null)
        {
            loadData=i.getStringExtra(getResources().getString(R.string.geographicextra));
        }
        getSupportActionBar().setTitle(loadData);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loadingData));
        progressDialog.setCancelable(false);
        progressDialog.show();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        templeBinding.rvTemple.setLayoutManager(layoutManager);
        firebaseFirestore = FirebaseFirestore.getInstance();
        templeList = new ArrayList<>();
        templeAdapter = new TempleAdapter(templeList, this);
        templeBinding.rvTemple.setAdapter(templeAdapter);
        fetchDataFromFireStore();
    }

    private void fetchDataFromFireStore() {

        firebaseFirestore.collection(loadData).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                    progressDialog.dismiss();
                    TempleModal templeModal = d.toObject(TempleModal.class);
                    templeList.add(templeModal);
                }
                templeAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Log.i(TAG, e.getMessage());
            }
        });
    }
}
