package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.FavoriteNotCreatedException;
import com.example.demo.exception.FavoriteNotDeletedException;
import com.example.demo.exception.FavoriteNotGetException;
import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.model.request.DeleteFavoriteRequest;
import com.example.demo.model.request.FavoriteBaseRequest;
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
    public List<UserFavoriteResponse> getFavorite(String uuid) {
        try {
            User findUserInfo = new User();
            findUserInfo.setUuid(uuid);
            List<Favorite> favorites = favoriteRepository.findByUserOrderByCreatedAtDesc(findUserInfo);
            List<UserFavoriteResponse> response = new ArrayList<>();
            favorites.forEach(favorite -> {
                UserFavoriteResponse res = new UserFavoriteResponse();
                User postUser = favorite.getPost().getUserId();
                Post postInfo = favorite.getPost();
                res.setUuid(postUser.getUuid());
                res.setPostId(postInfo.getId());
                res.setUserName(postUser.getName());
                res.setUserId(postUser.getUserId());
                res.setContent(postInfo.getContent());
                res.setReplyTo(postInfo.getReplyTo());
                res.setCreatedAt(postInfo.getCreatedAt());
                res.setFavoriteCreatedAt(null);
                response.add(res);
            });
            return response;
        } catch (DataAccessException e) {
            throw new FavoriteNotGetException("お気に入りを取得できませんでした。");
        }
    }

    @Override
    public void deleteFavorite(DeleteFavoriteRequest deleteFavoriteRequest) {
        try {
            Favorite deleteFavoriteInfo = setFavoriteInfo(deleteFavoriteRequest);
            favoriteRepository.delete(deleteFavoriteInfo);
        } catch (DataAccessException e) {
            throw new FavoriteNotDeletedException("お気に入りを削除できませんでした。");
        }
    }

    private Favorite setFavoriteInfo(FavoriteBaseRequest favoriteRequest) {
        Favorite favoriteInfo = new Favorite();
        String uuid = favoriteRequest.getUuid();
        Long postId = favoriteRequest.getPostId();

        User user = new User();
        user.setUuid(uuid);
        favoriteInfo.setUser(user);

        Post post = new Post();
        post.setId(postId);
        favoriteInfo.setPost(post);

        FavoritePK favoritePK = new FavoritePK();
        favoritePK.setPostId(postId);
        favoritePK.setUserId(uuid);
        favoriteInfo.setFavoritePK(favoritePK);

        return favoriteInfo;
    }
}
