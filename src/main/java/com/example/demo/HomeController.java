package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessageRepository messageRepository;

    //Home Page/TL
    @RequestMapping("/")
    public String listMessages(Model model) {
        model.addAttribute("messages",messageRepository.findAll());
        return "list";
    }

    @GetMapping("/chat")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "chat";
    }

    @PostMapping("/process")
    public String processForm(@Valid Message message,
                              BindingResult result){
        if (result.hasErrors()){
            return "chat";
        }
        messageRepository.save(message);
        return "redirect:/";
    }


}