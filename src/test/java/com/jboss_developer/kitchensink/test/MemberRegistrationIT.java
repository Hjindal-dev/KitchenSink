package com.jboss_developer.kitchensink.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jboss_developer.kitchensink.data.MemberRepository;
import com.jboss_developer.kitchensink.model.Member;
import com.jboss_developer.kitchensink.service.MemberRegistration;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRegistrationIT {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRegistration memberRegistration; // Autowire MemberRegistration directly

    @BeforeEach
    public void setUp() {
        // Clear the repository before each test to avoid data leakage between tests
        memberRepository.deleteAll();
    }

    @Test
    public void testRegisterMember() {
        // Create a new member instance
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");

        // Register the new member
        memberRegistration.register(newMember);

        // Verify that the member was saved correctly
        Member fetchedMember = memberRepository.findByEmail("jane@mailinator.com").orElse(null);
        assertThat(fetchedMember).isNotNull();
        assertThat(fetchedMember.getId()).isNotNull();
        assertThat(fetchedMember.getName()).isEqualTo("Jane Doe");
        assertThat(fetchedMember.getPhoneNumber()).isEqualTo("2125551234"); // Additional check for phone number
    }
}
