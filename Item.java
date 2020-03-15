public class Item extends Products {

    public Item() {

    }

    public String toString(Item item) {
        // description,amount,quantity_in,quantity_out,date_in,date_out

        return " '" + item.getName() + "'," + " '" + item.getDescription() + "'," + " '" + item.getAmount() + "',"
                + " '" + item.getQuantity_in() + "'," + " '" + item.getQuantity_out() + "'," + " '" + item.getDate_in()
                + "'," + " '" + item.getDate_out() + "'";

    }

    public String infoToString(Item item){
            return "ID:" + item.getId()+"\n"+
             "NAME:" + item.getName()+"\n"+ 
             "DESCRIPTION:" + item.getDescription()+"\n"+ 
             "PRICE:$" + item.getAmount()+"\n"+
             "DATE_IN:" + item.getDate_in()+"\n"+ 
             "DATE_OUT:" + item.getDate_out()+"\n"+ 
             "QUANTITY_IN:" + item.getQuantity_in()+"\n"+ 
             "QUANTITY_OUT:" + item.getQuantity_out();
    }
}