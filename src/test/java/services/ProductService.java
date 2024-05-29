package services;

import dto.ProductDTO;
import entity.Products;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ProductService {

    ProductDTO productDTO = new ProductDTO();

    public Products findProduct(int id){
        return productDTO.findById(id);
    }

    public void saveProduct(Products product){
        productDTO.save(product);
    }

    public void deleteProduct(Products product){
        productDTO.delete(product);
    }

    public void updateProduct(Products product){
        productDTO.update(product);
    }

    public List<Products> finAllProduct(){
        return productDTO.findAll();
    }



}
