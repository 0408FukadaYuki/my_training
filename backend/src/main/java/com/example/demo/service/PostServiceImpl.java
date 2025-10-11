package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.PostNotCreatedException;
import com.example.demo.exception.PostNotDeletedException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.PostWithFavorite;
import com.example.demo.model.User;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.model.response.GetAllPostResponse;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void createPost(CreatePostRequest createPostRequest) {
        try {
            Post createPostInfo = new Post();
            User user = new User();
            user.setUuid(createPostRequest.getUserId());
            createPostInfo.setUserId(user);
            createPostInfo.setReplyTo(createPostRequest.getReplyTo());
            createPostInfo.setContent(createPostRequest.getContent());
            postRepository.save(createPostInfo);
        } catch (DataAccessException e) {
            throw new PostNotCreatedException("投稿を作成できませんでした。");
        }
    }

    @Transactional
    @Override
    public void deletePost(Long id) {
        try {
            Post deletePost = postRepository.findById(id)
                    .orElseThrow(() -> new PostNotDeletedException("指定された投稿が見つかりませんでした。"));

            favoriteRepository.deleteByPost(deletePost);

            postRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new PostNotDeletedException("投稿を削除できませんでした。");
        }
    }

    @Override
    public List<GetAllPostResponse> findAllPost(String uuid) {
        try {
            List<PostWithFavorite> PostWithFavorite = postRepository.findAllPost(uuid);
            List<GetAllPostResponse> response = new ArrayList<>();
            PostWithFavorite.forEach(pf -> {
                GetAllPostResponse res = new GetAllPostResponse();
                res.setUuid(pf.getPost().getUserId().getUuid());
                res.setPostId(pf.getPost().getId());
                res.setUserId(pf.getPost().getUserId().getUserId());
                res.setUserName(pf.getPost().getUserId().getName());
                res.setContent(pf.getPost().getContent());
                res.setReplyTo(pf.getPost().getReplyTo());
                res.setCreatedAt(pf.getPost().getCreatedAt());
                res.setFavorite(pf.isFavorite());
                response.add(res);
            });
            return response;
        } catch (Exception e) {
            throw new PostNotFoundException("投稿を取得できませんでした。");
        }
    }
}
