package sg.edu.nus.iss.workshop24.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop24.model.Order;
// import sg.edu.nus.iss.workshop24.repository.Queries.*;
@Repository
public class OrderRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final String SQL_INSERT_ORDER_TABLE = """
        insert into orders(order_id, customer_name,  
        ship_address, notes, tax) values 
        ( ?, ? ,? ,?, ?)
        """;

    public boolean insertOrder(Order o){
        return jdbcTemplate.update(SQL_INSERT_ORDER_TABLE,
        o.getOrderId(),
        o.getCustomerName(),
        o.getShipAddress(),
        o.getNotes(),
        o.getTax() ) > 0;
        // '> 0' becomes a boolean; 1 is true, 0 is false
    } 

    public int getOByOrderId(){
        return 0; 
    }
}
