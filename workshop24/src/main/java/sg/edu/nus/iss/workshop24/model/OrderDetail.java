package sg.edu.nus.iss.workshop24.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Integer id;
    private Integer quantity;
    private String product;
    private String orderId;
    private BigDecimal unitPrice;
    private BigDecimal discount;


    public OrderDetail(String product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
    }
}
