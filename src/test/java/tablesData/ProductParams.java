package tablesData;

public enum ProductParams {
    id("id"),
    name("name"),
    description("description"),
    customer_id("customer_id"),
    price("price"),
    count("count");

    private final String param;

    ProductParams(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
