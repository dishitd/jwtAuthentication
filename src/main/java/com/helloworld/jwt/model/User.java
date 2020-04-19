package com.helloworld.jwt.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"USER\"")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(name="USERNAME", nullable=false)
    private String username;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    @Column(nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name="id"))
    @Column(name="role_name")
    private Set<String> userRoles;


}
