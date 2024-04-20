package com.jossegonnza.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //Getters and Setters
@Table(
        name = "tbl_comments"
)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT") //Cambia el tipo de dato del contenido a Text, más grande que Strind
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
