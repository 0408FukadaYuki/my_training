<script setup lang="ts">
const { getPost } = usePost()
const postStore = usePostStore();

onMounted(async () => {
    try {
        const posts: Post[] = await getPost();
        postStore.posts = [...posts];
    } catch (error: any) {
        //ネットワークエラーのerro.vueを表示
        throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })

    }
})
</script>

<template>
    <UContainer class="h-screen flex justify-center items-center">
        <div class="h-full w-1/2 flex-col items-center justify-items-center hidden-scrollbar bg-white">
            <Post v-for="post in postStore.getPosts" :post="post"></Post>
        </div>
    </UContainer>
</template>
