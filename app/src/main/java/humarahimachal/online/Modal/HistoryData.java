package humarahimachal.online.Modal;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryData implements Parcelable {

    String name;
    String info;

    public static final Creator<HistoryData> CREATOR = new Creator<HistoryData>() {
        @Override
        public HistoryData createFromParcel(Parcel in) {
            return new HistoryData(in);
        }

        @Override
        public HistoryData[] newArray(int size) {
            return new HistoryData[size];
        }
    };


    public HistoryData() {

    }

    public HistoryData(String name, String info) {
        this.name = name;
        this.info = info;
    }

    protected HistoryData(Parcel in) {
        name = in.readString();
        info = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(info);
    }
}
