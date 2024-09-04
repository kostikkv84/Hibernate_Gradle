package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers", schema = "public", catalog = "shop")
@Data
@Builder
@JsonIgnoreProperties
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcustomer", nullable = false)
    private Integer idcustomer;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "lastname", nullable = true)
    private String lastname;

    @Column(name = "idproduct", nullable = false)
    private Integer idproduct;

  /*  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Products> products; */

    @Override
    public String toString() {
        return "Customers{" +
                "idcustomer=" + idcustomer +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", idproduct=" + idproduct +
                '}';
    }
}
