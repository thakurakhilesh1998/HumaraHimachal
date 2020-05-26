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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import humarahimachal.online.BuildConfig;
import humarahimachal.online.R;
import humarahimachal.online.UI.LoginActivity;
import humarahimachal.online.Utils.CreateSnackBar;
import humarahimachal.online.databinding.SettingFragmentBinding;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private static final String MSG_RESET = "Password reset email sent to your email";
    SettingFragmentBinding settingFragmentBinding;
    FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        settingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.setting_fragment, container, false);
        return settingFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settingFragmentBinding.logout.setOnClickListener(this);
        settingFragmentBinding.share.setOnClickListener(this);
        settingFragmentBinding.changepassword.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth != null) {
            settingFragmentBinding.tvName.setText(mAuth.getCurrentUser().getDisplayName());
            settingFragmentBinding.tvEmail.setText(mAuth.getCurrentUser().getEmail());
            if (mAuth.getCurrentUser().getPhotoUrl() != null) {
                Picasso.get().load(mAuth.getCurrentUser().getPhotoUrl()).into(settingFragmentBinding.ivAccount);
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                onLogOut();
                break;
            case R.id.changepassword:
                onChangePassword();
                break;
            case R.id.share:
                onShare();
        }
    }

    private void onShare() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void onChangePassword() {
        if (mAuth != null) {
            mAuth.sendPasswordResetEmail(mAuth.getCurrentUser().getEmail()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    CreateSnackBar.createSnackBar(getContext(), settingFragmentBinding.parentView, MSG_RESET);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    CreateSnackBar.createSnackBar(getContext(), settingFragmentBinding.parentView, e.getMessage());
                }
            });
        }
    }

    private void onLogOut() {
        mAuth.signOut();
        getActivity().finish();
        Intent login = new Intent(getContext(), LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
    }
}
