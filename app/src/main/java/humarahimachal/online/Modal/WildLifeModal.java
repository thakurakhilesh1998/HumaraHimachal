package humarahimachal.online.Modal;

public class WildLifeModal {

    private String area;
    private String district;
    private String name;

    public WildLifeModal() {

    }

    public WildLifeModal(String area, String district, String name) {
        this.area = area;
        this.district = district;
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
