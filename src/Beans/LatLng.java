package Beans;


public class LatLng {
    private String Lat;
    private String Lng;

    public LatLng() {
    }

    public LatLng(String Lat, String Lng) {
        this.Lat = Lat;
        this.Lng = Lng;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String Lat) {
        this.Lat = Lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String Lng) {
        this.Lng = Lng;
    }
}
