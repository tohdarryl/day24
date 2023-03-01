package sg.edu.nus.iss.workshop24prac.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop24prac.model.PurchaseOrder;

import static sg.edu.nus.iss.workshop24prac.repository.Queries.*;

@Repository
public class PurchaseOrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

public Boolean insertOrder(PurchaseOrder po){
    return jdbcTemplate.update(SQL_INSERT_PO_TABLE, 
    po.getPorderId(),
    po.getOrderDate(),
    po.getCustomerName(),
    po.getShipAddress(),
    po.getNotes(),
    po.getTax()) > 0;
    // '>0' becomes a boolean; 1 is true, 0 is false
}

}
