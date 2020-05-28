package humarahimachal.online.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import humarahimachal.online.Modal.HistoryData;
import humarahimachal.online.R;
import humarahimachal.online.databinding.HistoryViewLayoutBinding;

public class HistoryView extends Fragment {
    HistoryViewLayoutBinding historyView;

    public HistoryView() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        historyView = DataBindingUtil.inflate(inflater, R.layout.history_view_layout, container, false);
        return historyView.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HistoryData historyData = (HistoryData) getArguments().get(getResources().getString(R.string.historyBundle));

        String name = historyData.getName();
        String info = historyData.getInfo();
        int namelenght = name.length();
        historyView.tvHistoryTitle.setText(name.substring(2, namelenght));
        historyView.tvMainText.setText(info);
    }
}
