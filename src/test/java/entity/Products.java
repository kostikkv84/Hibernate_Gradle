package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "products", schema = "public", catalog = "shop")
@Data
@Builder
@JsonIgnoreProperties
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "customer_id")
    private Integer customer_id;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customer; */

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", customer_id=" + customer_id +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Products products = (Products) object;
        return Objects.equals(id, products.id) && Objects.equals(name, products.name) && Objects.equals(description, products.description) && Objects.equals(customer_id, products.customer_id) && Objects.equals(price, products.price) && Objects.equals(count, products.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, customer_id, price, count);
    }
}
