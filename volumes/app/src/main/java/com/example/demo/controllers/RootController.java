package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;

import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;

import antlr.collections.List;

@Controller
@RequestMapping("/")
public class RootController {

	@Autowired  //他のクラスを呼び出すことができる. InquiryRepositoryインスタンスをnewしなくても、repositoryのメソッドを使用できています
	InquiryRepository repository;
	
	@Autowired
	InquiryRepository2 repository2;

//　	元コード
//	@GetMapping
//	public String index() {
//		return "root/index";
//	}	
	
//	上の元コードから一覧表示を作成中
	@GetMapping
	public String inquiryList(Model model) {
		List<InquiryForm> items = InquiryForm.findAll();
		model.addAttribute("items", items);
		return "root/index";
	}
	


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
	
	@GetMapping("/form2")
	public String form2(InquiryForm2 inquiryForm2) {
		return "root/form2";
	}

	@PostMapping("/form2")
	public String form2(@Validated InquiryForm2 inquiryForm2, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form2";
		}

		// RDBと連携できることを確認しておきます。
		repository2.saveAndFlush(inquiryForm2);
		inquiryForm2.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form2";
	}
}