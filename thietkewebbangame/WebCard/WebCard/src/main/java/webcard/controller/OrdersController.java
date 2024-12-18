package webcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import webcard.model.OrdersRepository;

@Controller
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;

    @GetMapping("/admin/orders")
    public String index(Model model) {
        model.addAttribute("list", ordersRepository.findAll());
        return "orders/index";
    }

    @GetMapping("/orders/add")
    public String add() {
        return "orders/add";
    }

    @PostMapping("/orders/add")
    public String add(webcard.model.Orders obj) {
        ordersRepository.save(obj);
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String edit(@PathVariable("id") short id, Model model) {
        model.addAttribute("o", ordersRepository.findById(id).get());
        return "orders/edit";
    }

    @PostMapping("/orders/edit/{id}")
    public String edit(@PathVariable("id") short id, webcard.model.Orders obj) {
        obj.setId(id);
        ordersRepository.save(obj);
        return "redirect:/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String delete(@PathVariable("id") short id) {
        ordersRepository.deleteById(id);
        return "redirect:/orders";
    }
}
