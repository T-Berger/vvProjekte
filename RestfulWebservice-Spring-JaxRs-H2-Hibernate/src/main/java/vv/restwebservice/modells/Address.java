package vv.restwebservice.modells;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Address-Table")
public class Address {
    private String street;
    private String zip;
    private String location;

    //Getters and Setters

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
