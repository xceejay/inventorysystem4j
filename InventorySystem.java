
// import java.io.BufferedReader;
import java.io.IOException;
// import java.io.InputStreamReader;
// import java.sql.*;
import java.util.*;

public class InventorySystem {

    public static void Menu() throws IOException {

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
        System.out.println(" 1. Add Products To Inventory");
        System.out.println(" 2. Remove Products From Inventory");
        System.out.println(" 3. Modify Name Of Product In Inventory");
        System.out.println(" 4. Modify Price Of Product In Inventory");
        System.out.println(" 5. Modify Description Of Product In Inventory");
        System.out.println(" 6. Display & Sort Products By ID");
        System.out.println(" 7. Display & Sort Products By Name");
        System.out.println(" 8. Display & Sort Products By Price");
        System.out.println(" 9. Display & Sort Products By Date In");
        System.out.println("10. Display & Sort Products By Expiry Date");
        System.out.println("11. Display & Sort Products By Products Quantity");
        System.out.println("12. Search For An Item");// newly added module---add to productinterface
        System.out.println("13. Generate Bill");
        System.out.println("14. Exit Program");
        System.out.print("Enter a choice:");
        choice = new Scanner(System.in).nextInt();
        clear();
        switch (choice) {
            case 1:
                addItem(); // add items to database
                clear();
                Menu();

                break;

            case 2:
                removeItem(); // remove items from database
                clear();
                Menu();
                break;

            case 3:
                modifyItemName();
                clear();
                Menu();
                break;

            case 4:
                clear();
                Menu();
                break;

            case 5:
                modifyItemDescription();
                clear();
                Menu();
                break;

            case 6:
                displayProductsID();
                clear();
                Menu();
                break;

            case 7:
                displayProductsName();
                clear();
                Menu();
                break;

            case 8:
                displayProductsAmount();
                clear();
                Menu();
                break;

            case 9:
                displayProductsDate_in();
                clear();
                Menu();
                break;
            case 10:
                displayProductsExp_Date();
                clear();
                Menu();
                break;
            case 11:
                displayProductsQuantity_in();
                clear();
                Menu();
                break;
            case 12:
                searchItem();
                clear();
                Menu();
                break;

            case 13:

                clear();
                Menu();
                break;
            case 14:

                System.exit(1);
                break;

            default:
                Menu();
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

    public static boolean addItem() {
        Item item = new Item();
        ProductsHandler itemAdder = new ProductsHandler();
        

        System.out.println("[0] Increase Quantity of Existing Item");
        System.out.println("[1] Add New Item To Database");
        System.out.print("Enter your choice:");
        int choice = new Scanner(System.in).nextInt();
      
        if (choice == 1) {

            System.out.print("Enter Name of Item:");

            item.setName(new Scanner(System.in).nextLine());
            System.out.print("Enter Price of Item:$");
            item.setAmount(new Scanner(System.in).nextDouble());
            System.out.print("Enter Description of Item:");
            item.setDescription(new Scanner(System.in).nextLine());
            System.out.print("Enter How Many of This Item is being added:");
            item.setQuantity_in(new Scanner(System.in).nextInt());
            System.out.println("\nNOTICE : ID is 0 because it has not been assigned one yet");
            System.out.println(item.infoToString(item));

            System.out.print("ARE YOU SURE YOU WANT TO ADD THIS ITEM?(Y/N)");
            if (new Scanner(System.in).next().equalsIgnoreCase("Y")) {

                if (itemAdder.addItem(item)) {
                    System.out.println("SUCESSFULLY ADDED+! Add Another Item Again By Pressing y and <Enter>");
                    System.out.print("Or Pressing Any Key To Continue To Menu:");
                }
            } else {
                System.out.println("ADDITION STOPPED! Add Another Item Again By Pressing y and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");
            }
            if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
                addItem();
            }
        } else if (choice == 0) {

            System.out.print("Want To See Database For Item's ID Before You Proceed? (y/n)?");
        
            if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
                displayProductsDefault();
            }
            System.out.print("Enter ID of Item:");
            item.setId(new Scanner(System.in).nextInt());
            if (String.valueOf(item.getId()) == null) {
                System.out.println("No Such Item, You Could Try Again By Pressing y and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");
                if (new Scanner(System.in).next().contains("y")) {
                    addItem();
                }
            } else
                System.out.print("Enter How Many of This Item is being added:");
            int quantity = new Scanner(System.in).nextInt();
            try {
                itemAdder.increaseQuantity(item, quantity);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            System.out.println("Still want to ADD an Item? You Could Do That By Pressing y and <Enter>");
            System.out.print("Or Pressing Any Key To Continue To Menu:");

            if (new Scanner(System.in).next().contains("y")) {

                addItem();

            }

        }

        return true;
    }

    public static boolean removeItem() {
        Item item = new Item();
        ProductsHandler itemRemover = new ProductsHandler();
        boolean sucessful = false;
        System.out.println("[0] Remove Item Permanently");
        System.out.println("[1] Remove Item and Still Keep It In The Database");
        System.out.print("Enter your choice:");
        int choice = new Scanner(System.in).nextInt();

        System.out.print("Want To See Database For Item's ID Before You Proceed? (y/n)?");

        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            displayProductsDefault();
        }
        if (choice == 1) {
            System.out.print("Enter ID of Item:");

            item.setId(new Scanner(System.in).nextInt());
            item = itemRemover.searchDB(item);

            System.out.println("\t\t ITEM REMOVAL");
            System.out.println("\t\t ------------");

            if (String.valueOf(item.getId()) == null) {

                System.out.println("No Such Item, You Could Try Again By Pressing y and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");

                if (new Scanner(System.in).next().contains("y")) {
                    removeItem();
                }

            } else {
                System.out.println(item.infoToString(item));
                System.out.print("Are You Sure You Want To Remove This Item? (yes/no)");
                if (new Scanner(System.in).next().contains("y")) {
                    System.out.print("How many?");
                    int quantity = new Scanner(System.in).nextInt();
                    if (itemRemover.removeItem(item, quantity)) {
                        System.out.println("Sucessfully Removed  " + quantity + " of Item " + item.getId()
                                + "(ID) from the Inventory️🔔\n");
                        sucessful = true;

                    } else {
                        System.out.println("Failed To Remove Item: Quantity stated exceeds amout of Items️️🚫\n");
                        sucessful = false;
                    }
                } else {
                    System.out.println("Still want to remove an Item? You Could Do That By Pressing y and <Enter>");
                    System.out.print("Or Pressing Any Key To Continue To Menu:");

                    if (new Scanner(System.in).next().contains("y")) {

                        removeItem();

                    }

                }

            }
        } else if (choice == 0) {
            System.out.print("Enter ID of Item:");

            item.setId(new Scanner(System.in).nextInt());
            item = itemRemover.searchDB(item);

            System.out.println("\t\t ITEM REMOVAL");
            System.out.println("\t\t ------------");

            if (String.valueOf(item.getId()) == null) {

                System.out.println("No Such Item, You Could Try Again By Pressing y and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");

                if (new Scanner(System.in).next().contains("y")) {
                    removeItem();
                }

            } else {
                System.out.println(item.infoToString(item));
                System.out.print("Are You Sure You Want To Remove This Item? (yes/no)");
                if (new Scanner(System.in).next().contains("y")) {
                    if (itemRemover.removeItemPermanently(item)) {
                        System.out.println("Sucessfully Removed  Item " + item.getId()
                                + "(ID) permanently from the Inventory️🔔\n");
                        sucessful = true;
                        System.out.println("Still want to remove an Item? You Could Do That By Pressing y and <Enter>");
                        System.out.print("Or Pressing Any Key To Continue To Menu:");

                        if (new Scanner(System.in).next().contains("y")) {

                            removeItem();

                        }

                    }
                }
            }
        } else {
            System.out.println("Input Should Be In The Range Of [0-1],Try Again");
            removeItem();
        }
        return sucessful;

    }

