<script setup lang="ts">
import { usePost } from '~/composables/usePost';
const { createPost } = usePost();
const userStore = useUserStore();

interface Emits {
    (event: "refreshPostData"): void,
    (event: "showErrorToast", title: string, description: string): void,
}

const emit = defineEmits<Emits>();

const state = reactive({
    content: '',
})
async function submit() {
    try {
        await createPost(userStore.getLoginUserId, state.content, null);
        state.content = '';
        emit("refreshPostData");
    } catch (error: any) {
        //400,500番台の場合はエラートーストを表示
        if (error.message) {
            emit("showErrorToast", "error", "投稿を作成できませんでした。");
        } else {
            //ネットワークエラーのerror.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
}

const goToProfile = async () => {
    await navigateTo('/UserPage')
}

const disableFlag = computed(() => !state.content);

</script>

<template>
    <UContainer class="flex flex-col border-1 rounded-none mt-0.5 w-full h-32">
        <div class="flex mt-2">
            <div class="cursor-pointer" @click="goToProfile">
                <UAvatar class="mr-2 " src="/img/exampleImage.png" size="sm" />
            </div>

            <textarea v-model=state.content minlength="1" maxlength="140"
                class="resize-none h-20 w-full focus:outline-none" placeholder="いまなにしてる？"></textarea>
        </div>
        <div class="mt-1 flex flex-row-reverse">
            <UButton @click="submit" :disabled=disableFlag>ポストする</UButton>
        </div>
    </UContainer>
</template>
