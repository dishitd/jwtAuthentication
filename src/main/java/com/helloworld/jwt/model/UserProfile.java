package com.helloworld.jwt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile implements Serializable {

    @Id
    private Long userId;

    @Column(nullable = false)
    private String role;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private User user;

}
