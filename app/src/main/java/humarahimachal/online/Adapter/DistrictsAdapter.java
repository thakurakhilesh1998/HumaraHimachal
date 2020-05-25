package humarahimachal.online.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import humarahimachal.online.Fragments.DistrictFragment;
import humarahimachal.online.Modal.DistrictModal;
import humarahimachal.online.R;

public class DistrictsAdapter extends FragmentPagerAdapter {
    ArrayList<DistrictModal> districtList;
    Context context;

    public DistrictsAdapter(@NonNull FragmentManager fm, ArrayList<DistrictModal> districtList, Context context) {
        super(fm);
        this.districtList = districtList;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                DistrictFragment first = new DistrictFragment();
                Bundle firstList = new Bundle();
                firstList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(0));
                first.setArguments(firstList);
                return first;
            case 1:
                DistrictFragment second = new DistrictFragment();
                Bundle secondList = new Bundle();
                secondList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(1));
                second.setArguments(secondList);
                return second;
            case 2:
                DistrictFragment third = new DistrictFragment();
                Bundle thidList = new Bundle();
                thidList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(2));
                third.setArguments(thidList);
                return third;
            case 3:
                DistrictFragment fourth = new DistrictFragment();
                Bundle fourthList = new Bundle();
                fourthList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(3));
                fourth.setArguments(fourthList);
                return fourth;
            case 4:
                DistrictFragment five = new DistrictFragment();
                Bundle fiveList = new Bundle();
                fiveList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(4));
                five.setArguments(fiveList);
                return five;
            case 5:
                DistrictFragment sixth = new DistrictFragment();
                Bundle sixthList = new Bundle();
                sixthList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(5));
                sixth.setArguments(sixthList);
                return sixth;
            case 6:
                DistrictFragment seven = new DistrictFragment();
                Bundle sevenList = new Bundle();
                sevenList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(6));
                seven.setArguments(sevenList);
                return seven;
            case 7:
                DistrictFragment eight = new DistrictFragment();
                Bundle eightList = new Bundle();
                eightList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(7));
                eight.setArguments(eightList);
                return eight;
            case 8:
                DistrictFragment nine = new DistrictFragment();
                Bundle nineList = new Bundle();
                nineList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(8));
                nine.setArguments(nineList);
                return nine;
            case 9:
                DistrictFragment ten = new DistrictFragment();
                Bundle tenList = new Bundle();
                tenList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(9));
                ten.setArguments(tenList);
                return ten;
            case 10:
                DistrictFragment ele = new DistrictFragment();
                Bundle eleList = new Bundle();
                eleList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(10));
                ele.setArguments(eleList);
                return ele;
            case 11:
                DistrictFragment last = new DistrictFragment();
                Bundle lastList = new Bundle();
                lastList.putParcelable(context.getResources().getString(R.string.districtfragment), districtList.get(11));
                last.setArguments(lastList);
                return last;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 12;
    }
}
