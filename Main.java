import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            new InventorySystem().clear();
            new InventorySystem().Menu();
        } catch (Exception e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}