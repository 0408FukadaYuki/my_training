<script setup lang="ts">
const postStore = usePostStore();
const userStore = useUserStore();
interface Reactive {
    active :string;
    showData:Post[];
}

const state = reactive<Reactive>({
    active: '0',
    showData: [],
})
const items = [
    {
        label: '投稿',
    },
    {
        label: 'いいね',
    }
]

const getPost = computed(() => {
    if (state.active === '0') {
        return postStore.posts.filter((post)=>{
            return post.uuid === userStore.getLoginUserId;
        });
    } else if (state.active === '1') {
        return samplePosts;
    } 
})
const samplePosts: Post[] = [
    {
        uuid: "123e4567-e89b-12d3-a456-426614174000",
        postId: 1,
        userId: "user_001",
        userName: "TaroYamada",
        content: "今日はとても良い天気ですね！",
        replyTo: "",
        createdAt: "2025-10-06T10:16:00Z"
    },
    {
        uuid: "223e4567-e89b-12d3-a456-426614174001",
        postId: 2,
        userId: "user_002",
        userName: "HanakoSaito",
        content: "明日の予定は何ですか？",
        replyTo: "123e4567-e89b-12d3-a456-426614174000",
        createdAt: "2025-10-06T10:20:00Z"
    },
    {
        uuid: "323e4567-e89b-12d3-a456-426614174002",
        postId: 3,
        userId: "user_003",
        userName: "KenSuzuki",
        content: "新しいプロジェクトが楽しみです！",
        replyTo: "",
        createdAt: "2025-10-06T10:25:00Z"
    }
];
</script>

<template>
    <UContainer class="h-screen flex flex-col">
        <div class="bg-green-300 w-full flex-col h-32 mt-3">
            <div class="bg-green-600 flex justify-center">
                <UAvatar src="/img/exampleImage.png" size="xl" />
                <div class="text-2xl">山田太郎@yamda.tarou</div>
            </div>
            <div class="bg-blue-400 mt-5 text-xl">
                新しいことにチャレンジするのが好きです。趣味は読書と散歩、コーヒーが欠かせません。
            </div>
        </div>
        <div class="bg-red-300 w-full flex-col justify-center">
            <UTabs v-model="state.active" :items="items"></UTabs>
            <div>
                <Post v-for="post in getPost" :post="post"></Post>
            </div>
        </div>
    </UContainer>
</template>
