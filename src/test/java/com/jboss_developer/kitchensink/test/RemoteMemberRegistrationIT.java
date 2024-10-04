package com.jboss_developer.kitchensink.test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jboss_developer.kitchensink.controller.MemberRestController;
import com.jboss_developer.kitchensink.model.Member;
import com.jboss_developer.kitchensink.service.MemberRegistration;

public class RemoteMemberRegistrationIT {

    @Mock
    private MemberRegistration memberRegistration;

    @InjectMocks
    private MemberRestController memberRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllMembers() {
        List<Member> members = new ArrayList<>();
        Member member = new Member();
        member.setName("Jane Doe");
        members.add(member);
        when(memberRegistration.findAllOrderedByName()).thenReturn(members);

        ResponseEntity<List<Member>> response = memberRestController.listAllMembers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
     
        List<Member> responseBody = response.getBody();
        assertNotNull(responseBody);

        assertEquals(1, responseBody.size());
        assertEquals("Jane Doe", responseBody.get(0).getName());
    }

    @Test
    public void testGetMemberById() {
        Member member = new Member();
        member.setId("1");
        when(memberRegistration.findById("1")).thenReturn(member);

        ResponseEntity<Member> response = memberRestController.getMemberById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    
        Member responseBody = response.getBody();
        assertNotNull(responseBody); 

        assertEquals("1", responseBody.getId());
    }

    @Test
    public void testCreateMember() {
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");

        when(memberRegistration.emailAlreadyExists(any())).thenReturn(false);
        doNothing().when(memberRegistration).register(any());

        ResponseEntity<?> response = memberRestController.createMember(newMember);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(memberRegistration, times(1)).register(newMember);
    }

    @Test
    public void testCreateMemberWithExistingEmail() {
        Member newMember = new Member();
        newMember.setEmail("jane@mailinator.com");

        when(memberRegistration.emailAlreadyExists(any())).thenReturn(true);

        ResponseEntity<?> response = memberRestController.createMember(newMember);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(memberRegistration, times(0)).register(newMember);
    }
}
