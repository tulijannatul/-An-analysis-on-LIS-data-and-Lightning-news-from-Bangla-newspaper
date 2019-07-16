package Beans;

import Tools.Constants;

public class LISdataProperty {
    private String listId;
    private DateOb date;
    private LatLng latlng;
    private String radiance;
    private String milliseconds;
    private String groups;
    private String Events;
    private String orbitId;
    

    public LISdataProperty() {
    }

    public LISdataProperty(DateOb date, LatLng latlng, String radiance, String milliseconds, String Events) {
        this.date = date;
        this.latlng = latlng;
        this.radiance = radiance;
        this.milliseconds = milliseconds;
        this.Events = Events;
    }

    @Override
    public String toString(){
        return ""+ this.listId+" "
                + this.getDateString()+" "
                + this.latlng.getLat()+" "
                + this.latlng.getLng()+" "
                + this.radiance+" " 
                + this.milliseconds+" "
                + this.groups+" "
                + this.Events+" "
                + this.orbitId+" "
                + this.date.getTime() +"\n";
    }
    
    public String toCSV(){
        return    Constants.getCSV(this.listId)+","
                + Constants.getCSV(this.getDateString())+"," 
                + Constants.getCSV(this.latlng.getLat())+","
                + Constants.getCSV(this.latlng.getLng())+","
                + Constants.getCSV(this.radiance)+"," 
                + Constants.getCSV(this.milliseconds)+","
                + Constants.getCSV(this.groups)+","
                + Constants.getCSV(this.Events)+","
                + Constants.getCSV(this.orbitId)+","
                + Constants.getCSV(this.date.getTime())+
                "\n";
    }
    
    private String getDateString(){
        return this.date.getYear()+this.date.getMonth()+this.date.getDay();
    }
    
    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }    
    
    public DateOb getDate() {
        return date;
    }

    public void setDate(DateOb date) {
        this.date = date;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public String getRadiance() {
        return radiance;
    }

    public void setRadiance(String radiance) {
        this.radiance = radiance;
    }

    public String getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getEvents() {
        return Events;
    }
    public void setEvents(String Events) {
        this.Events = Events;
    }

    public String getOrbitId() {
        return orbitId;
    }

    public void setOrbitId(String orbitId) {
        this.orbitId = orbitId;
    }
    
}
