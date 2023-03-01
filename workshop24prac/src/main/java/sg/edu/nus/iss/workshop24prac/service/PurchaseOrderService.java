package sg.edu.nus.iss.workshop24prac.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.workshop24prac.exception.OrderException;
import sg.edu.nus.iss.workshop24prac.model.LineItem;
import sg.edu.nus.iss.workshop24prac.model.OrderResult;
import sg.edu.nus.iss.workshop24prac.model.PurchaseOrder;
import sg.edu.nus.iss.workshop24prac.repository.LineItemRepo;
import sg.edu.nus.iss.workshop24prac.repository.PurchaseOrderRepo;

@Service
public class PurchaseOrderService {
    
    @Autowired
    LineItemRepo lineItemRepo;

    @Autowired
    PurchaseOrderRepo purchaseOrderRepo;

@Transactional(rollbackFor = OrderException.class)
public void createOrder(PurchaseOrder po) throws OrderException{
    String porderId = UUID.randomUUID().toString().substring(0,8);
    po.setPorderId(porderId);
    // Insert PurchaseOrder using purchaseOrderRepo
    purchaseOrderRepo.insertOrder(po);
    // Check List<LineItem> > 3 throw exception
    if(po.getListOfLineItems().size() > 3 ){
        throw new OrderException();
    }

    // Insert List<LineItem> using lineItemRepo
    lineItemRepo.insertLineItem(po.getListOfLineItems(), porderId);
}
}
