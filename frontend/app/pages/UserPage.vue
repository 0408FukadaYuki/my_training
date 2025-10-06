<script setup lang="ts">
const { getUserFavorite } = useFavorite();
const postStore = usePostStore();
const userStore = useUserStore();
interface Reactive {
    active: string;
    myPosts: Post[];
    myFavorite: Post[];
}

const state = reactive<Reactive>({
    active: '0',
    myPosts: [],
    myFavorite: [],
})
const items = [
    {
        label: '投稿',
    },
    {
        label: 'いいね',
    }
]

onMounted(async () => {
    try {
        state.myPosts = postStore.posts.filter((post) => {
            return post.uuid === userStore.getLoginUserId;
        });
        state.myFavorite = await getUserFavorite(userStore.getLoginUserId);
    } catch (error: any) {
        //400,500番台の場合はエラーアラートを表示
        if (error.message) {

        } else {
            //ネットワークエラーのerror.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
})

const getPost = computed(() => {
    if (state.active === '0') {
        console.log('MyPosts' + state.myPosts)
        return state.myPosts;
    } else {
        console.log('MyFavorite' + state.myFavorite)
        return state.myFavorite;
    }
})
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
