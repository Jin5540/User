package com.example.user.member.domain.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long index;

    @Column(unique = true, length = 45)
    private String userId;

    @Column(length = 64)
    private String password;

    private String nickName;

    private String name;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @PrePersist // insert시 동작 / 비영속 -> 영속
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate // update시 동작
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }
}