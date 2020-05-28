package humarahimachal.online.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import humarahimachal.online.Fragments.HistoryView;
import humarahimachal.online.Modal.HistoryData;
import humarahimachal.online.R;

public class HistoryAdapter extends FragmentPagerAdapter {
    ArrayList<HistoryData> historyData;
    Context context;

    public HistoryAdapter(@NonNull FragmentManager fm, ArrayList<HistoryData> historyData, Context context) {
        super(fm);
        this.historyData = historyData;
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HistoryView first = new HistoryView();
                sendDataToFragment(first, 0);
                return first;
            case 1:
                HistoryView second = new HistoryView();
                sendDataToFragment(second, 1);
                return second;
            case 2:
                HistoryView third = new HistoryView();
                sendDataToFragment(third, 2);
                return third;
            case 3:
                HistoryView fourth = new HistoryView();
                sendDataToFragment(fourth, 3);
                return fourth;
            case 4:
                HistoryView five = new HistoryView();
                sendDataToFragment(five, 4);
                return five;
            case 5:
                HistoryView six = new HistoryView();
                sendDataToFragment(six, 5);
                return six;
            case 6:
                HistoryView seven = new HistoryView();
                sendDataToFragment(seven, 6);
                return seven;
            case 7:
                HistoryView eight = new HistoryView();
                sendDataToFragment(eight, 7);
                return eight;
            case 8:
                HistoryView nine = new HistoryView();
                sendDataToFragment(nine, 8);
                return nine;
            case 9:
                HistoryView ten = new HistoryView();
                sendDataToFragment(ten, 9);
                return ten;
            case 10:
                HistoryView ele = new HistoryView();
                sendDataToFragment(ele, 10);
                return ele;

        }
        return null;
    }

    private void sendDataToFragment(HistoryView first, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(context.getString(R.string.historyBundle), historyData.get(i));
        first.setArguments(bundle);
    }

    @Override
    public int getCount() {
        return historyData.size();
    }
}
