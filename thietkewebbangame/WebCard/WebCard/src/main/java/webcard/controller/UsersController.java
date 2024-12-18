package webcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import webcard.model.UsersRepository;

@Controller
public class UsersController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository usersRepository;
    
    @GetMapping("/admin/users")
    public String index(Model model) {
        model.addAttribute("list", usersRepository.findAll());
        return "users/index";
    }

    @GetMapping("/users/add")
    public String add() {
        return "users/add";
    }

    @PostMapping("/users/add")
    public String add(webcard.model.Users obj) {
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        usersRepository.save(obj);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable("id") short id, Model model) {
        model.addAttribute("o", usersRepository.findById(id).get());
        return "users/edit";
    }

    @PostMapping("/users/edit/{id}")
    public String edit(@PathVariable("id") short id, webcard.model.Users obj) {
        obj.setId(id);
        usersRepository.save(obj);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") short id) {
        usersRepository.deleteById(id);
        return "redirect:/users";
    }
}
