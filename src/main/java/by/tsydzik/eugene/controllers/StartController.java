package by.tsydzik.eugene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.tsydzik.eugene.entity.User;


@Controller
public class StartController {

	@RequestMapping(value = "/")
	public String login() {
		return "redirect:login";
	}
}
