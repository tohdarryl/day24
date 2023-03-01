package sg.edu.nus.iss.workshop24prac.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResult {
    private int insertCntForPurchaseOrder;
    private int insertCntForLineItem;
}
