package com.jossegonnza.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //Getters and Setters
@Table(
        name = "tbl_posts"
)
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt; //"creado en"

    @ManyToOne(
            fetch = FetchType.LAZY
    ) //Muchos post de un sólo usuario
    @JoinColumn(name = "user_id")
    private UserEntity user; //Nombre que usamos para mappear en UserEntity

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL
    )
    private List<CommentEntity> commments = new ArrayList<>();
}
