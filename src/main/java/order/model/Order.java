package order.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

    private @Id @GeneratedValue Long id;
    private String name;
    private Date creationDate;
    private String location;
    private String status;
    @OneToMany(mappedBy="org_order", cascade = CascadeType.ALL)
    private Set<OrderRegel> regels;
    
    public Order() {}
    
    public Order( String name, Date creationDate, String location, String status ) {
        this.name = name;
        this.creationDate = creationDate;
        this.location = location;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate ) {
        this.creationDate = creationDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation( String location ) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public Set<OrderRegel> getRegels() {
        return regels;
    }

    public void setRegels( Set<OrderRegel> regels ) {
        this.regels = regels;
    }
}
