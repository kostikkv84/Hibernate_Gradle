import base.BaseTest;
import entity.Customers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.CustomerService;

import java.io.IOException;
import java.util.List;

/**
 * Примеры работы с БД - основные запросы
 */
public class TestDBCustomer extends BaseTest {

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
}
