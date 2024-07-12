import base.BaseTest;
import base.TestListener;
import entity.Products;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import services.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@ExtendWith(TestListener.class)
public class TestDBProduct extends BaseTest {

    //******************************************************

    @Test
    @Tag("smoke")
    void insertManualIntoTable(){

        Products products = new Products();
        products.setName("Samsung Galaxy");
        products.setDescription("S12 fe");
        products.setPrice(10000.00);
        products.setCount(7);

        getSession().getTransaction().begin();

        getSession().save(products);

        getSession().getTransaction().commit();
       // session.close();
    }

    @Test
    @Tag("smoke")
    void insertFromJson() throws IOException {
        File file = new File("src/test/jsonFiles/product.json");
        System.out.println(file);

        Products products = objectM.readValue(file, Products.class);
        products.setPrice(45000.00);

         getSession().beginTransaction();

        getSession().save(products);
        getSession().getTransaction().commit();
       //  session.close();

    }

    @Test
    @Tag("regress")
    public void insertProduct() throws IOException {
        ProductService productService = new ProductService(); // стартуем сессию
        File file = new File("src/test/jsonFiles/product.json");
        Products products = objectM.readValue(file, Products.class); // записываем из Json в класс Product
        products.setName("device11"); // меняем название продукта
        productService.saveProduct(products); // сохраняем продукт в БД

        Products getProducts = productService.findProduct(products.getId()); // получаем сохраненный продукт
        Assertions.assertEquals(products.getName(),getProducts.getName());   // сравниваем значения
    }

    @Test
    @Owner("Koskv")
    @Description("Получение продукта по ID")
    @Tag("regress")
    public void getProduct() throws IOException {
      //  Allure.step("Стартуем ProductService");
        ProductService productService = new ProductService(); // стартуем сессию
        Products product = productService.findProduct(13); // сохраняем продукт в БД
        Assertions.assertEquals("Samsung Galaxy", product.getName());   // сравниваем значения
    }

    @Test
    @Tag("regress")
    public void updateProduct() throws IOException {
        ProductService productService = new ProductService(); // стартуем сессию
        Products product = productService.findProduct(17); // находим запись
        product.setDescription("newDescription Test");  // меняем запись
        productService.updateProduct(product);   // обновляем запись
        Products newProduct = productService.findProduct(17); // находим обновленную запись

        // сравниваем результаты
        Assertions.assertEquals(product.getDescription(), newProduct.getDescription());   // сравниваем значения
    }

    @Test
    @Tag("regress")
    public void deleteProduct() throws IOException {
        ProductService productService = new ProductService();  // Открыли сессию
        File file = new File("src/test/jsonFiles/product.json"); // нашли JSON с данными
        Products product = objectM.readValue(file, Products.class); // записываем из Json в класс Product
        productService.saveProduct(product); // сохраняем продукт в БД
        Integer id = product.getId(); // получаем id нового продукта
        product = productService.findProduct(id); // находим созданный продукт

        System.out.println(product);
        productService.deleteProduct(product);  // удаляем продукт из БД
        Products getProducts = productService.findProduct(id); // ищем удаленную запись
        // сравниваем результаты
        int i =0;

        System.out.println(getProducts);
        Assertions.assertEquals(null, getProducts);   //

    }

    @Test
    @Tag("regress")
    @Tag("smoke")
    public void getAllProduct() throws IOException {
        ProductService productService = new ProductService(); // стартуем сессию
        List<Products> product = productService.finAllProduct();  // достаем все записи
        product.stream().forEach(x -> System.out.println(x.getId()));
         System.out.println("Найдено - " + product.size() + " записей в бд!");
        Assertions.assertTrue(product.size() > 1);   // сравниваем значения
    }

    @Test
    @Owner("Koskv")
    @Description("Получение продукта c ценой более указанной в параметре! ")
    public void getProductsMoreThan1000 (){
        ProductService service = new ProductService(); // стартуем сессию
        List<Products> product = service.finAllProductMoreThan(10000);  // достаем все записи
        product.stream().forEach(x -> System.out.println(x.getPrice()));
    }

}
