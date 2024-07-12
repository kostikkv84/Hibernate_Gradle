package services;

import dto.ProductDTO;
import entity.Products;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ProductService {

    ProductDTO productDTO = new ProductDTO();

    @Step("Находим продукт по Id")
    public Products findProduct(int id){
        return productDTO.findById(id);
    }

    @Step("Сохраняем продукт")
    public void saveProduct(Products product){
        productDTO.save(product);
    }

    @Step("Удаляем продукт")
    public void deleteProduct(Products product){
        productDTO.delete(product);
    }

    @Step("Обновляем продукт")
    public void updateProduct(Products product){
        productDTO.update(product);
    }

    @Step("Получаем все продукты")
    public List<Products> finAllProduct(){
        return productDTO.findAll();
    }

    @Step("Получаем все продукты с ценой больше ....")
    public List<Products> finAllProductMoreThan(int price){
        return productDTO.findPriceMoreThan(price);
    }

}
