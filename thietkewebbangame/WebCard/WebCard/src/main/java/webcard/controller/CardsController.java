package webcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import webcard.model.Cards;
import webcard.model.CardsRepository;
import webcard.service.CardsService;

@Controller
public class CardsController {

    @Autowired
    CardsRepository cardsRepository;
    @Autowired
    private CardsService cardsService;

    @GetMapping("/admin/cards")
    public String index(Model model) {
        model.addAttribute("list", cardsRepository.findAll());
        return "cards/index";
    }

    @GetMapping("/cards/add")
    public String add() {
        return "cards/add";
    }

    @PostMapping("/cards/add")
    public String add(@ModelAttribute Cards obj) {
        cardsService.updateImage(obj);
        cardsRepository.save(obj);
        return "redirect:/cards";
    }

    @GetMapping("/cards/edit/{id}")
    public String edit(@PathVariable("id") short id, Model model) {
        model.addAttribute("o", cardsRepository.findById(id).get());
        return "cards/edit";
    }

    @PostMapping("/cards/edit/{id}")
    public String edit(@PathVariable("id") short id, webcard.model.Cards obj) {
        obj.setId(id);
        cardsService.updateImage(obj);
        cardsRepository.save(obj);
        return "redirect:/cards";
    }

    @GetMapping("/cards/delete/{id}")
    public String delete(@PathVariable("id") short id) {
        cardsRepository.deleteById(id);
        return "redirect:/cards";
    }

    @GetMapping("/cards/detail/{id}")
    public String detail(@PathVariable("id") short id, Model model) {
        model.addAttribute("o", cardsRepository.findById(id).get());
        return "cards/detail";
    }
}
