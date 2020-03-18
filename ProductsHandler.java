import java.sql.*;
import java.util.ArrayList;

public class ProductsHandler extends Products implements ProductsInterface {

    @Override
    public boolean addItem(Item item) {

        // firesultst check if item exits in table firesultst $$todo

        try {
            DatabaseHandler myconnection = new DatabaseHandler();

            Connection connection = myconnection.getMyDatabase();

            PreparedStatement statement = connection.prepareStatement(
                    "insert into products (name,description,amount,quantity_in,quantity_out,date_in,date_out) values( "
                            + item.toString(item) + ")");

            statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean removeItem(Item item, int quantity) {
        boolean sucessful = false;
        try {
            if (reduceQuantity(item, quantity)) {
                sucessful = true;

            } else {
                sucessful = false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub

        if (sucessful)
            return true;
        else
            return false;
    }

    @Override
    public boolean editName(String oldName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean editDescription(String oldDescription) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean editAmount(String oldAmount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String viewProductsID() {
        String Table = "";
        // TODO Auto-generated method stub

        try {
            DatabaseHandler myconnection = new DatabaseHandler();

            Connection connection = myconnection.getMyDatabase();

            PreparedStatement statement = connection.prepareStatement("select * from products");

            ResultSet results = statement.executeQuery();

            int i = 0;

            while (results.next()) {
                ArrayList<Integer> id = new ArrayList<>();
                ArrayList<String> name = new ArrayList<>();
                ArrayList<String> description = new ArrayList<>();
                ArrayList<Double> amount = new ArrayList<>();
                ArrayList<Date> date_in = new ArrayList<>();
                ArrayList<Date> date_out = new ArrayList<>();
                ArrayList<Integer> quantity_in = new ArrayList<>();
                ArrayList<Integer> quantity_out = new ArrayList<>();

                id.add((int) Double.parseDouble(results.getString("id")));
                name.add(results.getString("name"));
                description.add(results.getString("description"));
                amount.add(Double.parseDouble(results.getString("amount")));
                date_in.add(Date.valueOf(results.getString("date_in")));
                date_out.add(Date.valueOf(results.getString("date_out")));
                quantity_in.add((int) Double.parseDouble(results.getString("quantity_in")));
                quantity_out.add((int) Double.parseDouble(results.getString("quantity_out")));
                
                
                Table += String.format("%-15s %-15s %-15s $%-14.2f %-15s %-15s %-15s %-15s %n", id.get(i), name.get(i),description.get(i) , amount.get(i)
                        , date_in.get(i) , date_out.get(i) , quantity_in.get(i) , quantity_out.get(i));
                

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Table;
    }

    @Override
    public String viewProductsName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String generateBill() {
        // TODO Auto-generated method stub
        return null;
    }

    public Item searchDB(Item item) {

        try {
            DatabaseHandler myconnection = new DatabaseHandler();
            Connection connection = myconnection.getMyDatabase();
            String search = "SELECT * FROM products WHERE  id=" + item.getId() + " OR name=\"" + item.getName() + "\"";
            PreparedStatement statement = connection.prepareStatement(search);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int id = (int) Double.parseDouble(results.getString("id"));
                String name = results.getString("name");
                String description = results.getString("description");
                double amount = Double.parseDouble(results.getString("amount"));
                Date date_in = Date.valueOf(results.getString("date_in"));
                Date date_out = Date.valueOf(results.getString("date_out"));
                int quantity_in = (int) Double.parseDouble(results.getString("quantity_in"));
                int quantity_out = (int) Double.parseDouble(results.getString("quantity_out"));
                item.setId(id);
                item.setName(name);
                item.setDescription(description);
                item.setAmount(amount);
                item.setDate_in(date_in);
                item.setDate_out(date_out);
                item.setQuantity_in(quantity_in);
                item.setQuantity_out(quantity_out);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public boolean increaseQuantity(Item item, int quantity) throws Exception {

        DatabaseHandler myconnection = new DatabaseHandler();
        Connection connection = myconnection.getMyDatabase();
        if (quantity > item.getQuantity_in()) {
            // UPDATE products SET quantity_in=quantity_in + 1 WHERE id =1

            return false;
        } else

            item.setQuantity_out(item.getQuantity_out() + quantity);

        String update = "UPDATE products SET quantity_out= " + item.getQuantity_out() + "  WHERE id =" + item.getId();
        PreparedStatement statement = connection.prepareStatement(update);
        ResultSet results = statement.executeQuery();

        return true;
    }

    @Override
    public boolean reduceQuantity(Item item, int quantity) throws Exception {
        DatabaseHandler myconnection = new DatabaseHandler();
        Connection connection = myconnection.getMyDatabase();
        if (quantity > item.getQuantity_in()) {
            // UPDATE products SET quantity_in=quantity_in + 1 WHERE id =1

            return false;
        } else

            item.setQuantity_in(item.getQuantity_in() - quantity);

        String update = "UPDATE products SET quantity_in= " + item.getQuantity_in() + "  WHERE id =" + item.getId();
        PreparedStatement statement = connection.prepareStatement(update);
        ResultSet results = statement.executeQuery();

        item.setQuantity_out(item.getQuantity_out() + quantity);
        update = "UPDATE products SET quantity_out= " + item.getQuantity_out() + "  WHERE id =" + item.getId();
        statement = connection.prepareStatement(update);
        results = statement.executeQuery();
        return true;
    }

}