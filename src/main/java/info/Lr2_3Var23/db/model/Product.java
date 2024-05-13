package info.Lr2_3Var23.db.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "PRODUCT")
@Data
public class Product implements Serializable {

    public Product() {
    }

    public Product(String name, Date productionDate, Date expirationDate) {
        this.name = name;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }
    
    public Product(String name, Date productionDate, Date expirationDate, Integer fridge) {
        this.fridge = id;
        this.name = name;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_PRODUCTIONDATE")
    private Date productionDate;
    
    @Column(name = "PRODUCT_EXPIRATIONDATE")
    private Date expirationDate;
    

  
    @Column(name = "PRODUCT_FRIDGE_ID")
    private Integer fridge;
    
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setProductionDate(Date productionDate)
    {
        this.productionDate = productionDate;
    }
    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }
    public void setFridge(Integer fridge)
    {
        this.fridge = fridge;
    }
    
    
    
    
    public Integer getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Date getProductionDate()
    {
        return productionDate;
    }
    public Date getExpirationDate()
    {
        return expirationDate;
    }
    public Integer getFridge()
    {
       return fridge;
    }
}
