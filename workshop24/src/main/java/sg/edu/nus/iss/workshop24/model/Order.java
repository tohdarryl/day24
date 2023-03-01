package sg.edu.nus.iss.workshop24.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private BigDecimal tax;
    //One Side (in MySQL, order_detail is the 'Many' side)
    private List<OrderDetail> OrderDetails = new LinkedList<>();


    public void addOrderDetail(OrderDetail od){
        this.OrderDetails.add(od);
    }
}
