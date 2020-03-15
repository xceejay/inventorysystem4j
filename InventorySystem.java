import java.sql.*;
import java.util.*;

public class InventorySystem {
    public static void main(String[] args) {
        addItem();

    }

    public static void addItem() {
        Item item = new Item();
        ProductsHandler additem = new ProductsHandler();
        System.out.print("Enter Name of Item:");

        item.setName(new Scanner(System.in).nextLine());
        System.out.print("Enter Price of Item:$");
        item.setAmount(new Scanner(System.in).nextDouble());
        System.out.print("Enter Price of Description of Item");
        item.setDescription(new Scanner(System.in).nextLine());
        System.out.print("Enter How Many of This Item is being added:");
        item.setQuantity_in(new Scanner(System.in).nextInt());

        additem.addItem(item);
    }

}