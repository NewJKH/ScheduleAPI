package org.jkh.scheduleapi.domain.member.repository;

import jakarta.validation.constraints.Email;
import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(Long id){
        return findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    Optional<Member> findByEmail(@Email String email);
    default Member findByEmailOrThrow(String email ){
        return findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }
}
