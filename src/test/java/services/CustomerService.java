package services;

import dto.CustomersDTO;
import entity.Customers;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerService {

    CustomersDTO customersDTO = new CustomersDTO();

    @Step("Найти запись в таблице Customers по Id")
    public Customers findCustomer(int id) {
        return customersDTO.findById(id);
    }

    @Step("Найти запись в таблице Customers по Name")
    public Customers findCustomer(String name) {
        return customersDTO.findByName(name);
    }

    @Step("Найти последнюю запись в таблице Customers")
    public Customers findLastCustomer() {
        return customersDTO.getLastCustomer();
    }

    @Step("Найти все записи из таблицы Customers")
    public List<Customers> findAll() {
        return  customersDTO.findAll();
    }

    @Step("Сохранение записи в таблицу Customers")
    public void save(Customers customers) {
        customersDTO.save(customers);
    }

    @Step("Обновление записи в таблице Customers")
    public void update(Customers customers) {
        customersDTO.update(customers);
    }

    @Step("Удалить запись из таблицы Customers")
    public void delete(Customers customers) {
        customersDTO.delete(customers);
    }

}
