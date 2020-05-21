package humarahimachal.online.Modal;

public class TempleModal {
    private String district;
    private String imagelink;
    private String info;
    private String name;

    public TempleModal()
    {

    }
    public TempleModal(String district, String imagelink, String info, String name) {
        this.district = district;
        this.imagelink = imagelink;
        this.info = info;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }


}
