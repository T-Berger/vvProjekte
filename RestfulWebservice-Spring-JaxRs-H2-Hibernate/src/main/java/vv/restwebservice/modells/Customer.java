package vv.restwebservice.modells;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name="Customer-Table")
// @NamedQuery(name="Customer.findAll", query="SELECT b FROM Customer b ORDER BY b.id")
@ApiModel
@XmlRootElement
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "CUSTOMER_ID")
    private long id;
    private String forename;
    private String surname;
    private Date birthday;

    @Embedded
    private Address address;

    private String description;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private List<Contract> contracts;

    public Customer(String forename, String surname, Date birthday, String description) {
        this.forename = forename;
        this.surname = surname;
        this.birthday = birthday;
        this.description = description;
    }
    public Customer(){}

    public Customer(String forename, String surname, Date birthday, Address address, String description, List<Contract> contracts) {
        this.forename = forename;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.description = description;
        this.contracts = contracts;
    }

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(getForename(), customer.getForename()) &&
                Objects.equals(getSurname(), customer.getSurname()) &&
                Objects.equals(getBirthday(), customer.getBirthday()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getDescription(), customer.getDescription()) &&
                Objects.equals(getContracts(), customer.getContracts());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, getForename(), getSurname(), getBirthday(), getAddress(), getDescription(), getContracts());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                ", description='" + description + '\'' +
                ", contracts=" + contracts +
                '}';
    }

    //...constructors, getters and setters

    public long getid() {
        return id;
    }

    public void setid(long id) {
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

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
