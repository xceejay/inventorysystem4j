
import java.sql.*;
import java.time.LocalDate;

public class Products {

    private int id ;
    private int quantity_out = 0;
    private int quantity_in = 1;
    private String name = null;
    private String description = null;
    private double amount = 0;
    private LocalDate date;
    private Date date_in = null;
    private Date date_out = null;
    // add expiry date to field list and set it equal to the date out

    public Products() {
        date_in = java.sql.Date.valueOf(date.now());
        date_out = java.sql.Date.valueOf(date.now().plusYears(5));

    }

    public Products(int id, int quantity_in, int quantity_out, String name, String description, double amount,
            Date date_added, Date date_out, Date date_in) {
        this.id = id;
        this.quantity_in = quantity_in;
        this.quantity_out = quantity_out;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date_in = java.sql.Date.valueOf(date.now());
        this.date_out = date_out;

    }

 
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param quantity_in the quantity_in to set
     */
    public void setQuantity_in(int quantity_in) {
        this.quantity_in = quantity_in;
    }

    /**
     * @param quantity_out the quantity_out to set
     */
    public void setQuantity_out(int quantity_out) {
        this.quantity_out = quantity_out;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @param date_in the date_in to set
     */
    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    /**
     * @param date_out the date_out to set
     */
    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the date_in
     */
    public Date getDate_in() {
        return date_in;
    }

    /**
     * @return the date_out
     */
    public Date getDate_out() {
        return date_out;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the quantity_in
     */
    public int getQuantity_in() {
        return quantity_in;
    }

    /**
     * @return the quantity_out
     */
    public int getQuantity_out() {
        return quantity_out;
    }
}