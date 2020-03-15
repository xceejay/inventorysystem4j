import java.sql.*;

public class ProductsHandler extends Products implements ProductsInterface {

    @Override
    public int addItem(Item item) {

        // first check if item exits in table first $$todo

        try {
            DatabaseHandler myconnection = new DatabaseHandler();;

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
        return 0;
    }

    @Override
    public int removeItem(String item) {
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        return null;
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

}