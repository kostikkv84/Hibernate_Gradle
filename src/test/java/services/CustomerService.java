package services;

import base.AllureAttachment;
import dto.CustomersDTO;
import entity.Customers;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * * Уже не актуален - работа через crudService
 * Сервис для работы с методами (которые обращаются к БД)
 * Так же добавляются данные в AllureReport
 */
@NoArgsConstructor
@AllArgsConstructor
public class CustomerService extends AllureAttachment {
    AllureAttachment attachment = new AllureAttachment();
    CustomersDTO customersDTO = new CustomersDTO();

    @Step("Найти запись в таблице Customers по Id")
    public Customers findCustomer(int id) {
        attachObjectToAllureReport(customersDTO.findById(id));
        return customersDTO.findById(id);
    }

    @Step("Найти запись в таблице Customers по Name")
    public Customers findCustomer(String name) {
        attachObjectToAllureReport(customersDTO.findByName(name));
        return customersDTO.findByName(name);
    }

    @Step("Найти последнюю запись в таблице Customers")
    public Customers findLastCustomer() {
        attachObjectToAllureReport(customersDTO.getLastCustomer());
        return customersDTO.getLastCustomer();
    }

    @Step("Найти все записи из таблицы Customers")
    public List<Customers> findAll() {
        attachListOfObjectToAllureReport(customersDTO.findAll());
        return  customersDTO.findAll();
    }

    @Step("Сохранение записи в таблицу Customers")
    public void save(Customers customers) {
        attachObjectToAllureReport(customers); // добавляемая запись в Allure
        customersDTO.save(customers);
    }

    @Step("Обновление записи в таблице Customers")
    public void update(Customers customers) {
        attachObjectToAllureReport(customers);
        customersDTO.update(customers);
    }

    @Step("Удалить запись из таблицы Customers")
    public void delete(Customers customers) {
        attachObjectToAllureReport(customers);
        customersDTO.delete(customers);
    }

//-------------------------------------------------------------------------------------
    @Step("Получаем все продукты по параметру")
    public List<Customers> findCustomerOnParamInt(Customers customers, String paramName, int value){
    List<Customers> foundProducts = customersDTO.findItemOnParamInt(Customers.class, paramName, value);
    attachment.attachObjectToAllureReport(foundProducts);
    return foundProducts;
}

    @Step("Получаем все продукты по параметру")
    public List<Customers> findCustomerOnParamStr(Customers customers, String paramName, String value){
        List<Customers> foundProducts = customersDTO.findItemOnParam(Customers.class, paramName, value);
        attachment.attachObjectToAllureReport(foundProducts);
        return foundProducts;
    }


}
