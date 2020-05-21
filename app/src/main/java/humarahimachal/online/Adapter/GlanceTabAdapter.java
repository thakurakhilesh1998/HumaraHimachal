package humarahimachal.online.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import humarahimachal.online.Fragments.GeneralInfo;
import humarahimachal.online.Fragments.MinisterInfo;

public class GlanceTabAdapter extends FragmentPagerAdapter {
    public GlanceTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new GeneralInfo();
            case 1:
                return new MinisterInfo();
        }
        return new GeneralInfo();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
