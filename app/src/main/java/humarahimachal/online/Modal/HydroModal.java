package humarahimachal.online.Modal;

public class HydroModal {

    private String name;
    private String capacity;
    private String district;
    private String stream;
    private String river;
    private String company;

    public HydroModal() {

    }

    public HydroModal(String name, String capacity, String district, String stream, String river, String company) {
        this.name = name;
        this.capacity = capacity;
        this.district = district;
        this.stream = stream;
        this.river = river;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
