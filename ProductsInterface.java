public interface ProductsInterface {

    // Add
    public abstract boolean addItem(Item item);

    // remove
    public abstract boolean removeItem(Item item, int quantity);

    // Modify
    public abstract boolean editName(String oldName);

    public abstract boolean editDescription(String oldDescription);

    public abstract boolean editAmount(String oldAmount);

    // Show
    public abstract String viewProductsID(int order); // view Products in ascending order by ID

    public abstract String viewProductsName(int order); // view Products in descending order by Name
    public abstract String viewProductsAmount(int order); // view Products in ascending order by ID

    public abstract String viewProductsDate_in(int order);
    public abstract String viewProductsExp_Date(int order);
    public abstract String viewProductsQuantity_in(int order);
    // functionalities

    public abstract String generateBill(); // generate Bill to a file to be printed

    public abstract Item searchDB(Item item);

    public abstract boolean increaseQuantity(Item item, int quantity) throws Exception;

    public abstract boolean reduceQuantity(Item item, int quantity) throws Exception;

}