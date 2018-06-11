package vv.restwebservice.modells;

import javax.persistence.Embeddable;

//@Entity
//@Table(name="Address-Table")
@Embeddable
public class Address {

    private String street;
    private String zip;
    private String location;

    public Address (){
        super();
    }
    public Address(String street, String zip, String location) {
        this.street = street;
        this.zip = zip;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
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
