package sg.edu.nus.iss.workshop24prac.controller;


import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.workshop24prac.exception.OrderException;
import sg.edu.nus.iss.workshop24prac.model.LineItem;
import sg.edu.nus.iss.workshop24prac.model.PurchaseOrder;
import sg.edu.nus.iss.workshop24prac.service.PurchaseOrderService;

@Controller
@RequestMapping(path = {"","/index.html"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    
    @Autowired
    PurchaseOrderService poSvc;


    @PostMapping("/cart")
    public String postCart(@RequestBody MultiValueMap<String, String> form, 
        Model model, HttpSession sess) {
            List<LineItem> lineItems = (List<LineItem>) sess.getAttribute("cart");
            if (lineItems == null) {
                lineItems = new LinkedList<>();
                // instantiate new List 
                sess.setAttribute("cart", lineItems);
            }
            
            // Index.html has item, quantity and name to be extracted and used
            LineItem lineItem = new LineItem();
            lineItem.setProduct(form.getFirst("item"));
            lineItem.setQuantity(Integer.parseInt(form.getFirst("quantity")));
            lineItems.add(lineItem);

            PurchaseOrder po = new PurchaseOrder();
            po.setCustomerName(form.getFirst("name"));
            po.setListOfLineItems(lineItems);
            po.setOrderDate(LocalDate.now());
            sess.setAttribute("checkoutCart", po);
            model.addAttribute("lineItems", lineItems);

            return "cart";
        }

        @PostMapping("/checkout")
        public String confirmCheckout(Model model, HttpSession sess)
            throws OrderException{
            List<LineItem> lineItems = (List<LineItem>)
                            sess.getAttribute("cart");
            // May have issue
            PurchaseOrder po = (PurchaseOrder)sess.getAttribute("checkoutCart");
            // System.out.println(o);
            poSvc.createOrder(po); 
            sess.invalidate();
            model.addAttribute("total", lineItems.size());
            return "checkout";
        }
}
