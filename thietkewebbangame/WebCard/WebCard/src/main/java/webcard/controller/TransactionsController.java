package webcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import webcard.model.TransactionsRepository;

@Controller
public class TransactionsController {

    @Autowired
    TransactionsRepository transactionsRepository;

    @GetMapping("/admin/transactions")
    public String index(Model model) {
        model.addAttribute("list", transactionsRepository.findAll());
        return "transactions/index";
    }

    @GetMapping("/transactions/add")
    public String add() {
        return "transactions/add";
    }

    @PostMapping("/transactions/add")
    public String add(webcard.model.Transactions obj) {
        transactionsRepository.save(obj);
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/edit/{id}")
    public String edit(@PathVariable("id") short id, Model model) {
        model.addAttribute("o", transactionsRepository.findById(id).get());
        return "transactions/edit";
    }

    @PostMapping("/transactions/edit/{id}")
    public String edit(@PathVariable("id") short id, webcard.model.Transactions obj) {
        obj.setId(id);
        transactionsRepository.save(obj);
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/delete/{id}")
    public String delete(@PathVariable("id") short id) {
        transactionsRepository.deleteById(id);
        return "redirect:/transactions";
    }
}
