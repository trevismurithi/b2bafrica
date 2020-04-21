package net.b2bafrica.virtualmeeting.PersonalClass;

public class CompanyDetails {

    public CompanyDetails(){}
    public CompanyDetails(String coName, String industry, String country, String website, String address) {
        this.coName = coName;
        this.industry = industry;
        this.country = country;
        this.website = website;
        this.address = address;
    }

    private String industry;
    private String country;
    private String website;
    private String address;
    private String profileLink;
    private String coName;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }
}
