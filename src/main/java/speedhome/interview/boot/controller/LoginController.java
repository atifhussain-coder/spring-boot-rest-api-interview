package speedhome.interview.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.put("name", "Spring Assessment for Library Application");
        return "welcome";
    }

//    @GetMapping("/login")
//    public String showLogin(Model model) {
//        model.addAttribute("page", "login");
//        return "redirect:/"; // or the name of your Thymeleaf template
//    }
//
//    @GetMapping("/logout")
//    public String showLogout(Model model) {
//        model.addAttribute("page", "logout");
//        return "redirect:/"; // or the name of your Thymeleaf template
//    }

}
