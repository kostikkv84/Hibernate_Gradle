package steps;

import entity.Customers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import services.CrudService;

import java.util.List;

@Slf4j
public class CustomerDBSteps {
    CrudService crudService = new CrudService();
    private Class<?> clazz;

    @Given("I have a class {string}")
    public void iHaveAClass(String className) throws ClassNotFoundException {
        // Получаем класс по имени
        this.clazz = Class.forName("" + className);
    }

    @Then("Get {string} list")
    public <T> void getList(String className) {
        // Используем clazz, который был установлен ранее
        List<T> customers = crudService.findAll((Class<T>) clazz);
        Assertions.assertTrue(customers.size() > 0, "Записи в таблице Customers не найдены");
    }

    @Then("Get customer by ID >  id покупателя = {int} > ожидаем {string}")
    public void getById(int id, String expected) {
        List<Customers> customer = crudService.findItem(Customers.class, "idcustomer", id);
        log.info(customer.toString());
        Assertions.assertEquals(expected, customer.get(0).getName());
    }

    @Then("Get customer by Name >  Найти покупателя по имени {string} = {string} > ожидаем {string}")
    public void getCustomerByName(String param, String value, String surname) {
        List<Customers> customer = crudService.findItem(Customers.class, param, value);
        log.info(customer.toString());
        Assertions.assertEquals(surname, customer.get(0).getSurname());
    }

    //@Then("^Найти запись в таблице \"([^\"]*)\" по полю \"([^\"]*)\" со значением \"([^\"]*)\" > проверить наличие значения  \"([^\"]*)\"$")
    @Then("Найти запись в таблице {string} по полю {string} со значением {string} > проверить наличие значения  {string}")
    public <T> void getItem(Class<T> clazz, String param, String value, String value1) {
        List<T> item = crudService.findItem(clazz, param, value);
        System.out.println(item);
      //  Assertions.assertTrue(item.toString().contains(value1));
    }






  /*  @Then("Get customer by ID > ищем по ID = {int} > ожидаем имя = {string}")
    public void getByID(int id, String expected) {
        List<Customers> customer = crudService.findItemInt(Customers.class,"idcustomer", id);
        Assertions.assertEquals(expected, customer.get(id).getName());
    } */


}
