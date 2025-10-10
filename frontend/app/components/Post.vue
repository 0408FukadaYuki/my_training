<script setup lang="ts">
import 'bootstrap-icons/font/bootstrap-icons.css';
const { createFavorite, deleteFavorite } = useFavorite();
const postStore = usePostStore();
const userStore = useUserStore();

interface Emits {
    (event: "openDeleteModal", value: boolean): void,
    (event: "refreshPostData"): void,
    (event: "showToast", title: string, description: string): void,
}

const emit = defineEmits<Emits>();

interface Props {
    post: Post,
}
const props = defineProps<Props>();

const createdDate = computed(() => {
    const date = new Date(props.post.createdAt);
    return date.toLocaleString();
})

const showPostDeleteIconFlag = computed(() => {
    return props.post.uuid === userStore.getLoginUserUuid;
})

const submitFavoriteInfo = (async () => {
    try {
        if (props.post.favorite) {
            await deleteFavorite(userStore.getLoginUserUuid, props.post.postId);
            emit("showToast", "success", "お気に入りを削除しました。");
            emit("refreshPostData");
        } else {
            await createFavorite(userStore.getLoginUserUuid, props.post.postId);
            emit("showToast", "success", "お気に入りを保存しました。");
            emit("refreshPostData");
        }
    } catch (error: any) {
        //400,500番台の場合はエラートーストを表示
        if (error.message) {
            emit("showToast", "error", error.message);
        } else {
            //ネットワークエラーのerror.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
});

const openDeleteModal = (() => {
    postStore.deletePostId = props.post.postId;
    emit("openDeleteModal", true);
})

</script>

<template>
    <UCard class="border-1 rounded-none mt-0.5 w-full">
        <template #header>
            <div class="h-8 flex">
                <UAvatar src="/img/exampleImage.png" size="sm" />
                <div class="ml-1">{{ post.userName }}</div>
                <div class="ml-1 text-gray-400">@{{ post.userId }}</div>
                <div class="ml-5">{{ createdDate }}</div>
            </div>
        </template>

        <div class="h-16 ">
            {{ post.content }}
        </div>

        <template #footer>
            <div class="h-8 flex justify-center">
                <div class="mx-auto cursor-pointer">
                    <i class="bi bi-reply hover:text-blue-400"></i>
                </div>
                <div @click="submitFavoriteInfo" class="mx-auto cursor-pointer">
                    <i v-if="props.post.favorite" class="bi bi-star-fill text-amber-400"></i>
                    <i v-else class="bi bi-star hover:text-amber-400"></i>
                </div>
                <div @click="openDeleteModal" class="mx-auto cursor-pointer">
                    <i v-if="showPostDeleteIconFlag" class="bi bi-trash hover:text-red-500"></i>
                </div>
            </div>
        </template>
    </UCard>
</template>
