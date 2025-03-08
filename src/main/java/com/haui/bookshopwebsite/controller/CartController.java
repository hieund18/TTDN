package com.haui.bookshopwebsite.controller;

import com.haui.bookshopwebsite.controller.common.BaseController;
import com.haui.bookshopwebsite.dto.AddToCartRequest;
import com.haui.bookshopwebsite.dto.CartDTO;
import com.haui.bookshopwebsite.dto.CartItemDTO;
import com.haui.bookshopwebsite.dto.OrderPerson;
import com.haui.bookshopwebsite.entity.Book;
import com.haui.bookshopwebsite.entity.User;
import com.haui.bookshopwebsite.service.BookService;
import com.haui.bookshopwebsite.service.CartService;
import com.haui.bookshopwebsite.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController extends BaseController {

    private final HttpSession session;
    private final OrderService orderService;
    private CartService cartService;
    private BookService bookService;

    @GetMapping
    public String getCartPage(Model model) {
        CartDTO cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        double totalCart = cart.calculateTotalAmount();
        model.addAttribute("totalCart", totalCart);
        return "user/cart";
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {

        if (getCurrentUser() != null) {
            Long productId = request.getProductId();
            Integer quantity = request.getQuantity();
            Book existingBook = bookService.getBookById(productId);

            if(quantity > existingBook.getQty() || existingBook.getQty() == 0){
                return ResponseEntity.ok("error");
            }

            CartItemDTO addedItem = new CartItemDTO();
            addedItem.setQuantity(quantity);
            addedItem.setBookId(productId);
            addedItem.setTitle(existingBook.getTitle());
            addedItem.setPrice(existingBook.getSalePrice());
            addedItem.setCoverImage(existingBook.getCoverImage());
            cartService.addToCart(session, addedItem);


            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    @PostMapping("/update-cart-item")
    @ResponseBody
    public ResponseEntity<String> updateCartItem(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateCartItemQuantity(session, productId, quantity);
        return ResponseEntity.ok("Cart item updated.");
    }

    @GetMapping("/remove-cart-item/{id}")
    public String removeCartItem(@PathVariable Long id) {
        cartService.removeCartItem(session, id);
        return "redirect:/cart";
    }

    @GetMapping("/cart-item-count")
    @ResponseBody
    public int getCartItemCount() {
        return cartService.getCart(session).getCartItems().size();
    }

    @GetMapping("/checkout")
    public String getCheckOut(Model model) {
        CartDTO cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        double totalCart = cart.calculateTotalAmount();
        model.addAttribute("totalCart", totalCart);

        User curUser = getCurrentUser();
        OrderPerson orderPerson = new OrderPerson();
        orderPerson.setFullName(curUser.getFullName());
        orderPerson.setEmail(curUser.getEmail());
        orderPerson.setPhoneNumber(curUser.getPhoneNumber());
        orderPerson.setAddress(curUser.getAddress());
        model.addAttribute("orderPerson", orderPerson);

        return "user/checkout";
    }

    @GetMapping("/checkout-vnpay")
    public String getCheckOutVnPay(Model model) {
        CartDTO cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        double totalCart = cart.calculateTotalAmount();
        model.addAttribute("totalCart", totalCart);

        User curUser = getCurrentUser();
        OrderPerson orderPerson = new OrderPerson();
        orderPerson.setFullName(curUser.getFullName());
        orderPerson.setEmail(curUser.getEmail());
        orderPerson.setPhoneNumber(curUser.getPhoneNumber());
        orderPerson.setAddress(curUser.getAddress());
        model.addAttribute("orderPerson", orderPerson);

        return "user/checkout-vnpay";
    }

    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute("orderPerson") OrderPerson orderPerson) {
        User curUser = getCurrentUser();
        orderService.createOrder(cartService.getCart(session), curUser, orderPerson);
        cartService.clearCart(session);
        return "redirect:/orders";
    }

//    @PostMapping("/place-order-vnpay")
//    public String placeOrderVnPay(@ModelAttribute("orderPerson") OrderPerson orderPerson){
//
//        return "user/checkout-vnpay";
//    }

}
