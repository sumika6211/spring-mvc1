package com.example.spring_mvc1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring_mvc1.model.Item;
import com.example.spring_mvc1.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("items", itemService.findAll());
		return "index";
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("item", itemService.findOne(id));
		return "show";
	}
	
	@GetMapping("new")
	public String newItem(@ModelAttribute("item") Item item, Model model) {
		return "new";
	}
	
	@GetMapping("{id}/edit")
  public String edit(@PathVariable Long id, @ModelAttribute("item") Item item, Model model) {
    model.addAttribute("item", itemService.findOne(id));
    return "edit";
  }
	
	@PostMapping
	public String create(@ModelAttribute("item") @Validated Item item, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "new";
		}else {
			itemService.save(item);
			return "redirect:/items";
		}
	}
	
	@PostMapping("{id}/update")
	public String edit(@PathVariable Long id, @ModelAttribute("item") @Validated Item item, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("item", item);
			return "edit";
		}else {
			item.setId(id);
			itemService.update(item);
			return "redirect:/items";
		}
	}
	
	@GetMapping("{id}/delete")
	public String delete(@PathVariable Long id) {
		itemService.delete(id);
		return "redirect:/items";
	}
}
