package humarahimachal.online.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import humarahimachal.online.Adapter.GeneralInfoAdapter;
import humarahimachal.online.Modal.GeneralInfoModal;
import humarahimachal.online.R;
import humarahimachal.online.Utils.FirebaseDataSorting;
import humarahimachal.online.databinding.AtglancefragmentBinding;

public class GeneralInfo extends Fragment {
    private static String GENERAL_INFO = "generalinfo";
    private static String TAG = "GeneralInfo";
    FirebaseFirestore firebaseFirestore;
    AtglancefragmentBinding atglancefragmentBinding;
    ArrayList<GeneralInfoModal> generalInfoModals;
    GeneralInfoAdapter generalInfoAdapter;
    ProgressDialog progressDialog;

    public GeneralInfo() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        atglancefragmentBinding = DataBindingUtil.inflate(inflater, R.layout.atglancefragment, container, false);
        View v = atglancefragmentBinding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage(FirebaseDataSorting.waiting);
        progressDialog.show();
        progressDialog.setCancelable(false);
        generalInfoModals = new ArrayList<>();
        generalInfoAdapter = new GeneralInfoAdapter(getContext(), generalInfoModals);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        atglancefragmentBinding.rvatglance.setLayoutManager(layoutManager);
        atglancefragmentBinding.rvatglance.setAdapter(generalInfoAdapter);
        getDataFromFirestore();
    }


    private void getDataFromFirestore() {
        firebaseFirestore.collection(GENERAL_INFO).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    progressDialog.dismiss();
                    DocumentSnapshot d = queryDocumentSnapshots.getDocuments().get(0);
                    FirebaseDataSorting.getDataFromFirebase(d,generalInfoModals);
                    generalInfoAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Log.i("tag", e.getMessage());
            }
        });
    }
}
