package vv.restwebservice.modells;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
//@Table(name="Contract-Table")
// @NamedQuery(name="Contract.findAll", query="SELECT b FROM Contract b ORDER BY b.id")
@XmlRootElement
//@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class  Contract implements Serializable {
    //    private static final long serialVersionUID = 1L;

    @Id
    //    @GenericGenerator(name="hilo-strategy", strategy = "hilo")
    //    @org.hibernate.annotations.GenericGenerator(name=“hilo-strategy”, strategy = “hilo”)
    //    @GeneratedValue(generator = ”hilo-strategy”)
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="contractId")
    @JsonProperty("contractId")
    private long id;
    @ApiModelProperty
    private String type;
    @ApiModelProperty
    private String surname;
    @ApiModelProperty
    private float price;

    @ManyToOne (fetch=FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="CUSTOMER_ID")
    @JsonIgnore
    private Customer customer;

    private long foreignKey;
    public Contract(){};
    public Contract(String type, String surname, float price, Customer customer) {
        this.type = type;
        this.surname = surname;
        this.price = price;
        this.customer = customer;
        this.foreignKey = customer.getid();
    }

    public Contract(String type, String surname, float price, Customer customer, long id) {
        this.type = type;
        this.surname = surname;
        this.price = price;
        this.customer = customer;
        this.foreignKey = customer.getid();
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return id == contract.id &&
                Float.compare(contract.getPrice(), getPrice()) == 0 &&
                Objects.equals(getType(), contract.getType()) &&
                Objects.equals(getSurname(), contract.getSurname()) &&
                Objects.equals(getCustomer(), contract.getCustomer());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, getType(), getSurname(), getPrice(), getCustomer());
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", surname='" + surname + '\'' +
                ", price=" + price +
                ", customer=" + foreignKey +
                '}';
    }



    //    public static long getSerialVersionUID() {
    //        return serialVersionUID;
    //    }

    //...constructors, getters and setters
    public long getContractId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(long foreignKey) {
        this.foreignKey = foreignKey;
    }
}
