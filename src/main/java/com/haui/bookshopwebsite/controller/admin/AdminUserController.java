package com.haui.bookshopwebsite.controller.admin;

import com.haui.bookshopwebsite.entity.User;
import com.haui.bookshopwebsite.service.UserService;
import com.haui.bookshopwebsite.controller.common.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@RequestMapping("admin/users_management")
public class AdminUserController extends BaseController {

    private UserService userService;

    @GetMapping
    public String getUsersPage(@RequestParam(name = "page", defaultValue = "1") int page,
                               Model model) {
        int pageSize = 6;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<User> usersPage = userService.getAllUserOrderByCreatedDate(pageable);
        model.addAttribute("users", usersPage);
        return "admin/user";
    }

    @GetMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Người dùng đã được xóa thành công.");
        return "redirect:/admin/users_management";
    }
}
