import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            new InventorySystem().clear();
            new InventorySystem().Menu();
        } catch (IOException e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}