    public static void searchItem() {
        ProductsHandler itemFinder = new ProductsHandler();
        Item item = new Item();

        System.out.println("\t\t ITEM REMOVAL");
        System.out.println("\t\t ------------");

        System.out.print("Search By Name Or ID?");

        String searchBy = new Scanner(System.in).next();
        // soon implement name search

        if (searchBy.matches("[Nn][Aa][Mm][Ee]")) {
            System.out.println("What Item Do You Want Information On?");
            System.out.print("Type Name Here:");
            item.setName(new Scanner(System.in).nextLine());
            item = itemFinder.searchDB(item);

            System.out.println(item.infoToString(item));
        } else if (searchBy.matches("[Ii][Dd]")) {
            System.out.println("What Item Do You Want Information On?");
            System.out.print("Type ID Here:");
            item.setId(new Scanner(System.in).nextInt());
            item = itemFinder.searchDB(item);

            System.out.println(item.infoToString(item));
        } else {
            System.out.println("Incorrect Input");
            System.out.print("Press \"y\" To Search For Another Item Or Any Key To Continue To Menu:");
            if (new Scanner(System.in).next().contains("y")) {
                searchItem();

            }

        }

        System.out.print("Press \"y\" To Search For Another Item Or Any Key To Continue To Menu:");
        if (new Scanner(System.in).next().contains("y")) {
            searchItem();

        }

    }

