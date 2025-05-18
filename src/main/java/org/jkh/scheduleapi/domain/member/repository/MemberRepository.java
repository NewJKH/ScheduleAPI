package org.jkh.scheduleapi.domain.member.repository;

import jakarta.validation.constraints.Email;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(Long id){
        return findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," 사용자를 찾지 못했습니다. "));
    }

    Optional<Member> findByEmail(@Email String email);
    default Member findByEmailOrThrow(String email ){
        return findByEmail(email)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," 사용자를 찾지 못했습니다. "));
    }
}
