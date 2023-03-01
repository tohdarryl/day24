package sg.edu.nus.iss.workshop24prac.repository;

public class Queries {
    public static final String SQL_INSERT_LL_TABLE =
    "insert into line_item(product, unit_price, discount, quantity, porder_id) values (?, ?, ?, ?, ?)";

    public static final String SQL_INSERT_PO_TABLE =
    "insert into purchase_order(porder_id, order_date, customer_name, ship_address, notes, tax) values (?, ?, ?, ?, ?, ?)";

}
