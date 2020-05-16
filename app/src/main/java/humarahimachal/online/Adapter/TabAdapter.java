package humarahimachal.online.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import humarahimachal.online.Fragments.HomeFragment;
import humarahimachal.online.Fragments.SettingsFragment;
import humarahimachal.online.UI.HomePage;

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  (new HomeFragment());
            case 1:
                return (new SettingsFragment());
        }

        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
