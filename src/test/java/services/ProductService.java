package services;

import base.AllureAttachment;
import dto.ProductDTO;
import entity.Products;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import static base.AllureAttachment.attachListOfObjectToAllureReport;

@NoArgsConstructor
@AllArgsConstructor
public class ProductService {
    AllureAttachment attachment = new AllureAttachment();
    ProductDTO productDTO = new ProductDTO();

    @Step("Находим продукт по Id")
    public Products findProduct(int id){
        attachment.attachObjectToAllureReport(productDTO.findById(id));
        return productDTO.findById(id);
    }

    @Step("Сохраняем продукт")
    public void saveProduct(Products product){
        attachment.attachObjectToAllureReport(product);
        productDTO.save(product);
    }

    @Step("Удаляем продукт")
    public void deleteProduct(Products product){
        attachment.attachObjectToAllureReport(product);
        productDTO.delete(product);
    }

    @Step("Обновляем продукт")
    public void updateProduct(Products product){
        attachment.attachObjectToAllureReport(product);
        productDTO.update(product);
    }

    @Step("Получаем все продукты")
    public List<Products> finAllProduct(){
        List<Products> products = productDTO.findAll();
        attachListOfObjectToAllureReport(products);
        return productDTO.findAll();
    }

    @Step("Получаем все продукты с ценой больше ....")
    public List<Products> finAllProductMoreThan(int price){
        attachment.attachObjectToAllureReport(productDTO.findPriceMoreThan(price));
        return productDTO.findPriceMoreThan(price);
    }

}
