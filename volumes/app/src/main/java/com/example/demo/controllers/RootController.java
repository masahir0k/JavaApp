package com.example.demo.controllers;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.InquiryForm;

import com.example.demo.repositries.InquiryRepository;

import ch.qos.logback.core.joran.spi.EventPlayer;

//import antlr.collections.List;
import java.util.List;
import java.util.Optional; 

@Controller
@RequestMapping("/")
public class RootController {

	/*
	 * 他のクラスを呼び出すことができる. 
	 * InquiryRepositoryインスタンスをnewしなくても、repositoryのメソッドを使用できる
	 *   InquiryRepository = repository; 
	 *   repository= new repository();
	 */
	@Autowired
	InquiryRepository repository;

	@GetMapping
	public String index() {
		return "root/index";
	}
	
    @GetMapping("/list")
    public String inquiryList(Model model) {
        List<InquiryForm> items = repository.findAll();
        model.addAttribute("items", items);
        return "root/list";
    }
    //inquiryListメソッドの引数にModel型の値を設定。
    //　パラメーターとしてModelインスタンスがmodel変数に渡される
    
    //構成：RootController視点 = repositoryの実態
    //右側のitemsリストを、左側でどう使うのか
    //InquiryFormテーブルからitemsリストを作り、
    //　repository.findAll(List<InquiryForm> = 全エンティティ)を入れて使う
    //　（repository = InquiryRepository newと同じなのでメソッドが使える）
    
    //model変数（インスタンス）を使って、ビューにitemリストを表示させる変数名を設定

	
	@GetMapping("/form")
	public String form(InquiryForm inquiryForm) {
		return "root/form";
	}

	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form";
		}

		// RDBと連携できることを確認しておきます。
		repository.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form";
	}
	
	/*
	 * making edit page controller
	 */
	@GetMapping("{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
//		Optional<InquiryForm> item = repository.findById(id);
		InquiryForm item = repository.findById(id);
		model.addAttribute("item", item);
		return "root/edit";
	}
	
    @PutMapping("{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute InquiryForm inquiryform) {
        inquiryform.setId(id);
        repository.saveAndFlush(inquiryform);
//        return "redirect:{id}/edit";
        return "root/{id}/edit";
    }
    
    @DeleteMapping("/list")
    public String destroy(@PathVariable Long id, @ModelAttribute InquiryForm inquiryform) {
    	InquiryForm item = repository.findById(id);
    	repository.delete(item);
        return "redirect:/list";
    }
	
	
	
}