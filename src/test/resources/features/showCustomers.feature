Feature:  Get Customers scenarios

  Scenario: Get list of customers
    Given I have a class "Customer"
    Then Get Customer list


  Scenario: Получить покупателя по ID = 1
    Then Get customer by ID >  id покупателя = 1 > ожидаем "Fsd"


  Scenario: Получить покупателя по Name
    Then Get customer by Name >  Найти покупателя по имени "name" = "Георгий" > ожидаем "Васильев"

  Scenario: Получить покупателя по Name
    Then Найти запись в таблице "Customer" по полю "name" со значением "Георгий" > проверить наличие значения  "Васильев"
 #   Then Значение найденной записи = ""