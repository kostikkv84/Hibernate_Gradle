import entity.Products;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class TestDB extends BaseTest{


    //******************************************************

    @Test
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
    public void getProduct() throws IOException {
        ProductService productService = new ProductService(); // стартуем сессию
         Products product = productService.findProduct(13); // сохраняем продукт в БД
       Assertions.assertEquals("Samsung Galaxy1", product.getName());   // сравниваем значения
    }

    @Test
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
    public void getAllProduct() throws IOException {
        ProductService productService = new ProductService(); // стартуем сессию
        List<Products> product = productService.finAllProduct();  // достаем все записи
        System.out.println("Найдено - " + product.size() + " записей в бд!");
        Assertions.assertTrue(product.size() > 1);   // сравниваем значения
    }

}
