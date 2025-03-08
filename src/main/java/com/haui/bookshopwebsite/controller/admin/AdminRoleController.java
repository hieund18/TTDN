package com.haui.bookshopwebsite.controller.admin;

import com.haui.bookshopwebsite.service.RoleService;
import com.haui.bookshopwebsite.controller.common.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/roles_management")
public class AdminRoleController extends BaseController {
    private final RoleService roleService;
}

