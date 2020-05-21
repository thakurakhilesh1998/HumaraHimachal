package humarahimachal.online.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import humarahimachal.online.Modal.HydroModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.HydroprojectviewBinding;

public class HydroProjectAdapter extends RecyclerView.Adapter<HydroProjectAdapter.HydroHolder> {

    Context context;
    ArrayList<HydroModal> hydroList;

    public HydroProjectAdapter(Context context, ArrayList<HydroModal> hydroList) {
        this.context = context;
        this.hydroList = hydroList;
    }

    @NonNull
    @Override
    public HydroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HydroprojectviewBinding hydroprojectviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.hydroprojectview, parent, false);
        return new HydroHolder(hydroprojectviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HydroHolder holder, int position) {

        if(position%2==0)
        {
            holder.itemView.parentView.setBackgroundColor(context.getResources().getColor(R.color.lightAccentColor));
        }
        else
        {
            holder.itemView.parentView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.itemView.tvHydroName.setText(hydroList.get(position).getName());
        holder.itemView.tvCapacity.setText(hydroList.get(position).getCapacity());
        holder.itemView.tvCompany.setText(hydroList.get(position).getCompany());
        holder.itemView.tvDistrict.setText(hydroList.get(position).getDistrict());
        holder.itemView.tvStremRiver.setText(hydroList.get(position).getStream());
        holder.itemView.tvMainRiver.setText(hydroList.get(position).getRiver());
    }

    @Override
    public int getItemCount() {
        return hydroList.size();
    }

    public class HydroHolder extends RecyclerView.ViewHolder {
        HydroprojectviewBinding itemView;

        public HydroHolder(@NonNull HydroprojectviewBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
