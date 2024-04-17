package com.jossegonnza.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //Getters and Setters
@Table(
        name = "tbl_users"
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String username;
    private String password;

    @OneToMany (
            mappedBy = "user",
            cascade = CascadeType.ALL
    )//Relación one user to many posts
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany (
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<CommentEntity> comments = new ArrayList<>();
}
