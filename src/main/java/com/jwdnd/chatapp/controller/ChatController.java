package com.jwdnd.chatapp.controller;

import com.jwdnd.chatapp.model.ChatForm;
import com.jwdnd.chatapp.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {this.messageService = messageService;}

    @GetMapping
    public String getChatPage(ChatForm chatForm, Model model){
        model.addAttribute("chatMessages",this.messageService.getChatMessages());
        return "chat";
    }

    @PostMapping
    public String postChatMessage(ChatForm chatForm, Model model){
        this.messageService.addMessage(chatForm);
        chatForm.setMessageText("");
        model.addAttribute("chatMessages",this.messageService.getChatMessages());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessagetTypes(){return new String[]{"Say","Shout","Whisper"};}
}
