package com.jboss_developer.kitchensink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jboss_developer.kitchensink.data.MemberRepository;
import com.jboss_developer.kitchensink.model.Member;
import com.jboss_developer.kitchensink.util.LoggerComponent;

import jakarta.validation.Valid;

@Service
public class MemberRegistration {

    @Autowired
    private LoggerComponent loggerComponent;

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAllOrderedByName() {
        return memberRepository.findAllByOrderByNameAsc();
    }

    public Member findById(String id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void register(@Valid Member member) {
        loggerComponent.logInfo("This is the newly added member: " + member);
        memberRepository.save(member);
    }

    public boolean emailAlreadyExists(String email) {
        return memberRepository.findByEmail(email) != null;
    }
}
