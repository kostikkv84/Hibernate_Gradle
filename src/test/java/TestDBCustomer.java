import base.AllureAttachment;
import base.BaseMethods;
import base.BaseTest;
import entity.Customers;
import entity.Products;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.CrudService;
import tablesData.CustomersParams;
import tablesData.ProductParams;

import java.io.IOException;
import java.util.List;

/**
 * Примеры работы с БД - основные запросы
 */
public class TestDBCustomer extends BaseTest {
    AllureAttachment attachment = new AllureAttachment();
    BaseMethods baseMethods = new BaseMethods();
    CrudService crudService = new CrudService();
    private String name;

    /**
     * Вставка записи в таблицу и проверка успешного результата
     * Через универсальный метод - save(T)
     * @throws IOException
     */
    @Test
    public void insertCustomer() throws IOException {
        Customers customer = getEntity(Customers.class, "src/test/jsonFiles/customer.json");
        customer.setName(setRandomName());
        customer.setLastname(setRandomName());
        customer.setSurname(setRandomName());
        crudService.save(customer); // сохранение через универсальный метод.
       /* System.out.println(customer.getIdcustomer());
        System.out.println(customer.toString()); */

        List<Customers> customersList = crudService.findItem(Customers.class, "idcustomer", customer.getIdcustomer());

        // Проверяем, что список не пуст и содержит ожидаемого клиента
        Assertions.assertFalse(customersList.isEmpty(), "Список клиентов пуст.");
        Assertions.assertEquals(customer.getIdcustomer(), customersList.get(0).getIdcustomer(), "Идентификаторы клиентов не совпадают.");
    }

    @Test
    @Owner("Koskv")
    @Description("Получение покупателя по параметру - Customer_ID! ")
    public void getCustomerById() {
        String param = CustomersParams.idcustomer.getParam();
        List<Customers> customer = crudService.findItem(Customers.class,param, 1);
        Assertions.assertEquals("Fsd", customer.get(0).getName());
    }

    @Owner("Koskv")
    @Description("Обновление покупателя, проверка! ")
    @Test
    public void updateCustomer() {
        Customers customer = crudService.findLastItem(Customers.class, CustomersParams.idcustomer.getParam()); // находим последнюю запись по ID
        customer.setSurname("testSurname"); //  Указываем фамилию в последней записи - setSurname
        crudService.update(customer); // обновляем таблицу измененной записью

        Assertions.assertEquals("testSurname", customer.getSurname()); // проверяем фамилию до и фамилию после
    }

    @Test // Удаление и проверка, что запись удалена
    public void deleteCustomer() throws IOException {
        Customers customer = getEntity(Customers.class, "src/test/jsonFiles/customer.json");
        crudService.save(customer); // добавили покупателя
        Integer idcustomer = crudService.findLastItem(Customers.class, CustomersParams.idcustomer.getParam()).getIdcustomer(); // получили id добавленного покупателя
        crudService.delete(crudService.findLastItem(Customers.class,"idcustomer"));// удалили последнего добавленного покупателя
        Assertions.assertNotEquals(idcustomer , crudService.findLastItem(Customers.class,"idcustomer").getIdcustomer(), "Запись не удалена.");
    }

    /**
     * Получить список всех покупателей
     */
    @Test
    @Description ("Получение всех записей из таблицы - Customers")
    public void getAllCustomers() {
        List<Customers> customers = crudService.findAll(Customers.class);
        System.out.println("Найдено - " + customers.size() + " записей в бд!");
        Assertions.assertTrue(customers.size() > 0, "Записи в таблице Customers не найдены");
    }

    @Test
    @Owner("Koskv")
    @Description("Получение покупателя по рандомному параметру, например - Surname! ")
    public void getCustomerOnCustomerSurname (){
        String paramName = CustomersParams.surname.getParam(); // получаем имя параметра
        System.out.println("Выбран параметр: " + paramName); // выводим в консоль выбранные параметр
        // получаем список продуктов и записываем в объект класса - ищем по surname = "Ольгович1"
        List<Customers> customersList = crudService.findItem(Customers.class, paramName, "Ольгович");  // достаем все записи

        customersList.stream().forEach(x -> System.out.println(x.getSurname())); // просто просмотр что выбралось
        // Проверяем, что все выбранные записи содержат нужные данные.
        customersList.stream().forEach(x -> Assertions.assertEquals("Ольгович", (x.getSurname())));
    }

    @Test
    @Owner("Koskv")
    @Description("Получение покупателя по рандомному параметру, например - ProductId! ")
    public void getCustomerOnProductID (){
        String paramName = CustomersParams.idproduct.getParam(); // получаем имя параметра = idproduct
        System.out.println("Выбран параметр: " + paramName); // выводим в консоль выбранные параметр

        // получаем список клиентов и записываем в объект класса - ищем по ID = 86
        List<Customers> customersList = crudService.findItem(Customers.class, paramName, 86);  // достаем все записи

        customersList.stream().forEach(x -> System.out.println(x.getSurname())); // просто просмотр что выбралось
        // Проверяем, что все выбранные записи содержат нужные данные.
        customersList.stream().forEach(x -> Assertions.assertEquals("Васильев", (x.getSurname())));
    }

    @Test
    @Owner("Koskv")
    @Description("Универсальный клас. Получение покупателя по рандомному параметру, например - ProductId! ")
    public void getProductOnProductName (){
        String paramName = ProductParams.name.getParam(); // получаем имя параметра
        System.out.println("Выбран параметр: " + paramName); // выводим в консоль выбранные параметр

        // получаем список продуктов и записываем в объект класса - ищем по ID = 86
        List<Products> productsList = crudService.findItem(Products.class, paramName, "Apple");  // достаем все записи

        // productsList.stream().forEach(x -> System.out.println(x.getName())); // просто просмотр что выбралось
        // Проверяем, что все выбранные записи содержат нужные данные.
        productsList.stream().forEach(x -> Assertions.assertEquals("Apple", (x.getName())));
    }


}
