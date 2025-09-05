# DB定義

### User
|No|物理名|論理名|データ型|NotNull|memo|
|:---:|:---:|:---:|:--:|:-:|:-:|
|1|id|id|INT|〇|主キー|
|2|user_id|ユーザーID|VARCHAR(15)|〇||
|3|name|名前|VARCHAR(30)|〇||
|4|mail|メールアドレス|VARCHAR(30)|〇||
|5|profile|自己紹介|VARCHAR(30)|||
|6|birth_date|生年月日|DATE|||
|7|icon_image|アイコン画像|||パスを保存してデータは別サーバーが一般的|
|8|password|パスワード||〇|SHA256でハッシュ化して保存|

### Post
|No|物理名|論理名|データ型|NotNull|memo|
|:---:|:---:|:---:|:--:|:-:|:-:|
|1|id|id|INT|3|content|投稿内容|VARCHAR(140)|〇|||〇||
|2|user_id|ユーザーID|VARCHAR(15)|〇|外部キー|
|3|content|投稿内容|VARCHAR(140)|〇||
|4|reply_to|リプライ先ID|INT||元投稿の場合はNULL|
|5|creted_at|作成時間|DATETIME|〇||

### Like
|No|物理名|論理名|データ型|NotNull|memo|
|:---:|:---:|:---:|:--:|:-:|:-:|
|1|user_id|ユーザーID|VARCHAR(15)|〇|複合キー|
|2|post_id|投稿内容|VARCHAR(140)|〇|複合キー|
|3|creted_at|作成時間|DATETIME|〇|

## 画面一覧
##### 必須
- ログイン画面（?）
- タイムライン 
- プロフィール画面
    - 自分の投稿
    - いいね欄
- 投稿作成画面

#### できたら
- 投稿詳細画面
    - リプライ


## 機能一覧
- ログイン（?）
- 新規投稿
- 投稿削除
- タイムライン
    - いいね機能
    - 昇順