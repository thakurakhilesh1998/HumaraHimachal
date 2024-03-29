package humarahimachal.online.UI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cz.intik.overflowindicator.SimpleSnapHelper;
import humarahimachal.online.Adapter.NewsAdapter;
import humarahimachal.online.Modal.NewsDataModal;
import humarahimachal.online.R;
import humarahimachal.online.Utils.NewsNetworikUtil;
import humarahimachal.online.databinding.ActivityNewsFeedBinding;

public class NewsFeedActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String ARTICLES = "articles";
    private static final String SOURCES = "source";
    private static final String URL_TO_IMAGE = "urlToImage";
    private static final String TITLE = "title";
    private static final String DES = "description";
    private static final String PUBLISH_AT = "publishedAt";
    private static final String NAME = "name";
    private static final String URL = "url";
    private static final String TAG = "NewsFeed";
    ActivityNewsFeedBinding newsFeedBinding;
    ArrayList<NewsDataModal> newsList;
    NewsAdapter newsAdapter;
    NewsFeedLoader newsDataLoader;
    Sprite doubleBounce;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        newsFeedBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_feed);
        getSupportActionBar().setTitle(getResources().getString(R.string.newsfeed));
        doubleBounce = new Wave();
        newsFeedBinding.progrssBar.setIndeterminateDrawable(doubleBounce);
        newsDataLoader = new NewsFeedLoader();
        newsList = new ArrayList<>();
        setUpAdd();
        newsFeedBinding.btnTryAgain.setOnClickListener(this);
        if (NewsNetworikUtil.isConnectedToInternet(this)) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            newsFeedBinding.rvNewsArticles.setLayoutManager(layoutManager);
            newsAdapter = new NewsAdapter(this, newsList);
            newsFeedBinding.rvNewsArticles.setAdapter(newsAdapter);
            addCardLikeFunctionality();
            newsDataLoader.execute();
        } else {
            newsFeedBinding.relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            newsFeedBinding.notConnectedView.setVisibility(View.VISIBLE);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });
    }

    private void addCardLikeFunctionality() {
        newsFeedBinding.viewPagerIndicator.attachToRecyclerView(newsFeedBinding.rvNewsArticles);
        SimpleSnapHelper pageSnapHelper = new SimpleSnapHelper(newsFeedBinding.viewPagerIndicator);
        newsFeedBinding.rvNewsArticles.setOnFlingListener(null);
        pageSnapHelper.attachToRecyclerView(newsFeedBinding.rvNewsArticles);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTryAgain) {
            finish();
            startActivity(getIntent());
        }
    }

    private void setUpAdd() {

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstialid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public class NewsFeedLoader extends AsyncTask<Void, Void, ArrayList<NewsDataModal>> {

        @Override
        protected ArrayList<NewsDataModal> doInBackground(Void... voids) {
            try {
                URL url = NewsNetworikUtil.getUrl();
                Log.i(TAG, url.toString());
                setDataToList(NewsNetworikUtil.sendRequestToServer(url));

            } catch (MalformedURLException | JSONException e) {
                e.printStackTrace();
                return null;
            }

            return newsList;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsDataModal> newsDataModals) {
            super.onPostExecute(newsDataModals);
            Log.i(TAG, String.valueOf(newsList.size()));
            if (newsList.size() > 0) {
                newsFeedBinding.rvNewsArticles.setVisibility(View.VISIBLE);
                newsFeedBinding.progrssBar.setVisibility(View.GONE);
                newsAdapter.notifyDataSetChanged();
            } else {
                newsFeedBinding.relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                newsFeedBinding.notConnectedView.setVisibility(View.VISIBLE);
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsFeedBinding.progrssBar.setVisibility(View.VISIBLE);
        }

        private void setDataToList(String data) throws JSONException {
            JSONObject response = new JSONObject(data);
            JSONArray articles = response.getJSONArray(ARTICLES);
            for (int i = 0; i < articles.length(); i++) {
                JSONObject object = articles.getJSONObject(i);
                JSONObject name = object.getJSONObject(SOURCES);
                newsList.add(new NewsDataModal(object.getString(URL_TO_IMAGE), object.getString(TITLE),
                        object.getString(DES), object.getString(PUBLISH_AT), name.getString(NAME),
                        object.getString(URL)));
            }
        }

    }
}
