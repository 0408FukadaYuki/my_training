package com.example.demo.service;

import com.example.demo.model.request.CreatePostRequest;

public interface PostService {

    /**
     * 投稿作成関数。リプライも同じAPIで処理する
     *
     * @param createPostRequest リクエストボディで受け取る新規投稿の情報。
     *                          リプライの場合はreplyToにリプライ先PostのIDをセットし、
     *                          新規投稿の場合はnullをセットする。
     */

    public void createPost(CreatePostRequest createPostRequest);
    /**
     * 投稿削除関数
     *
     * @param id 削除対象のPostのID
     */
    public void deletePost(Long id);
}
