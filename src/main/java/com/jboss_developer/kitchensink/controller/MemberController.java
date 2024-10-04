package com.jboss_developer.kitchensink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jboss_developer.kitchensink.model.Member;
import com.jboss_developer.kitchensink.service.MemberRegistration;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Controller
public class MemberController {

    private final MemberRegistration memberRegistration;

    @Autowired
    public MemberController(MemberRegistration memberRegistration) {
        this.memberRegistration = memberRegistration;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberRegistration.findAllOrderedByName());
        return "index";
    }

    @PostMapping("/members/register")
    public String register(@Valid @ModelAttribute("newMember") Member newMember,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("members", memberRegistration.findAllOrderedByName());
            return "index"; // Return to index if validation errors occur
        }
        try {
            memberRegistration.register(newMember);
            model.addAttribute("successMessage", "Registration successful!");
            return "redirect:/";
        } catch (ConstraintViolationException e) {
            model.addAttribute("errorMessage", extractRootErrorMessage(e));
            model.addAttribute("members", memberRegistration.findAllOrderedByName());
            return "index";
        }
    }

    private String extractRootErrorMessage(Throwable throwable) {
        if (throwable == null) {
            return "Registration failed. See server log for more information.";
        }
        return throwable.getLocalizedMessage();
    }
}
