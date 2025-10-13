package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class FavoritePK implements Serializable{
    @Column(name = "user_id")
    private String userId;
    @Column (name ="post_id")
    private Long postId;

    public FavoritePK(){}
}
