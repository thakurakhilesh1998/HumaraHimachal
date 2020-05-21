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
import humarahimachal.online.UI.HydroProjectActivity;
import humarahimachal.online.UI.RiverActivity;
import humarahimachal.online.UI.TempleActivity;
import humarahimachal.online.UI.WildLifeSantuaryActivity;
import humarahimachal.online.databinding.AboutviewBinding;

public class GeographicAdapter extends RecyclerView.Adapter<GeographicAdapter.GeographicHolder> {
    Context context;
    ArrayList<AboutModal> geographicData;

    public GeographicAdapter(Context context, ArrayList<AboutModal> geographicData) {
        this.context = context;
        this.geographicData = geographicData;
    }

    @NonNull
    @Override
    public GeographicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AboutviewBinding aboutviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.aboutview, parent, false);
        return new GeographicHolder(aboutviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GeographicHolder holder, final int position) {

        if (position % 2 == 0) {
            holder.itemView.parentview.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.itemView.parentview.setBackgroundColor(context.getResources().getColor(R.color.lightAccentColor));
        }
        holder.itemView.ivImageAbout.setImageResource(geographicData.get(position).getImageAbout());
        holder.itemView.tvAboutName.setText(geographicData.get(position).getAboutName());
        holder.itemView.parentview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, RiverActivity.class));
                        break;
                    case 1:
                        Intent temple = new Intent(context, TempleActivity.class);
                        temple.putExtra(context.getResources().getString(R.string.geographicextra), context.getResources().getString(R.string.templecheck));
                        context.startActivity(temple);
                        break;
                    case 2:
                        Intent lakes = new Intent(context, TempleActivity.class);
                        lakes.putExtra(context.getResources().getString(R.string.geographicextra), context.getResources().getString(R.string.lakescheck));
                        context.startActivity(lakes);
                        break;
                    case 3:
                        context.startActivity(new Intent(context, HydroProjectActivity.class));
                        break;
                    case 4:
                        Intent nationalPark=new Intent(context,TempleActivity.class);
                        nationalPark.putExtra(context.getResources().getString(R.string.geographicextra),context.getResources().getString(R.string.nationalparkcheck));
                        context.startActivity(nationalPark);
                        break;
                    case 5:
                        context.startActivity(new Intent(context,WildLifeSantuaryActivity.class));
                        break;
                    case 6:
                        Intent heritage=new Intent(context,TempleActivity.class);
                        heritage.putExtra(context.getResources().getString(R.string.geographicextra),context.getResources().getString(R.string.heritagecheck));
                        context.startActivity(heritage);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return geographicData.size();
    }

    public class GeographicHolder extends RecyclerView.ViewHolder {
        AboutviewBinding itemView;

        public GeographicHolder(@NonNull AboutviewBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
