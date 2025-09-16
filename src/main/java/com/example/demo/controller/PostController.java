package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.model.response.GetAllPostResponse;
import com.example.demo.service.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * 投稿作成API。リプライも同じAPIで処理する
     *
     * @param createPostRequest リクエストボディで受け取る新規投稿の情報。
     *                          リプライの場合はreplyToにリプライ先PostのIDをセットし、
     *                          新規投稿の場合はnullをセットする。
     */

    @PostMapping("/create")
    public void createPost(@RequestBody CreatePostRequest createPostRequest) {
        postService.createPost(createPostRequest);
    }


    /**
     * 投稿削除API
     *
     * @param id 削除対象のPostのID
     */
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    /**
     * 投稿取得API
     * @return　保存されているすべての投稿
     */
    @GetMapping("/all")
    public List<GetAllPostResponse> getAllPost() {
        List<GetAllPostResponse> response = new ArrayList<>();
        response = postService.findAllPost();
        return response;
    }

}
