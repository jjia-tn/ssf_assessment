package ibf2022.batch2.ssf.ssf_assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ibf2022.batch2.ssf.ssf_assessment.model.Cart;
import ibf2022.batch2.ssf.ssf_assessment.model.Item;
import ibf2022.batch2.ssf.ssf_assessment.model.Shipping;
// import ibf2022.batch2.ssf.ssf_assessment.model.Shipping;
import ibf2022.batch2.ssf.ssf_assessment.service.CartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PurchaseOrderController {

    @Autowired
    private CartService cartSvc;

    // @GetMapping(path = {"/", "view1.html"})
    // public String getIndex(Model model, HttpSession sess) {
    //     sess.invalidate();
    //     model.addAttribute("item", new Item());

    //     return "view1";
    // }

    @GetMapping(path = {"/", "/view1.html"})
    public String getCart(Model model, HttpSession sess) {
        
        Cart cart = (Cart)sess.getAttribute("cart");
        
        if (null == cart) {
            cart = new Cart();
            sess.setAttribute("cart", cart);
        }

        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);

        return "view1";
    }

    @PostMapping(path = "/view1")
    public String postCartOrder(Model model, HttpSession sess, @Valid Item item, BindingResult binding) {

        Cart cart = (Cart)sess.getAttribute("cart");

        if (binding.hasErrors()) {
            model.addAttribute("item", new Item());
            model.addAttribute("cart", cart);
            return "view1";
        }

        List<ObjectError> errors = cartSvc.validateItem(item);
        if (!errors.isEmpty()) {
            for (ObjectError err: errors)
                binding.addError(err);
            return "view1";
        }

        cart.addItemToCart(item);
        model.addAttribute("item", item);
        model.addAttribute("cart", cart);

        return "view1";
        
    }

    @GetMapping(path = "/view2") 
    public String getAddress(Model model, HttpSession sess) {

        Cart cart = (Cart)sess.getAttribute("cart");

        if (null == cart) {

            return "view1";
        }
        
        // sess.invalidate();
        model.addAttribute("shipping", new Shipping());

        return "view2";
    }

    @PostMapping
    public String postOrder(Model model, HttpSession sess, @Valid Shipping shipping, BindingResult binding) {

        if (binding.hasErrors()) {
            return "view2"; 
        }

        // Cart cart = (Cart)sess.getAttribute("cart");

        model.addAttribute("shipping", shipping);

        return "view3";
    }

}
