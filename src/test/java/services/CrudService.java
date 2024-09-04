package services;

import base.AllureAttachment;
import dto.CrudDTO;
import io.qameta.allure.Step;

import java.util.List;

public class CrudService extends AllureAttachment {

    CrudDTO CrudDTO = new CrudDTO();

    @Step("Найти список записей в таблице по Int")
    public <T> List<T> findItemInt(Class<T> clazz, String param, int id) {
        attachObjectToAllureReport(CrudDTO.findItemOnParamInt(clazz, param, id));
        return CrudDTO.findItemOnParamInt(clazz, param, id);
    }


    @Step("Найти список записей в таблице по Str")
    public <T> List<T> findItemStr(Class<T> clazz, String param, String value) {
        attachObjectToAllureReport(CrudDTO.findItemOnParamStr(clazz, param, value));
        return CrudDTO.findItemOnParamStr(clazz, param, value);
    }

    @Step("Найти последнюю запись в таблице")
    public <T> T findLastItem(Class<T> clazz, String param) {
        attachObjectToAllureReport(CrudDTO.getLastItem(clazz, param));
        return CrudDTO.getLastItem(clazz, param);
    }

    @Step("Найти все записи из таблицы Customers")
    public <T> List<T> findAll(Class<T> clazz) {
        attachListOfObjectToAllureReport(CrudDTO.findAll(clazz));
        return  CrudDTO.findAll(clazz);
    }


    @Step("Сохранение записи в таблицу Customers")
    public <T> T save(T clazz) {
        attachObjectToAllureReport(clazz); // добавляемая запись в Allure
        CrudDTO.save(clazz); // предполагается, что метод save возвращает объект типа T
        return clazz;
    }

    @Step("Обновление записи в таблице Customers")
    public <T> T update(T clazz) {
        attachObjectToAllureReport(clazz);
        CrudDTO.update(clazz);
        return clazz;
    }

    @Step("Удалить запись из таблицы Customers")
    public <T> T delete(T clazz) {
        attachObjectToAllureReport(clazz);
        CrudDTO.delete(clazz);
        return clazz;
    }
/*
    //-------------------------------------------------------------------------------------
    @Step("Получаем все продукты по параметру")
    public List<Customers> findCustomerOnParamInt(Customers customers, String paramName, int value){
        List<Customers> foundProducts = customersDTO.findItemOnParamInt(Customers.class, paramName, value);
        attachment.attachObjectToAllureReport(foundProducts);
        return foundProducts;
    }

    @Step("Получаем все продукты по параметру")
    public List<Customers> findCustomerOnParamStr(Customers customers, String paramName, String value){
        List<Customers> foundProducts = customersDTO.findItemOnParamStr(Customers.class, paramName, value);
        attachment.attachObjectToAllureReport(foundProducts);
        return foundProducts;
    }

*/
}
