package sg.edu.nus.iss.workshop24prac.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
    private Integer id;
    private String product;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private Integer quantity;
    private String porderId;
}
