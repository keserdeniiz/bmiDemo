package com.example.bmidemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ReportsController {

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestParam("adminId") String adminId,
                             @RequestParam("password") String password,
                             RedirectAttributes redirectAttributes) {
        // Perform login validation
        if (adminId.equals("21995812") && password.equals("1234")) {
            // Redirect to the reports page
            return "redirect:/reports.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid admin ID or password");
            return "redirect:/admin-login.html";
        }
    }

    @GetMapping("/reports")
    public String showReportsPage() {
        return "reports";
    }
}
