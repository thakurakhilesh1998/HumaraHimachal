package humarahimachal.online.Data;

import android.content.Context;

import java.util.ArrayList;

import humarahimachal.online.Modal.AboutModal;
import humarahimachal.online.R;

public class GeographicData {
    public static ArrayList<AboutModal> getGeographicData(Context context)
    {
        ArrayList<AboutModal> geographicData=new ArrayList<>();
        geographicData.add(new AboutModal(R.drawable.river,context.getString(R.string.rivers)));
        geographicData.add(new AboutModal(R.drawable.temples,context.getString(R.string.temples)));
        geographicData.add(new AboutModal(R.drawable.lake,context.getString(R.string.lakes)));
        geographicData.add(new AboutModal(R.drawable.ic_dams,context.getResources().getString(R.string.powerProjects)));
        geographicData.add(new AboutModal(R.drawable.national_park,context.getString(R.string.nationalpark)));
        geographicData.add(new AboutModal(R.drawable.santuary,context.getString(R.string.wildlifesantuary)));
        geographicData.add(new AboutModal(R.drawable.heritages,context.getString(R.string.heritage)));
        return geographicData;
    }
}
