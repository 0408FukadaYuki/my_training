<script setup lang="ts">
const { getPost } = usePost()
const { getUserFavorite } = useFavorite();
const postStore = usePostStore();
const userStore = useUserStore();
const toast = useToast()
interface Reactive {
    active: string;
    myPosts: Post[];
    myFavorite: Post[];
    showAlertFlag: boolean;
    alertMessage: string,
    showDeleteModalFlag: boolean
}

const state = reactive<Reactive>({
    active: '0',
    myPosts: [],
    myFavorite: [],
    showAlertFlag: false,
    alertMessage: "",
    showDeleteModalFlag: false,
})
const items = [
    {
        label: '投稿',
    },
    {
        label: 'お気に入り',
    }
]

onMounted(async () => {
    try {
        state.myFavorite = await getUserFavorite(userStore.getLoginUserUuid);
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
})

const getShowPost = computed(() => {
    if (state.active === '0') {
        state.myPosts = postStore.posts.filter((post) => {
            return post.uuid === userStore.getLoginUserUuid;
        });
        return state.myPosts;
    } else {
        return state.myFavorite;
    }
})

const showToast = (title: string, description: string) => {
    toast.add({
        title: title,
        description: description,
        color: title === "error" ? "error" : "success"
    })
}

const refreshPostData = async () => {
    try {
        const posts: Post[] = await getPost(userStore.getLoginUserUuid);
        state.myFavorite = await getUserFavorite(userStore.getLoginUserUuid);
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

const toggleModal = ((value: boolean) => {
    state.showDeleteModalFlag = value;
})
</script>

<template>
    <UContainer class="h-screen flex flex-col">
        <UAlert v-if="state.showAlertFlag" color="error" class="w-1/2 mx-auto" :title=state.alertMessage />
        <DeleteModal v-if="state.showDeleteModalFlag" @showToast="showToast" @refreshPostData="refreshPostData"
            @closeDeleteModal="toggleModal"></DeleteModal>
        <div class=" w-full flex-col mt-3">
            <div class="h-16 flex justify-center items-center">
                <UAvatar class="mr-5" src="/img/exampleImage.png" size="xl" />
                <div class="text-2xl">{{ userStore.getLoginUserName }}@{{ userStore.getLoginUserId }}</div>
            </div>
            <div class="mt-3 text-xl">
                <div class="mx-auto w-4/5">{{ userStore.getLoginUserProfile }}</div>
            </div>
        </div>
        <div class="w-full mt-4 flex-col justify-center">
            <UTabs v-model="state.active" :items="items"></UTabs>
            <div>
                <Post v-for="post in getShowPost" :post="post" :key="post.postId" @refreshPostData="refreshPostData"
                    @showToast="showToast" @openDeleteModal="toggleModal"></Post>
            </div>
        </div>
    </UContainer>
</template>
