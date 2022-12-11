package models;

public class Emoji {
    private String unified;
    private String shortName;

    public Emoji() {
    }

    public Emoji(String unified, String shortName) {
        this.unified = unified;
        this.shortName = shortName;
    }

    public String getUnified() {
        return unified;
    }

    public void setUnified(String unified) {
        this.unified = unified;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
