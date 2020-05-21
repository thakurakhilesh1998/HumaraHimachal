package humarahimachal.online.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import humarahimachal.online.Modal.AboutModal;
import humarahimachal.online.R;
import humarahimachal.online.UI.GeographicActivity;
import humarahimachal.online.UI.Himachal_At_Glance_Activity;
import humarahimachal.online.UI.Himachal_History_Activity;
import humarahimachal.online.databinding.AboutviewBinding;

public class AboutHimachalAdapter extends RecyclerView.Adapter<AboutHimachalAdapter.AboutHolder> {

    AboutviewBinding aboutviewBinding;
    Context context;
    ArrayList<AboutModal> aboutModal;

    public AboutHimachalAdapter(Context context, ArrayList<AboutModal> aboutModal) {
        this.context = context;
        this.aboutModal = aboutModal;
    }


    @NonNull
    @Override
    public AboutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AboutviewBinding aboutviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.aboutview, parent, false);
        return new AboutHolder(aboutviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutHolder holder, final int position) {
        if (position % 2 == 0) {
            holder.aboutviewBinding.parentview.setBackgroundColor(context.getResources().getColor(R.color.lightAccentColor));
        } else {
            holder.aboutviewBinding.parentview.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.aboutviewBinding.ivImageAbout.setImageResource(aboutModal.get(position).getImageAbout());
        holder.aboutviewBinding.tvAboutName.setText(aboutModal.get(position).getAboutName());
        holder.aboutviewBinding.parentview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, Himachal_History_Activity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, Himachal_At_Glance_Activity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, GeographicActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return aboutModal.size();
    }

    public class AboutHolder extends RecyclerView.ViewHolder {
        public AboutviewBinding aboutviewBinding;

        public AboutHolder(@NonNull AboutviewBinding aboutviewBinding) {
            super(aboutviewBinding.getRoot());
            this.aboutviewBinding = aboutviewBinding;
        }
    }
}
