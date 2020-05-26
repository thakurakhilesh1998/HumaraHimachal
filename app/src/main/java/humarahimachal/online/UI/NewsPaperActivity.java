package humarahimachal.online.UI;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import humarahimachal.online.Modal.NewsPaperModal;
import humarahimachal.online.R;
import humarahimachal.online.Utils.NewsNetworikUtil;
import humarahimachal.online.databinding.ActivityNewsPaperBinding;

public class NewsPaperActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NewsPaperActivity";
    FirebaseFirestore firebaseFirestore;
    ActivityNewsPaperBinding newspaperBinding;
    RemotePDFViewPager remotePDFViewPager;
    PDFPagerAdapter pdfPagerAdapter;
    Sprite doubleBounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_paper);
        getSupportActionBar().setTitle(getResources().getString(R.string.weeklynewspaper));
        doubleBounce = new Wave();

        firebaseFirestore = FirebaseFirestore.getInstance();
        newspaperBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_paper);
        newspaperBinding.progrssBar.setIndeterminateDrawable(doubleBounce);
        if (NewsNetworikUtil.isConnectedToInternet(this)) {
            newspaperBinding.progrssBar.setVisibility(View.VISIBLE);
            getDataFromFirebase();
        } else {
            newspaperBinding.notConnectedView.setVisibility(View.VISIBLE);
        }
        newspaperBinding.btnTryAgain.setOnClickListener(this);

    }

    private void getDataFromFirebase() {

        firebaseFirestore.collection(getResources().getString(R.string.news_paper)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                    NewsPaperModal newsPaperModal = d.toObject(NewsPaperModal.class);
                    remotePDFViewPager = new RemotePDFViewPager(getApplicationContext(), newsPaperModal.getUrl(), new DownloadFile.Listener() {
                        @Override
                        public void onSuccess(String url, String destinationPath) {
                            newspaperBinding.progrssBar.setVisibility(View.GONE);
                            pdfPagerAdapter = new PDFPagerAdapter(getApplicationContext(), destinationPath);
                            remotePDFViewPager.setAdapter(pdfPagerAdapter);
                            setContentView(remotePDFViewPager);
                        }

                        @Override
                        public void onFailure(Exception e) {
                            newspaperBinding.notConnectedView.setVisibility(View.VISIBLE);
                            newspaperBinding.progrssBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onProgressUpdate(int progress, int total) {

                        }
                    });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                newspaperBinding.notConnectedView.setVisibility(View.VISIBLE);
                newspaperBinding.progrssBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTryAgain) {
            finish();
            startActivity(getIntent());
        }
    }
}
