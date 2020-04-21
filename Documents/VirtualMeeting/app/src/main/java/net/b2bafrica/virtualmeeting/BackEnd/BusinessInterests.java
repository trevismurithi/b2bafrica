package net.b2bafrica.virtualmeeting.BackEnd;

public class BusinessInterests {
    private String country;
    private String audience;
    private String meeting;
    private String typeMeet;
    private String dates;

    public BusinessInterests(String country, String audience, String meeting, String typeMeet, String dates) {
        this.country = country;
        this.audience = audience;
        this.meeting = meeting;
        this.typeMeet = typeMeet;
        this.dates = dates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getTypeMeet() {
        return typeMeet;
    }

    public void setTypeMeet(String typeMeet) {
        this.typeMeet = typeMeet;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}
