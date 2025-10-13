package com.example.demo.service;

import java.util.List;

import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.model.response.GetAllPostResponse;

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

    /**
     * 投稿取得関数
     * @param　uuid お気に入りしている保存しているかを検索するためのユーザー情報
     * @return 保存されているすべての投稿とuuidで指定したユーザーがお気に入りしているかの情報
     */
    public List<GetAllPostResponse> findAllPost(String uuid);

}
