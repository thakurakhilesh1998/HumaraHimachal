package humarahimachal.online.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import humarahimachal.online.Modal.DistrictModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.DistrictviewBinding;

public class DistrictFragment extends Fragment {

    DistrictviewBinding districtviewBinding;
    DistrictModal districtModal;

    public DistrictFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        districtviewBinding = DataBindingUtil.inflate(inflater, R.layout.districtview, container, false);
        View view = districtviewBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        districtModal = (DistrictModal) getArguments().get(getResources().getString(R.string.districtfragment));
        districtviewBinding.tvName.setText(districtModal.getName());
        districtviewBinding.tvHistory.setText(districtModal.getHistory());
        districtviewBinding.tvReorganized.setText(districtModal.getIncluded());
        if (districtModal.getOldname() != null) {
            districtviewBinding.tvAncientName.setText(districtModal.getOldname());
        } else {
            districtviewBinding.tvAncientName.setVisibility(View.GONE);
            districtviewBinding.tvAncientPlace.setVisibility(View.GONE);
        }
        districtviewBinding.tvheadquarter.setText(districtModal.getHq());
        districtviewBinding.tvDc.setText(districtModal.getDc());
        districtviewBinding.tvArea.setText(districtModal.getArea());
        districtviewBinding.tvPopulation.setText(districtModal.getPop());
        districtviewBinding.tvPopulationMale.setText(districtModal.getMale());
        districtviewBinding.tvPopulationFeMale.setText(districtModal.getFemale());
        districtviewBinding.tvPopDensity.setText(districtModal.getDensity());
        if (districtModal.getRiver() != null) {
            districtviewBinding.tvRiver.setText(districtModal.getRiver());
        } else {
            districtviewBinding.tvRiver.setVisibility(View.GONE);
            districtviewBinding.tvRiverPlace.setVisibility(View.GONE);
        }

        districtviewBinding.tvAssConst.setText(districtModal.getSubdivision());
        districtviewBinding.tvTeh.setText(districtModal.getTeh());
        districtviewBinding.tvNeigh.setText(districtModal.getNeigh());
        districtviewBinding.tvWeb.setText(Html.fromHtml("<u>" + districtModal.getWeb() + "</u>"));
        districtviewBinding.tvWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(districtModal.getWeb()));
                startActivity(i);
            }
        });


    }
}
