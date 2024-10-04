package com.jboss_developer.kitchensink.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jboss_developer.kitchensink.model.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    Optional<Member> findByEmail(String email);

    List<Member> findAllByOrderByNameAsc();

}
