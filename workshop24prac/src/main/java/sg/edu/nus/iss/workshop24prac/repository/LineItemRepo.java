package sg.edu.nus.iss.workshop24prac.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop24prac.model.LineItem;
import static sg.edu.nus.iss.workshop24prac.repository.Queries.*;

import java.util.List;

@Repository
public class LineItemRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

public void insertLineItem(List<LineItem> ll, String porderId){
    List<Object[]> arrData = ll.stream()
    .map( i -> {
        //create object[] using LineItem attributes for batchUpdate
        Object[] l = new Object[5];
        l[0] = i.getProduct();
        l[1] = i.getUnitPrice();
        l[2] = i.getDiscount();
        l[3] = i.getQuantity();
        l[4] = porderId;
        //return Object[] l
        return l;
    }).toList();

    jdbcTemplate.batchUpdate(SQL_INSERT_LL_TABLE, arrData);
}
}
