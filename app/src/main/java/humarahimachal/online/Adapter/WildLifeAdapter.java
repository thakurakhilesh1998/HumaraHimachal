package humarahimachal.online.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import humarahimachal.online.Modal.WildLifeModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.WildlifeviewBinding;

public class WildLifeAdapter extends RecyclerView.Adapter<WildLifeAdapter.WildLifeHolder> {
    Context context;
    ArrayList<WildLifeModal> wildList;

    public WildLifeAdapter(Context context, ArrayList<WildLifeModal> wildList) {
        this.context = context;
        this.wildList = wildList;
    }

    @NonNull
    @Override
    public WildLifeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        WildlifeviewBinding wildlifeviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.wildlifeview, parent, false);
        return new WildLifeHolder(wildlifeviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WildLifeHolder holder, int position) {
        if(position%2==0)
        {
            holder.itemView.parentView.setBackgroundColor(context.getResources().getColor(R.color.lightAccentColor));
        }
        else
        {
            holder.itemView.parentView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.itemView.tvSanctuaryName.setText(wildList.get(position).getName());
        holder.itemView.tvWildArea.setText(wildList.get(position).getArea());
        holder.itemView.tvWildDistt.setText(wildList.get(position).getDistrict());
    }

    @Override
    public int getItemCount() {
        return wildList.size();
    }

    public class WildLifeHolder extends RecyclerView.ViewHolder {
        WildlifeviewBinding itemView;

        public WildLifeHolder(@NonNull WildlifeviewBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
