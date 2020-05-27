package humarahimachal.online.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import humarahimachal.online.Adapter.GeneralInfoAdapter;
import humarahimachal.online.Modal.GeneralInfoModal;
import humarahimachal.online.R;
import humarahimachal.online.Utils.FirebaseDataSorting;
import humarahimachal.online.databinding.AtglancefragmentBinding;

public class MinisterInfo extends Fragment {
    private static final String QUERY_DATA = "ministers";
    private static final String TAG = "ministers";
    AtglancefragmentBinding atglancefragmentBinding;
    ArrayList<GeneralInfoModal> ministerInfo;
    GeneralInfoAdapter generalInfoAdapter;
    FirebaseFirestore firebaseFirestore;
    Sprite doubleBounce;
    LinearLayout notConnectedView;
    Button btntrtAgain;

    public MinisterInfo() {

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
        notConnectedView = view.findViewById(R.id.notConnectedView);
        btntrtAgain = view.findViewById(R.id.btnTryAgain);
        firebaseFirestore = FirebaseFirestore.getInstance();
        doubleBounce = new Wave();
        atglancefragmentBinding.progrssBar.setIndeterminateDrawable(doubleBounce);
        atglancefragmentBinding.progrssBar.setVisibility(View.VISIBLE);
        ministerInfo = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        atglancefragmentBinding.rvatglance.setLayoutManager(layoutManager);
        generalInfoAdapter = new GeneralInfoAdapter(getContext(), ministerInfo);
        atglancefragmentBinding.rvatglance.setAdapter(generalInfoAdapter);
        getMinisterInfoFromFirebase();
    }

    private void getMinisterInfoFromFirebase() {

        firebaseFirestore.collection(QUERY_DATA).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    atglancefragmentBinding.progrssBar.setVisibility(View.GONE);
                    DocumentSnapshot d = queryDocumentSnapshots.getDocuments().get(0);
                    FirebaseDataSorting.getDataFromFirebase(d, ministerInfo);
                    generalInfoAdapter.notifyDataSetChanged();
                } else {
                    notConnectedView.setVisibility(View.VISIBLE);
                    atglancefragmentBinding.progrssBar.setVisibility(View.GONE);
                    atglancefragmentBinding.rvatglance.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                atglancefragmentBinding.progrssBar.setVisibility(View.GONE);
                atglancefragmentBinding.rvatglance.setVisibility(View.GONE);
                notConnectedView.setVisibility(View.VISIBLE);
            }
        });


    }
}
