package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController{
    @RequestMapping("/hello")
    public String index(){
        return "hello";
    }
    @RequestMapping("/hello2")
	public String hello2(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello2";
    }
    @RequestMapping(value = {"/hello2", "/hello2/{name}"})
    public String helloPath(@PathVariable Optional<String> name, Model model) {
    	if(name.isPresent()) {
    		model.addAttribute("name", name.get());
    	} else {
    		model.addAttribute("name", "Phoenix");
    	}
    	return "hello2";
	}
    //http://localhost:8080/calcu-convert?number1=2&number2=9
    @RequestMapping("/calcu-convert")
	public String calculate(@RequestParam(value = "number1") String number1, @RequestParam(value = "number2") String number2, Model model){
		String[] angka = {"Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas", "Belas", "Puluh", "Ratus", "Seratus"};
		int num1 = Integer.parseInt(number1);
		int num2 = Integer.parseInt(number2);
		int sum = num1 + num2;
		String x = String.valueOf(sum);
		String[] x_split = x.split("");
		int x_integer0 = Integer.parseInt(x_split[0]);
		String hasil = null;
		
		if(sum <= 11) {
		hasil = number1 + " + "+ number2 +" = "+ sum +" ("+ angka[sum - 1] + ")";
		} else if (sum < 20) {
			int x_integer1 = Integer.parseInt(x_split[1]);
			hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[x_integer1 - 1] + " " + angka[11] + ")";
		} else if (sum < 100) {
			int x_integer1 = Integer.parseInt(x_split[1]);
			if(x_integer1 == 0) {
				hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[x_integer0 - 1] + " " + angka[12] + ")";
			} else {
				hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[x_integer0 - 1] + " " + angka[12] + " " + angka[x_integer1 - 1] + ")";
			}
		} else if (sum < 1000){
			int x_integer1 = Integer.parseInt(x_split[1]);
			int x_integer2 = Integer.parseInt(x_split[2]);
			if ((x_integer1 == 0) && (x_integer2 == 0)) {
				if(x_integer0 == 1) {
					hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[14] + ")";
				} else {
					hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[x_integer0 - 1] + " " + angka[13] + ")";
				}
			}
			else if (x_integer0 == 1) {
				if(x_integer1 == 0) {
					hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[14] + " " + angka[x_integer2 - 1] + ")";
				} else {
					hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[14] + " " + angka[x_integer1 - 1] + " " + angka[12] + " " + angka[x_integer2 - 1] + ")";
				}
			}else {
				if(x_integer1 == 0) {
					hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[x_integer0 - 1] + " " + angka[13] + " " + angka[x_integer2 - 1] + ")";
				} else {
					hasil = number1 + " + " + number2 + " = " + sum + " ("+ angka[x_integer0 - 1] + " " + angka[13] + " " + angka[x_integer1 - 1] + " " + angka[12] + " " + angka[x_integer2 - 1] + ")";
				}
			}
		} else {
		}
		
		model.addAttribute("hasil", hasil);
		return "calcu-convert";
	}
}