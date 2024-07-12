import base.BaseTest;
import entity.Customers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.CustomerService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestDBCustomer extends BaseTest {

    private String name;

    @Test
    public void insertCustomer() throws IOException {
        CustomerService customerService = new CustomerService();
        File file = new File("src/test/jsonFiles/customer.json");
        Customers customer = objectM.readValue(file, Customers.class);
        customer.setName("Гена");
        customer.setLastname("Гениет");
        customer.setSurname("Ольгович");
        customer.setIdproduct(19);
        customerService.save(customer);

        Customers getCustomer = customerService.findCustomer(customer.getIdcustomer());
       // System.out.println(getCustomer);
        Assertions.assertEquals(customer.getIdcustomer(),getCustomer.getIdcustomer());
    }

    @Test
    public void getCustomer() {
        CustomerService service = new CustomerService();
        Customers customer = service.findCustomer(1);
        Assertions.assertEquals("Fsd", customer.getName());
    }

    @Test
    public void updateCustomer() {
        CustomerService service = new CustomerService();
        Customers customer = service.findCustomer(1);
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
        File file = new File("src/test/jsonFiles/customer.json");
        Customers customer = objectM.readValue(file, Customers.class);
        service.save(customer); // добавили покупателя
        Integer idcustomer = service.findLastCustomer().getIdcustomer();
        service.delete(service.findLastCustomer()); // удалили последнего досвленного покупателя

        Assertions.assertNotEquals(idcustomer , service.findLastCustomer().getIdcustomer());
    }
}
