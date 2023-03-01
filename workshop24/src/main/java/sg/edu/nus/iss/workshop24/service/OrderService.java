package sg.edu.nus.iss.workshop24.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.workshop24.exception.OrderException;
import sg.edu.nus.iss.workshop24.model.Order;
import sg.edu.nus.iss.workshop24.model.OrderResult;
import sg.edu.nus.iss.workshop24.repository.OrderDetailsRepo;
import sg.edu.nus.iss.workshop24.repository.OrderRepo;

@Service
public class OrderService {
    
    @Autowired
    OrderRepo oRepo;

    @Autowired
    OrderDetailsRepo detailRepo;

    @Transactional(rollbackFor = OrderException.class)
    public OrderResult createOrder(Order o) throws OrderException{
        String orderId= UUID.randomUUID().toString()
            .substring(0, 8);
        o.setOrderId(orderId);
        // Insert Order using OrderRepo
        oRepo.insertOrder(o);
        // check List<OrderDetail> > 3 throw exception
        if(o.getOrderDetails().size() > 3){
            throw new OrderException();
        }

        // Insert Order Details List using OrderDetailsRepo
        detailRepo.addOrderDetails(o.getOrderDetails(), orderId);

        OrderResult r = new OrderResult();
        
        return r;
    }
}
