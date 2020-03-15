import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class InventorySystem {
    public static void main(String[] args) throws IOException {

        // String command = "bash WelcomeScreen.sh"; %%% USED A BASH SCRIPT INSTEAD%%
        // try {
        // runShellCMD(command);
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        int choice = 0;

        System.out.println("\t\t MENU");
        System.out.println("\t\t ----");
        System.out.println("1. Add Products To Inventory");
        System.out.println("2. Remove Products From Inventory");
        System.out.println("3. Modify Name Of Product In Inventory");
        System.out.println("4. Modify Price Of Product In Inventory");
        System.out.println("5. Modify Decription Of Product In Inventory");
        System.out.println("6. Display Products In Ascending Order(ID)");
        System.out.println("7. Display Products In Ascending Order(Name)");
        System.out.println("8. Search For An Item");// newly added module---add to productinterface
        System.out.println("9. Generate Bill");
        System.out.println("10. Exit Program");
        System.out.print("Enter a choice:");
        choice = new Scanner(System.in).nextInt();

        switch (choice) {
            case 1:
                addItem(); // add items to database
                clear();
                main(args);
                break;

            case 2:
                removeItem(); // remove items from database
                clear();
                main(args);
                break;

            case 3:

                main(args);
                break;

            case 4:

                main(args);
                break;

            case 5:

                main(args);
                break;

            case 6:

                main(args);
                break;

            case 7:

                main(args);
                break;

            case 8:

                main(args);
                break;

            case 9:

                main(args);
                break;

            case 10:

                main(args);
                break;

            default:
                main(args);
                break;

        }
    }

    // public static void runShellCMD(String command) throws
    // IOException,InterruptedException {

    // Process proc;

    // proc = Runtime.getRuntime().exec(command);

    // // Read the output

    // BufferedReader reader = new BufferedReader(new
    // InputStreamReader(proc.getInputStream()));

    // String line = "";
    // while ((line = reader.readLine()) != null) {
    // System.out.print(line + "\n");
    // }

    // proc.waitFor();

    // }

    public static void addItem() {
        Item item = new Item();
        ProductsHandler itemAdder = new ProductsHandler();
        System.out.print("Enter Name of Item:");

        item.setName(new Scanner(System.in).nextLine());
        System.out.print("Enter Price of Item:$");
        item.setAmount(new Scanner(System.in).nextDouble());
        System.out.print("Enter  Description of Item");
        item.setDescription(new Scanner(System.in).nextLine());
        System.out.print("Enter How Many of This Item is being added:");
        item.setQuantity_in(new Scanner(System.in).nextInt());

        if (itemAdder.addItem(item)) {
            System.out.println("SUCESSFULLY ADDED+! Add Another Item Again By Pressing \"0\" and <Enter>");
            System.out.println("Or Pressing Any Key To Continue To Menu:");
            if (new Scanner(System.in).nextInt() == 0) {
                addItem();
            }
        }

    }

    public static void removeItem() {
        Item item = new Item();
        ProductsHandler itemRemover = new ProductsHandler();

        System.out.print("Enter ID of Item:");

        item.setId(new Scanner(System.in).nextInt());
        item = itemRemover.searchDB(item);
        clear();
        System.out.println("\t\t ITEM REMOVAL");
        System.out.println("\t\t ------------");

        if (item.getName() == null) {

            System.out.println("No Such Item, You Could Try Again By Pressing \"0\" and <Enter>");
            System.out.print("Or Pressing Any Key To Continue To Menu:");

            if (new Scanner(System.in).nextInt() == 0) {
                removeItem();
            }

        } else {
            System.out.println(item.infoToString(item));
            System.out.print("Are You Sure You Want To Remove This Item? (yes/no)");
            if (new Scanner(System.in).next().contains("yes")) {

                itemRemover.removeItem(item);
            } else {
                System.out.println("Still want to remove an Item? You Could Try Again By Pressing \"0\" and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");

                if (new Scanner(System.in).nextInt() == 0) {
                    removeItem();

                }

            }
        }

    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}