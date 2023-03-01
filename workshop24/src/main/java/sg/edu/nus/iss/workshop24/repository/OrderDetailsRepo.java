package sg.edu.nus.iss.workshop24.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop24.model.OrderDetail;

@Repository
public class OrderDetailsRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final String SQL_INSERT_ORDERDETAILS_TABLE = """
        insert into order_details(product, unit_price,  
        discount, quantity, order_id) values 
        ( ?, ? ,? ,?, ?)
        """;


    // Using .stream() to map OrderDetail Object attributes to Object []
    public void addOrderDetails(List<OrderDetail> OrderDetails, 
        String orderId){

        List<Object[]> arrData =  OrderDetails.stream()
        .map( li -> {
            //create object [] using OrderDetail attributes for batchUpdate
            Object[] l = new Object[5];
            l[0] = li.getProduct();
            l[1] = li.getUnitPrice();
            l[2] = li.getDiscount();
            l[3] = li.getQuantity();
            l[4] = orderId;
            return l;
        }).toList();
    
        jdbcTemplate.batchUpdate(SQL_INSERT_ORDERDETAILS_TABLE, arrData);

    }

    public int getOrderDetailCountByOrderId(){
        return 0; 
    }
    
}
