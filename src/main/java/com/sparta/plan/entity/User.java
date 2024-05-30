package com.sparta.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    //아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    //별명
    @Column(nullable = false, unique = true)
    private String username;

    //비밀번호
    @Column(nullable = false)
    private String password;

    //권한
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}