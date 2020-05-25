package humarahimachal.online.Modal;

import android.os.Parcel;
import android.os.Parcelable;

public class DistrictModal implements Parcelable {

    private String area;
    private String dc;
    private String density;
    private String female;
    private String history;
    private String hq;
    private String included;
    private String male;
    private String name;
    private String neigh;
    private String oldname;
    private String pop;
    private String river;
    private String subdivision;
    private String teh;
    private String web;

    public DistrictModal() {

    }

    public DistrictModal(String area, String dc, String density, String female, String history, String hq, String included, String male, String name, String neigh, String oldname, String pop, String river, String subdivision, String teh, String web) {
        this.area = area;
        this.dc = dc;
        this.density = density;
        this.female = female;
        this.history = history;
        this.hq = hq;
        this.included = included;
        this.male = male;
        this.name = name;
        this.neigh = neigh;
        this.oldname = oldname;
        this.pop = pop;
        this.river = river;
        this.subdivision = subdivision;
        this.teh = teh;
        this.web = web;
    }

    protected DistrictModal(Parcel in) {
        area = in.readString();
        dc = in.readString();
        density = in.readString();
        female = in.readString();
        history = in.readString();
        hq = in.readString();
        included = in.readString();
        male = in.readString();
        name = in.readString();
        neigh = in.readString();
        oldname = in.readString();
        pop = in.readString();
        river = in.readString();
        subdivision = in.readString();
        teh = in.readString();
        web = in.readString();
    }

    public static final Creator<DistrictModal> CREATOR = new Creator<DistrictModal>() {
        @Override
        public DistrictModal createFromParcel(Parcel in) {
            return new DistrictModal(in);
        }

        @Override
        public DistrictModal[] newArray(int size) {
            return new DistrictModal[size];
        }
    };

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getHq() {
        return hq;
    }

    public void setHq(String hq) {
        this.hq = hq;
    }

    public String getIncluded() {
        return included;
    }

    public void setIncluded(String included) {
        this.included = included;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeigh() {
        return neigh;
    }

    public void setNeigh(String neigh) {
        this.neigh = neigh;
    }

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getTeh() {
        return teh;
    }

    public void setTeh(String teh) {
        this.teh = teh;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(area);
        parcel.writeString(dc);
        parcel.writeString(density);
        parcel.writeString(female);
        parcel.writeString(history);
        parcel.writeString(hq);
        parcel.writeString(included);
        parcel.writeString(male);
        parcel.writeString(name);
        parcel.writeString(neigh);
        parcel.writeString(oldname);
        parcel.writeString(pop);
        parcel.writeString(river);
        parcel.writeString(subdivision);
        parcel.writeString(teh);
        parcel.writeString(web);
    }
}
