package humarahimachal.online.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import humarahimachal.online.Modal.GeneralInfoModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.AtglancehpBinding;

public class GeneralInfoAdapter extends RecyclerView.Adapter<GeneralInfoAdapter.GeneralInfoHolder> {
    Context context;
    ArrayList<GeneralInfoModal> generalData;


    public GeneralInfoAdapter(Context context, ArrayList<GeneralInfoModal> generalData) {
        this.context = context;
        this.generalData = generalData;
    }

    @NonNull
    @Override
    public GeneralInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AtglancehpBinding atglancehpBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.atglancehp, parent, false);
        return new GeneralInfoHolder(atglancehpBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralInfoHolder holder, int position) {
        holder.atglancehpBinding.tvName.setText(generalData.get(position).getName());
        holder.atglancehpBinding.tvValue.setText(generalData.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return generalData.size();
    }

    public class GeneralInfoHolder extends RecyclerView.ViewHolder {
        AtglancehpBinding atglancehpBinding;

        public GeneralInfoHolder(@NonNull AtglancehpBinding atglancehpBinding) {
            super(atglancehpBinding.getRoot());
        }
    }
}
