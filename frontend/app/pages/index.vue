<script setup lang="ts">
    async function login() {
        await navigateTo('/timeLine')
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
                    <UInput placeholder="メールアドレス" class="w-full" />
                </UFormField>

                <UFormField name="password">
                    <UInput type="password" placeholder="パスワード" class="w-full" />
                </UFormField>
                <UButton type="submit" class="w-full" @click="login">
                    <div class="mx-auto">ログイン</div>
                </UButton>
            </UForm>
        </div>
    </UContainer>

</template>
