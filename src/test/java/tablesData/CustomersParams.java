package tablesData;

public enum CustomersParams {
    idcustomer("idcustomer"),
    name("name"),
    surname("surname"),
    lastname("lastname"),
    idproduct("idproduct");

    private final String param;


    CustomersParams(String param) {
        this.param = param;
    }

    public String getParam(){
        return param;
    }
}
