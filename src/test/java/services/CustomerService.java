package services;

import base.AllureAttachment;
import dto.CustomersDTO;
import entity.Customers;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerService extends AllureAttachment {

    CustomersDTO customersDTO = new CustomersDTO();

    @Step("Найти запись в таблице Customers по Id")
    public Customers findCustomer(int id) {
        customersDTO.findById(id);
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

}
