package sg.edu.nus.iss.workshop24.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.workshop24.exception.OrderException;
import sg.edu.nus.iss.workshop24.model.Order;
import sg.edu.nus.iss.workshop24.model.OrderDetail;
import sg.edu.nus.iss.workshop24.model.OrderResult;
import sg.edu.nus.iss.workshop24.service.OrderService;

@Controller
@RequestMapping({ "", "/" })
public class OrderController {

    @Autowired
    OrderService oSvc;

    @PostMapping("/cart")
    public String postCart(@RequestBody MultiValueMap<String, String> form,
            Model model, HttpSession sess) {
        List<OrderDetail> orderDetails = (List<OrderDetail>) sess.getAttribute("cart");
        if (null == orderDetails) {
            orderDetails = new LinkedList();
            sess.setAttribute("cart", orderDetails);
        }
        // to extract product and quantity for OrderDetail constructor
        String product = form.getFirst("item");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        orderDetails.add(new OrderDetail(product, quantity));

        Order o = new Order();
        o.setCustomerName(form.getFirst("name"));
        o.setOrderDetails(orderDetails);
        // System.out.println(o);
        //o has product, name and quantity
        sess.setAttribute("checkoutCart", o);
        model.addAttribute("lineItems", orderDetails);
        return "cart";
    }

    @PostMapping("/checkout")
    public String confirmCheckout(Model model, HttpSession sess)
        throws OrderException{
        List<OrderDetail> orderDetails = (List<OrderDetail>)
                        sess.getAttribute("cart");
        // May have issue
        Order o = (Order)sess.getAttribute("checkoutCart");
        // System.out.println(o);
        OrderResult r = oSvc.createOrder(o); 
        sess.invalidate();
        model.addAttribute("total", orderDetails.size());
        return "checkout";
    }


}
