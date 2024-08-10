package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers", schema = "public", catalog = "shop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

   // @ManyToOne // создание связи между таблицами Customers & Products
   // @JoinColumn(name = "idproduct", referencedColumnName = "id")
    @Column(name = "idproduct", nullable = false)
    private Integer idproduct;

    @Override
    public String toString() {
        return "Costumers{" +
                "idcustomer=" + idcustomer +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", idproduct=" + idproduct +
                '}';
    }
}
