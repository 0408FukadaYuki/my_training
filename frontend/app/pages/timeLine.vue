<script setup lang="ts">
const { getPost } = usePost()
const postStore = usePostStore();

const state = reactive({
    showAlertFlag: false,
    alertMessage: "",
})

onMounted(async () => {
    try {
        const posts: Post[] = await getPost();
        postStore.posts = [...posts];
    } catch (error: any) {
        //400,500番台の場合はエラーアラートを表示
        if (error.message) {
            state.showAlertFlag = true;
            state.alertMessage = error.message;
        } else {
            //ネットワークエラーのerro.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
})
</script>

<template>
    <UContainer class="h-screen flex-col flex justify-center items-center">
        <UAlert  v-if="state.showAlertFlag" color="error" class="w-1/2" :title=state.alertMessage />
        <div class="h-full w-1/2 flex-col items-center justify-items-center hidden-scrollbar bg-white">
            <Post v-for="post in postStore.getPosts" :post="post"></Post>
        </div>
    </UContainer>
</template>
