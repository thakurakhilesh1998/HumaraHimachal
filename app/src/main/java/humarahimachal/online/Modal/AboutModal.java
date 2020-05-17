package humarahimachal.online.Modal;

public class AboutModal {
    int imageAbout;
    String aboutName;

    public AboutModal(int imageAbout, String aboutName) {
        this.imageAbout = imageAbout;
        this.aboutName = aboutName;
    }


    public int getImageAbout() {
        return imageAbout;
    }

    public void setImageAbout(int imageAbout) {
        this.imageAbout = imageAbout;
    }

    public String getAboutName() {
        return aboutName;
    }

    public void setAboutName(String aboutName) {
        this.aboutName = aboutName;
    }
}
