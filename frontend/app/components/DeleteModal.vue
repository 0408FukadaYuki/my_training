<script setup lang="ts">
const { deletePost } = usePost();
const postStore = usePostStore();

interface Emits {
    (event: "closeDeleteModal", value: boolean): void,
    (event: "refreshPostData"): void,
    (event: "showToast", title: string, description: string): void,
}
const emit = defineEmits<Emits>();


const submitDeletePostInfo = (async () => {
    try {
        await deletePost(postStore.getDeletePostId!);
        postStore.deletePostId = null;
        emit('closeDeleteModal', false)
        emit("showToast", "success", "投稿を削除しました。");
        emit("refreshPostData");
    } catch (error: any) {
        //400,500番台の場合はエラートーストを表示
        if (error.message) {
            emit('closeDeleteModal', false)
            emit("showToast", "error", error.message);
        } else {
            //ネットワークエラーのerror.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
});
</script>


<template>
    <div class="fixed inset-0 z-50 flex items-center justify-center">
        <div class="absolute inset-0 bg-black/50 bg-opacity-50"></div>
        <div
            class="flex flex-col items-center justify-around relative bg-white rounded-lg shadow-lg w-96 h-70 p-6 z-60">
            <div class="mx-auto text-2xl">
                ポストを削除しますか？
            </div>
            <UButton @click="submitDeletePostInfo" color="error">削除する</UButton>
            <UButton @click="emit('closeDeleteModal', false);" color="neutral">キャンセル</UButton>
        </div>
    </div>
</template>