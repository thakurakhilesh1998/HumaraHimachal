package humarahimachal.online.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import humarahimachal.online.R;
import humarahimachal.online.UI.About_Himachal_Activity;
import humarahimachal.online.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {
    HomeFragmentBinding homeBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        View v = homeBinding.getRoot();
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeBinding.about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about:
                openAboutActivity();
        }
    }

    private void openAboutActivity() {

        startActivity(new Intent(getContext(), About_Himachal_Activity.class));
    }
}
