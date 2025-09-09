package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid", nullable = false)
    private User userId;
    @Column(name = "content", length = 140)
    private String content;
    @Column(name = "reply_to")
    private Long replyTo;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Post() {
    }
}
