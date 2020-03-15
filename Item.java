public class Item extends Products {

    public Item() {

    }

    public String toString(Item item) {
        // description,amount,quantity_in,quantity_out,date_in,date_out

        return " '" + item.getName() + "'," + " '" + item.getDescription() + "'," + " '" + item.getAmount() + "',"
                + " '" + item.getQuantity_in() + "'," + " '" + item.getQuantity_out() + "'," + " '" + item.getDate_in()
                + "'," + " '" + item.getDate_out()+"'";

    }
}