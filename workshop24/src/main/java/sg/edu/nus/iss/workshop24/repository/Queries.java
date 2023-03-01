package sg.edu.nus.iss.workshop24.repository;

public class Queries {
    public static final String SQL_INSERT_ORDER_TABLE = """
        insert into purchase_order(order_id, customer_name,  
        ship_address, notes, tax) values 
        ( ?, ? ,? ,?, ?)
        """;

    public static final String SQL_INSERT_ORDERDETAILS_TABLE = """
        insert into line_item(product, unit_price,  
        discount, quantity, order_id) values 
        ( ?, ? ,? ,?, ?)
        """;

    public static final String SQL_COUNT_PO_TABLE_PRED_ORDERID = """
        select count(*) from line_item where order_id = ?
        """;
    public static final String SQL_COUNT_LINEITEM_TABLE_PRED_ORDERID = """
        select count(*) from line_item where order_id = ?
        """;
}
