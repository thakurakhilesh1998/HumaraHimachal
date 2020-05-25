package humarahimachal.online.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import humarahimachal.online.Modal.NewsDataModal;
import humarahimachal.online.R;
import humarahimachal.online.databinding.NewsitemBinding;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context context;
    ArrayList<NewsDataModal> newsList;

    public NewsAdapter(Context context, ArrayList<NewsDataModal> newsList) {

        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsitemBinding newsitemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.newsitem, parent, false);

        return new NewsHolder(newsitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        if (newsList.get(position) == null) {
            holder.newsitemBinding.ivNews.setImageResource(R.drawable.ic_news_icon);

        } else {

            Picasso.get().load(newsList.get(position).getImageUrl()).into(holder.newsitemBinding.ivNews);
        }
        if(newsList.get(position).getDesc()==null)
        {
            holder.newsitemBinding.tvDes.setText(context.getResources().getString(R.string.notavailable));
        }
        else
        {
            holder.newsitemBinding.tvDes.setText(newsList.get(position).getDesc());
        }
        
        holder.newsitemBinding.tvPublishDate.setText(newsList.get(position).getDate());
        holder.newsitemBinding.tvSource.setText(newsList.get(position).getSource());
        holder.newsitemBinding.tvTitle.setText(newsList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        NewsitemBinding newsitemBinding;

        public NewsHolder(@NonNull NewsitemBinding newsitemBinding) {
            super(newsitemBinding.getRoot());
            this.newsitemBinding = newsitemBinding;
        }
    }
}
