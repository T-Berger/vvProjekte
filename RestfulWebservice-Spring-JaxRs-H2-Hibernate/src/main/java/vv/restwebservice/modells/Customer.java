package vv.restwebservice.modells;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Customer-Table")
// @NamedQuery(name="Customer.findAll", query="SELECT b FROM Customer b ORDER BY b.id")
@XmlRootElement
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    private String forename;
    private String surname;
    private Date birthday;
    private Address address;
    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    //...constructors, getters and setters

    public int getCustomerId() {
        return id;
    }

    public void setCustomerId(int id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
