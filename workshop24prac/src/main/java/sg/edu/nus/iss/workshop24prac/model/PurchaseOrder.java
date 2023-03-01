package sg.edu.nus.iss.workshop24prac.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    private String porderId;
    private LocalDate orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private BigDecimal tax;
    //One Side (in MySQL, line_item is the 'Many' side)
    private List<LineItem> listOfLineItems = new LinkedList<>();
}
