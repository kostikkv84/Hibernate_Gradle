import base.AllureAttachment;
import base.BaseTest;
import entity.Customers;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.CustomerService;
import tablesData.CustomersParams;

import java.io.IOException;
import java.util.List;

/**
 * Примеры работы с БД - основные запросы
 */
public class TestDBCustomer extends BaseTest {
    AllureAttachment attachment = new AllureAttachment();
    private String name;

    /**
     * Вставка записи в таблицу и проверка успешного результата
     * @throws IOException
     */
    @Test
    public void insertCustomer() throws IOException {
        CustomerService customerService = new CustomerService();
        Customers customer = getEntity(Customers.class, "src/test/jsonFiles/customer.json");
        customer.setName("Гена");
        customer.setLastname("Гениет");
        customer.setSurname("Ольгович");
        //customer.setIdproduct(19);
        customerService.save(customer);

        Customers getCustomer = customerService.findCustomer(customer.getIdcustomer());
       // System.out.println(getCustomer);
        Assertions.assertEquals(customer.getIdcustomer(),getCustomer.getIdcustomer());
    }

    @Test
    public void getCustomerById() {
        CustomerService service = new CustomerService();
        Customers customer = service.findCustomer(1);
        Assertions.assertEquals("Fsd", customer.getName());
    }

    @Test
    public void updateCustomer() {
        CustomerService service = new CustomerService();
        Customers customer = service.findLastCustomer();
        customer.setSurname("testSurname");
        service.update(customer);

        Assertions.assertEquals("testSurname", customer.getSurname());
    }

    @Test
    public void getAllCustomers() {
        CustomerService service = new CustomerService();
        List<Customers> customers = service.findAll();
        System.out.println("Найдено - " + customers.size() + " записей в бд!");
        Assertions.assertTrue(customers.size() > 0);
    }

    @Test
    public void deleteCustomer() throws IOException {
        CustomerService service = new CustomerService();
        Customers customer = getEntity(Customers.class, "src/test/jsonFiles/customer.json");
        service.save(customer); // добавили покупателя
        Integer idcustomer = service.findLastCustomer().getIdcustomer();
        service.delete(service.findLastCustomer()); // удалили последнего добавленного покупателя

        Assertions.assertNotEquals(idcustomer , service.findLastCustomer().getIdcustomer(), "Запись не удалена.");
    }

    @Test
    @Owner("Koskv")
    @Description("Получение продукта по рандомному параметру - Customer_ID! ")
    public void getCustomerOnCustomerSurname (){
        CustomerService service = new CustomerService(); // стартуем сессию
        String paramName = CustomersParams.surname.getParam(); // получаем имя параметра
        Customers customers = new Customers(); // создать объект класса Products
        // получаем список продуктов и записываем в объект класса
        List<Customers> customers1 = service.findProductOnParamInt(customers, paramName, 1);  // достаем все записи
        customers1.stream().forEach(x -> System.out.println(x.getSurname())); // просто просмотр что выбралось
        customers1.stream().forEach(x -> Assertions.assertEquals("Ольгович", (x.getSurname())));
    }
}
