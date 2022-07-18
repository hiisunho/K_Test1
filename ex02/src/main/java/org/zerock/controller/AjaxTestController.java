package org.zerock.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxTestController {

	@GetMapping("/GetServer")
	public String getServer() {
	    Calendar calendar=Calendar.getInstance();
	    int second=calendar.get(Calendar.SECOND);
	    return second+"";
	}
	
}
