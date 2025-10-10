<script setup lang="ts">
const { getPost } = usePost()
const postStore = usePostStore();
const userStore = useUserStore();
const toast = useToast()

const state = reactive({
    showAlertFlag: false,
    showDeleteModalFlag: false,
    alertMessage: "",
})



onMounted(async () => {
    try {
        const posts: Post[] = await getPost(userStore.getLoginUserUuid);
        postStore.posts = [...posts];
    } catch (error: any) {
        //400,500番台の場合はエラーアラートを表示
        if (error.message) {

        } else {
            //ネットワークエラーのerror.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
})

const showToast = (title: string, description: string) => {
    toast.add({
        title: title,
        description: description,
        color: title === "error" ? "error" : "success"
    })
}

const toggleModal = ((value: boolean) => {
    state.showDeleteModalFlag = value;
})

const refreshPostData = async () => {
    try {
        const posts: Post[] = await getPost(userStore.getLoginUserUuid);
        postStore.posts = [...posts];
    } catch (error: any) {
        //400,500番台の場合はエラーアラートを表示
        if (error.message) {
            state.showAlertFlag = true;
            state.alertMessage = error.message;
        } else {
            //ネットワークエラーのerror.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
}
</script>

<template>
    <UContainer class="h-screen flex-col flex justify-center items-center">
        <UAlert v-if="state.showAlertFlag" color="error" class="w-1/2" :title=state.alertMessage />
        <DeleteModal v-if="state.showDeleteModalFlag" @showToast="showToast" @refreshPostData="refreshPostData"
            @closeDeleteModal="toggleModal"></DeleteModal>
        <div class="h-full w-1/2 flex-col items-center justify-items-center hidden-scrollbar bg-white">
            <CreatePost @refreshPostData="refreshPostData" @showToast="showToast"></CreatePost>
            <Post v-for="post in postStore.getPosts" :post="post" :key="post.postId" @refreshPostData="refreshPostData"
                @showToast="showToast" @openDeleteModal="toggleModal"></Post>
        </div>
    </UContainer>
</template>