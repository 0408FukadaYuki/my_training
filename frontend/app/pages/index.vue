<script setup lang="ts">
import { useLogin } from '~/composables/useLogin'
import type { LoginResponse } from '~/composables/useLogin'

const { login } = useLogin();

const state = reactive({
    email: '',
    password: ''
})

async function submit() {
    const response: LoginResponse = await login(state.email, state.password);

    if (response.success) {
        await navigateTo('/timeLine');
    }
}
</script>


<template>
    <UContainer class="bg-gray-200 h-screen flex justify-center items-center">
        <div class="h-1/2 w-1/2 flex-col items-center justify-items-center bg-white border-4 border-green-300 ">
            <div class="mt-5 w-full text-center text-5xl h-1/5">
                <h1>Log in</h1>
            </div>
            <UForm class="space-y-8 w-3/4 mx-auto flex flex-col justify-items-center">
                <UFormField name="email">
                    <UInput v-model="state.email" placeholder="メールアドレス" class="w-full" />
                </UFormField>

                <UFormField name="password">
                    <UInput v-model="state.password" type="password" placeholder="パスワード" class="w-full" />
                </UFormField>
                <UButton type="submit" class="w-full" @click="submit">
                    <div class="mx-auto">ログイン</div>
                </UButton>
            </UForm>
        </div>
    </UContainer>

</template>
