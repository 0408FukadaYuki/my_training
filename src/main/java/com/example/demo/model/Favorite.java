package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "favorites")
public class Favorite {
    @EmbeddedId
    protected FavoritePK favoritePK;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid", insertable = false, updatable = false,nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id",insertable = false, updatable = false, nullable = false)
    private Post post;
    @Column(name = "created_at")
    private Date createdAt;

    public Favorite() {
    }
}
