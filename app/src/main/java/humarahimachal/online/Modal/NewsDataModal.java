package humarahimachal.online.Modal;

public class NewsDataModal {

    private String imageUrl;
    private String title;
    private String desc;
    private String date;
    private String source;
    private String urlToSource;

    public NewsDataModal(String imageUrl, String title, String desc, String date, String source, String urlToSource) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.source = source;
        this.urlToSource = urlToSource;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrlToSource() {
        return urlToSource;
    }

    public void setUrlToSource(String urlToSource) {
        this.urlToSource = urlToSource;
    }
}
