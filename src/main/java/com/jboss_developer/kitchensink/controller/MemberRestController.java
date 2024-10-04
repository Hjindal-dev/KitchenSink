package com.jboss_developer.kitchensink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jboss_developer.kitchensink.exception.ResourceNotFoundException;
import com.jboss_developer.kitchensink.model.Member;
import com.jboss_developer.kitchensink.service.MemberRegistration;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/members")
public class MemberRestController {

    @Autowired
    private MemberRegistration memberRegistration;

    @GetMapping
    public ResponseEntity<List<Member>> listAllMembers() {
        List<Member> members = memberRegistration.findAllOrderedByName();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Member member = memberRegistration.findById(id);
        if (member == null) {
            throw new ResourceNotFoundException("Member not found with ID: " + id);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createMember(@Valid @RequestBody Member member) {
        if (memberRegistration.emailAlreadyExists(member.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
        memberRegistration.register(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
}
