package humarahimachal.online.Modal;

public class RiverModal {
    String riverName;
    String vedicName;
    String sanskritName;
    String riverLength;
    String catchmentArea;
    String originPlace;
    String exitPlace;
    String mainPlaces;
    String tributaryRivers;
    String hpEntry;

    public RiverModal(String hpEntry, String riverName, String vedicName, String sanskritName, String riverLength, String originPlace, String exitPlace, String mainPlaces, String tributaryRivers, String catchmentArea) {
        this.hpEntry = hpEntry;
        this.riverName = riverName;
        this.vedicName = vedicName;
        this.sanskritName = sanskritName;
        this.riverLength = riverLength;
        this.originPlace = originPlace;
        this.exitPlace = exitPlace;
        this.mainPlaces = mainPlaces;
        this.tributaryRivers = tributaryRivers;
        this.catchmentArea = catchmentArea;
    }

    public String getCatchmentArea() {
        return catchmentArea;
    }

    public void setCatchmentArea(String catchmentArea) {
        this.catchmentArea = catchmentArea;
    }

    public String getHpEntry() {
        return hpEntry;
    }

    public void setHpEntry(String hpEntry) {
        this.hpEntry = hpEntry;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public String getVedicName() {
        return vedicName;
    }

    public void setVedicName(String vedicName) {
        this.vedicName = vedicName;
    }

    public String getSanskritName() {
        return sanskritName;
    }

    public void setSanskritName(String sanskritName) {
        this.sanskritName = sanskritName;
    }

    public String getRiverLength() {
        return riverLength;
    }

    public void setRiverLength(String riverLength) {
        this.riverLength = riverLength;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getExitPlace() {
        return exitPlace;
    }

    public void setExitPlace(String exitPlace) {
        this.exitPlace = exitPlace;
    }

    public String getMainPlaces() {
        return mainPlaces;
    }

    public void setMainPlaces(String mainPlaces) {
        this.mainPlaces = mainPlaces;
    }

    public String getTributaryRivers() {
        return tributaryRivers;
    }

    public void setTributaryRivers(String tributaryRivers) {
        this.tributaryRivers = tributaryRivers;
    }
}
