package humarahimachal.online.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import humarahimachal.online.Modal.TempleModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.TempleLayoutBinding;

public class TempleAdapter extends RecyclerView.Adapter<TempleAdapter.TempleHolder> {
    ArrayList<TempleModal> templeList;
    Context context;

    public TempleAdapter(ArrayList<TempleModal> templeList, Context context) {
        this.templeList = templeList;
        this.context = context;
    }

    @NonNull
    @Override
    public TempleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TempleLayoutBinding templeLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.temple_layout, parent, false);
        return new TempleHolder(templeLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TempleHolder holder, int position) {
        holder.itemView.tvDistricts.setText("Situated in District:" + templeList.get(position).getDistrict());
        holder.itemView.tvName.setText(templeList.get(position).getName());
        holder.itemView.tvInfo.setText(templeList.get(position).getInfo());
        Picasso.get().load(templeList.get(position).getImagelink()).into(holder.itemView.ivTempleImage);
    }

    @Override
    public int getItemCount() {
        return templeList.size();
    }

    public class TempleHolder extends RecyclerView.ViewHolder {
        TempleLayoutBinding itemView;

        public TempleHolder(@NonNull TempleLayoutBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
