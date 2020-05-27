package humarahimachal.online.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import humarahimachal.online.Modal.RiverModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.RiverviewBinding;

public class RiverAdapter extends RecyclerView.Adapter<RiverAdapter.RiverHolder> {

    Context context;
    ArrayList<RiverModal> riverInfo;

    public RiverAdapter(Context context, ArrayList<RiverModal> riverInfo) {
        this.context = context;
        this.riverInfo = riverInfo;
    }

    @NonNull
    @Override
    public RiverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RiverviewBinding riverviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.riverview, parent, false);

        return new RiverHolder(riverviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RiverHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.parentView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.itemView.parentView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

        holder.itemView.tvCatchmentHP.setText(riverInfo.get(position).getCatchmentArea());
        holder.itemView.tvExitHP.setText(riverInfo.get(position).getExitPlace());
        holder.itemView.tvLengthHp.setText(riverInfo.get(position).getRiverLength());
        holder.itemView.tvMainPlace.setText(riverInfo.get(position).getMainPlaces());
        holder.itemView.tvOrigin.setText(riverInfo.get(position).getOriginPlace());
        holder.itemView.tvSanskritName.setText(riverInfo.get(position).getSanskritName());
        holder.itemView.tvVedicName.setText(riverInfo.get(position).getVedicName());
        holder.itemView.tvTrubutary.setText(riverInfo.get(position).getTributaryRivers());
        holder.itemView.tvRiverName.setText(riverInfo.get(position).getRiverName());
        holder.itemView.tvOriginHp.setText(riverInfo.get(position).getOriginPlace());

    }

    @Override
    public int getItemCount() {
        return riverInfo.size();
    }

    public class RiverHolder extends RecyclerView.ViewHolder {

        RiverviewBinding itemView;

        public RiverHolder(@NonNull RiverviewBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
