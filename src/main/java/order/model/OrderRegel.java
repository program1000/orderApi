package order.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderRegel")
public class OrderRegel {
    
    private @Id @GeneratedValue Long id;
    private String name;
    private int amount;
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order org_order;
    
    public OrderRegel() {
        
    }
    
    public OrderRegel( Order order, String name, int amount, BigDecimal price ) {
        this.org_order = order;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
    
    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Order getOrg_order() {
        return org_order;
    }

    public void setOrg_order( Order order ) {
        this.org_order = order;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount( int amount ) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }


}
