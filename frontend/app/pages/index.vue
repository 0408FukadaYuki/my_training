<script setup lang="ts">
import { useLogin } from '~/composables/useLogin'
import * as v from 'valibot'
import type { LoginResponse } from '~/composables/useLogin'

const { login } = useLogin();

const state = reactive({
    email: '',
    password: '',
    showAlertFlag: false,
    alertMessage: "",
    loadingValue: null,
    showLoadingFlag: false
})

const schema = v.object({
    email: v.pipe(v.string(), v.email('無効なメールアドレスです')),
    password: v.pipe(v.string(), v.nonEmpty('パスワードを入力してください'))
})

async function submit() {
    try {
        state.showLoadingFlag = true;
        const response: LoginResponse = await login(state.email, state.password);
        state.showLoadingFlag = false;
        if (response.success) {
            state.showAlertFlag = false;
            await navigateTo('/timeLine');
        } else {
            state.showAlertFlag = true;
            state.alertMessage = response.message;
        }

    } catch (error: any) {
        state.showLoadingFlag = false;
        //400,500番台の場合はエラーアラートを表示
        if (error.message) {
            state.showAlertFlag = true;
            state.alertMessage = error.message;
        } else {
            //ネットワークエラーのerro.vueを表示
            throw createError({ statusCode: 500, statusMessage: 'ネットワークエラーが発生しました。', fatal: true })
        }
    }
}
</script>


<template>
    <UContainer class="bg-gray-200 h-screen flex justify-center items-center flex-col">
        <div class="h-1/2 w-1/2 flex-col items-center justify-items-center bg-white border-4 border-green-300 ">
            <div class="mt-5 w-full text-center text-5xl h-1/5">
                <h1>Log in</h1>
            </div>
            <UForm :schema="schema" :state="state" class="space-y-8 w-3/4 mx-auto flex flex-col justify-items-center"
                @submit="submit">
                <UFormField name="email">
                    <UInput v-model="state.email" placeholder="メールアドレス" class="w-full" />
                </UFormField>

                <UFormField name="password">
                    <UInput v-model="state.password" type="password" placeholder="パスワード" class="w-full" />
                </UFormField>
                <UButton type="submit" class="w-full">
                    <div class="mx-auto">ログイン</div>
                </UButton>
            </UForm>
        </div>
        <UProgress v-if=state.showLoadingFlag v-model="state.loadingValue"  class="mt-5 w-1/2"/>
        <UAlert v-if=state.showAlertFlag color="error" class="mt-5 w-1/2" :title=state.alertMessage />
    </UContainer>

</template>
