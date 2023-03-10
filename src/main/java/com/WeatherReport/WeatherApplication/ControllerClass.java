package com.WeatherReport.WeatherApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerClass {

	@Autowired
	ExternalApiCall ext;
	
	@GetMapping(value="/home")
	public String home1() {
		return "Home";
	}
	
	@PostMapping(value="/home")
	public String home(@RequestParam String city,Model mod) {
		
		Weather wht=ext.callFunction(city);
		
		if(wht==null) {
		mod.addAttribute("msg","Please Enter Valid Name");
		}
		else {
			city=" ("+city+")";
			wht.setTimezone(wht.getTimezone()+city);
			mod.addAttribute("whtr", wht);
		}
		
		return "Home";
	}
	
	@ResponseBody
	@GetMapping(value="weather")
	public String whtr() {
	
		Weather wht=ext.callFunction("Hyderabad");
		String str="asdf";
		
		if(wht!=null) {
		System.out.println(wht.toString());
		str=wht.toString();
		}
		return  str;
	}
}
