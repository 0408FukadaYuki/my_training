package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.FavoriteNotCreatedException;
import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.model.response.UserFavoriteResponse;
import com.example.demo.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void createFavorite(CreateFavoriteRequest createFavoriteRequest) {
        try {
            Favorite createFavoriteInfo = setFavoriteInfo(createFavoriteRequest);
            favoriteRepository.save(createFavoriteInfo);
        } catch (DataAccessException e) {
            throw new FavoriteNotCreatedException("お気に入りを保存できませんでした。");
        }
    }

    @Override
    public List<UserFavoriteResponse> getFavorite(String uuid){
        try {
            User findUserInfo = new User();
            findUserInfo.setUuid(uuid);
            Iterable<Favorite> favorites = favoriteRepository.findByUser(findUserInfo);
            List<UserFavoriteResponse> response = new ArrayList<>();
            favorites.forEach(favorite ->{
                UserFavoriteResponse res = new UserFavoriteResponse();
                User postUser = favorite.getPost().getUserId();
                Post postInfo = favorite.getPost();
                res.setUuid(postUser.getUuid());
                res.setPostId(postInfo.getId());
                res.setUserName(postUser.getName());
                res.setUserId(postUser.getUserId());
                res.setContent(postInfo.getContent());
                res.setReplyTo(postInfo.getReplyTo());
                res.setPostCreatedAt(postInfo.getCreatedAt());
                res.setFavoriteCreatedAt(favorite.getCreatedAt());
                response.add(res);
            });
            return response;
        } catch (DataAccessException e) {
            throw new FavoriteNotCreatedException("お気に入りを取得できませんでした。");
        }
    }

    private Favorite setFavoriteInfo(CreateFavoriteRequest createFavoriteRequest) {
        Favorite createFavoriteInfo = new Favorite();
        String uuid = createFavoriteRequest.getUuid();
        Long postId = createFavoriteRequest.getPostId();

        User user = new User();
        user.setUuid(uuid);
        createFavoriteInfo.setUser(user);

        Post post = new Post();
        post.setId(postId);
        createFavoriteInfo.setPost(post);

        FavoritePK favoritePK = new FavoritePK();
        favoritePK.setPostId(postId);
        favoritePK.setUserId(uuid);
        createFavoriteInfo.setFavoritePK(favoritePK);

        return createFavoriteInfo;
    }
}