package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Post;
import com.example.demo.model.PostWithFavorite;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT new com.example.demo.model.PostWithFavorite(p, " +
            "CASE WHEN f.user.id IS NOT NULL THEN true ELSE false END AS favorite) " +
            "FROM Post p LEFT JOIN Favorite f ON p.id = f.post.id AND f.user.id = :userId " +
            "ORDER BY p.createdAt DESC")
    List<PostWithFavorite> findAllPost(@Param("userId") String userId);

}