    public static void displayProductsDefault() {

        ProductsHandler inventoryDisplayer = new ProductsHandler();
        // int input = -1;

        // do {
        // System.out.println("Press [1] To Sort By ID In Ascending Order Or [0] To Sort
        // In Desending Order");
        // System.out.print("Type Choice Here:");
        // input = new Scanner(System.in).nextInt();

        // if (input != 0 && input != 1) {
        // System.out.println(input);
        // System.out.println("Input Should Be In The Range Of [0-1],Try Again");
        // } else
        // break;

        // } while (input != 0 && input != 1);

        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.println("\t\t\t\t\t\t\t  PRODUCTS TABLE ");
        System.out.println("\t\t\t\t\t\t\t  -------------- ");

        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "ID", "NAME", "DESCRIPTION", "PRICE",
                "DATE IN", "EXPIRY DATE", "QUANTITY IN", "QUANTITY OUT");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "--", "----", "-----------", "-----",
                "-------", "-----------", "-----------", "------------");

        System.out.println(inventoryDisplayer.viewProductsID(1));

    }

    public static void displayProductsID() {

        ProductsHandler inventoryDisplayer = new ProductsHandler();
        int input = -1;

        do {
            System.out.println("Press [1] To Sort By ID In Ascending Order Or [0] To Sort In Desending Order");
            System.out.print("Type Choice Here:");
            input = new Scanner(System.in).nextInt();

            if (input != 0 && input != 1) {
                System.out.println(input);
                System.out.println("Input Should Be In The Range Of [0-1],Try Again");
            } else
                break;

        } while (input != 0 && input != 1);

        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.println("\t\t\t\t\t\t\t  PRODUCTS TABLE ");
        System.out.println("\t\t\t\t\t\t\t  -------------- ");

        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "ID", "NAME", "DESCRIPTION", "PRICE",
                "DATE IN", "EXPIRY DATE", "QUANTITY IN", "QUANTITY OUT");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "--", "----", "-----------", "-----",
                "-------", "-----------", "-----------", "------------");

        System.out.println(inventoryDisplayer.viewProductsID(input));

        System.out.print("Press Any Key To Continue To Menu:");

        new Scanner(System.in).next();

    }

    public static void displayProductsName() {

        ProductsHandler inventoryDisplayer = new ProductsHandler();

        int input = -1;

        do {
            System.out.println("Press [1] To Sort By Name In Ascending Order Or [0] To Sort In Desending Order");
            System.out.print("Type Choice Here:");
            input = new Scanner(System.in).nextInt();
            if (input != 0 && input != 1) {
                System.out.println("Input Should Be In The Range Of 0-1,Try Again");
            } else {
                break;
            }

        } while (input != 0 && input != 1);

        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.println("\t\t\t\t\t\t\t  PRODUCTS TABLE ");
        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "ID", "NAME", "DESCRIPTION", "PRICE",
                "DATE IN", "EXPIRY DATE", "QUANTITY IN", "QUANTITY OUT");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "--", "----", "-----------", "-----",
                "-------", "-----------", "-----------", "------------");

        System.out.println(inventoryDisplayer.viewProductsName(input));

        System.out.print("Press Any Key To Continue To Menu:");

        new Scanner(System.in).next();
    }

    public static void displayProductsExp_Date() {

        ProductsHandler inventoryDisplayer = new ProductsHandler();

        int input = -1;

        do {

            System.out
                    .println("Press [1] To Sort By Expiry Dates In Ascending Order Or [0] To Sort In Desending Order");
            System.out.print("Type Choice Here:");

            input = new Scanner(System.in).nextInt();
            if (input != 0 && input != 1) {
                System.out.println("Input Should Be In The Range Of 0-1,Try Again");
            } else
                break;
        } while (input != 0 && input != 1);

        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.println("\t\t\t\t\t\t\t  PRODUCTS TABLE ");
        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "ID", "NAME", "DESCRIPTION", "PRICE",
                "DATE IN", "EXPIRY DATE", "QUANTITY IN", "QUANTITY OUT");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "--", "----", "-----------", "-----",
                "-------", "-----------", "-----------", "------------");

        System.out.println(inventoryDisplayer.viewProductsExp_Date(input));

        System.out.print("Press Any Key To Continue To Menu:");

        new Scanner(System.in).next();

    }

    public static void displayProductsDate_in() {

        ProductsHandler inventoryDisplayer = new ProductsHandler();

        int input = -1;

        do {
            System.out.println("Press [1] To Sort By Date In, In Ascending Order Or [0] To Sort In Desending Order");
            System.out.print("Type Choice Here:");
            input = new Scanner(System.in).nextInt();
            if (input != 0 && input != 1) {
                System.out.println("Input Should Be In The Range Of 0-1,Try Again");
            } else
                break;
        } while (input != 0 && input != 1);

        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.println("\t\t\t\t\t\t\t  PRODUCTS TABLE ");
        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "ID", "NAME", "DESCRIPTION", "PRICE",
                "DATE IN", "EXPIRY DATE", "QUANTITY IN", "QUANTITY OUT");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "--", "----", "-----------", "-----",
                "-------", "-----------", "-----------", "------------");

        System.out.println(inventoryDisplayer.viewProductsDate_in(input));

        System.out.print("Press Any Key To Continue To Menu:");

        new Scanner(System.in).next();
    }

    public static void displayProductsAmount() {

        ProductsHandler inventoryDisplayer = new ProductsHandler();

        int input = -1;

        do {

            System.out.println("Press [1] To Sort By Price In Ascending Order Or [0] To Sort In Desending Order");
            System.out.print("Type Choice Here:");
            input = new Scanner(System.in).nextInt();
            if (input != 0 && input != 1) {
                System.out.println("Input Should Be In The Range Of 0-1,Try Again");
            } else
                break;

        } while (input != 0 && input != 1);

        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.println("\t\t\t\t\t\t\t  PRODUCTS TABLE ");
        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "ID", "NAME", "DESCRIPTION", "PRICE",
                "DATE IN", "EXPIRY DATE", "QUANTITY IN", "QUANTITY OUT");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "--", "----", "-----------", "-----",
                "-------", "-----------", "-----------", "------------");

        System.out.println(inventoryDisplayer.viewProductsAmount(input));

        System.out.print("Press Any Key To Continue To Menu:");

        new Scanner(System.in).next();
    }

    private static void displayProductsQuantity_in() {

        ProductsHandler inventoryDisplayer = new ProductsHandler();

        int input = -1;

        do {

            System.out.println("Press [1] To Sort By Quantity Left Ascending Order Or [0] To Sort In Desending Order");
            System.out.print("Type Choice Here:");
            input = new Scanner(System.in).nextInt();
            if (input != 0 && input != 1) {
                System.out.println("Input Should Be In The Range Of 0-1,Try Again");
            } else
                break;

        } while (input != 0 && input != 1);

        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.println("\t\t\t\t\t\t\t  PRODUCTS TABLE ");
        System.out.println("\t\t\t\t\t\t\t  -------------- ");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "ID", "NAME", "DESCRIPTION", "PRICE",
                "DATE IN", "EXPIRY DATE", "QUANTITY IN", "QUANTITY OUT");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "--", "----", "-----------", "-----",
                "-------", "-----------", "-----------", "------------");

        System.out.println(inventoryDisplayer.viewProductsQuantity_in(input));

        System.out.print("Press Any Key To Continue To Menu:");

        new Scanner(System.in).next();
    }

    public static void modifyItemName() {
        Item olditem = new Item();
        Item newitem = new Item();
        ProductsHandler itemModifier = new ProductsHandler();
        System.out.print("Want To See Database For Item's ID Before You Proceed? (y/n)?");

        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            displayProductsDefault();
        }
        System.out.print("Enter The ID Of The Item's Name You Want To Change:");
        newitem.setId(new Scanner(System.in).nextInt());
        olditem.setId(newitem.getId());
        System.out.print("Enter New Name Of Item:");
        newitem.setName(new Scanner(System.in).nextLine());

        System.out.println("\t\tModified Item");
        System.out.println("\t\t-------------");
        olditem = itemModifier.searchDB(olditem);
        System.out.println(olditem.infoToString(olditem));
        System.out.print("ARE YOU SURE YOU WANT TO MODIFY THIS ITEM?(Y/N)");
        if (new Scanner(System.in).next().equalsIgnoreCase("Y")) {

            if (itemModifier.editName(newitem, newitem.getName())) {
                System.out.println("SUCESSFULLY MODIFIED! MODIFY Another Item Again By Pressing y and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");
            }
        } else {
            System.out.println("MODIFICATION STOPPED! MODIFY Another Item Again By Pressing y and <Enter>");
            System.out.print("Or Pressing Any Key To Continue To Menu:");
        }
        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            modifyItemName();
        }

    }

    public static void modifyItemPrice() {
        Item olditem = new Item();
        Item newitem = new Item();
        ProductsHandler itemModifier = new ProductsHandler();
        System.out.print("Want To See Database For Item's ID Before You Proceed? (y/n)?");

        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            displayProductsDefault();
        }
        System.out.print("Enter The ID Of The Item's Price You Want To Change:");
        newitem.setId(new Scanner(System.in).nextInt());
        olditem.setId(newitem.getId());
        System.out.print("Enter New Price Of Item:");
        newitem.setAmount(new Scanner(System.in).nextDouble());

        System.out.println("\t\tModified Item");
        System.out.println("\t\t-------------");
        olditem = itemModifier.searchDB(olditem);
        System.out.println(olditem.infoToString(olditem));
        System.out.print("ARE YOU SURE YOU WANT TO MODIFY THIS ITEM?(Y/N)");
        if (new Scanner(System.in).next().equalsIgnoreCase("Y")) {

            if (itemModifier.editAmount(newitem, newitem.getAmount())) {
                System.out.println("SUCESSFULLY MODIFIED! MODIFY Another Item Again By Pressing y and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");
            }
        } else {
            System.out.println("MODIFICATION STOPPED! MODIFY Another Item Again By Pressing y and <Enter>");
            System.out.print("Or Pressing Any Key To Continue To Menu:");
        }
        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            modifyItemPrice();
        }

    }

    public static void modifyItemDescription() {
        Item olditem = new Item();
        Item newitem = new Item();
        ProductsHandler itemModifier = new ProductsHandler();
        System.out.print("Want To See Database For Item's ID Before You Proceed? (y/n)?");

        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            displayProductsDefault();
        }
        System.out.print("Enter The ID Of The Item's Description You Want To Change:");
        newitem.setId(new Scanner(System.in).nextInt());
        olditem.setId(newitem.getId());
        System.out.print("Enter New Description Of Item:");
        newitem.setDescription(new Scanner(System.in).nextLine());

        System.out.println("\t\tModified Item");
        System.out.println("\t\t-------------");
        olditem = itemModifier.searchDB(olditem);
        System.out.println(olditem.infoToString(olditem));
        System.out.print("ARE YOU SURE YOU WANT TO MODIFY THIS ITEM?(Y/N)");
        if (new Scanner(System.in).next().equalsIgnoreCase("Y")) {

            if (itemModifier.editDescription(newitem, newitem.getDescription())) {
                System.out.println("SUCESSFULLY MODIFIED! MODIFY Another Item Again By Pressing y and <Enter>");
                System.out.print("Or Pressing Any Key To Continue To Menu:");
            }
        } else {
            System.out.println("MODIFICATION STOPPED! MODIFY Another Item Again By Pressing y and <Enter>");
            System.out.print("Or Pressing Any Key To Continue To Menu:");
        }
        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            modifyItemDescription();
        }

    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
