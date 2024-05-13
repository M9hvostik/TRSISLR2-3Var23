package info.Lr2_3Var23.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "FRIDGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fridge implements Serializable {

    private static final long serialVersionUID = 1L;

    public Fridge(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "FRIDGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = Product.class, mappedBy = "fridge", fetch = FetchType.LAZY)
    private List<Product> productionsList;

    @Column(name = "FRIDGE_NAME", unique = true)
    private String name;
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    
    public void addProduct(Product product)
    {
        productionsList.add(product);
    }
    
    public void removeProduct(Product product)
    {
        productionsList.remove(product);
    }
    
}
