package webcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import webcard.model.CardsRepository;
import webcard.model.UsersRepository;

@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CardsRepository cardsRepository;

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("list", cardsRepository.findAll());
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/register")
    public String add() {
        return "home/register";
    }

    @PostMapping("/register")
    public String add(webcard.model.Users obj) {
        if (obj.getPassword() == null) {
            System.out.println("Password is null!");
            return "home/register";  // Or handle this case appropriately
        }
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        usersRepository.save(obj);
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "home/index";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome(Model model) {
        model.addAttribute("list", cardsRepository.findAll());
        return "home/index";
    }
}
