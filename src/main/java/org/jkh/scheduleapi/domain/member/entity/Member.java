package org.jkh.scheduleapi.domain.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jkh.scheduleapi.common.entity.BaseDate;
import org.jkh.scheduleapi.domain.comment.entity.Comment;

import java.util.ArrayList;
import java.util.List;

@Table(name = "members")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Email
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Member(String memberName, String email, String password) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
