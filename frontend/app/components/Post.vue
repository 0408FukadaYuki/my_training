<script setup lang="ts">

interface Props {
    post: Post,
}

const props = defineProps<Props>();

</script>

<template>
    <UCard class="border-1 rounded-none mt-0.5 w-full">
        <template #header>
            <div class="h-8 flex">
                <UAvatar src="/img/exampleImage.png" size="sm" />
                <div class="ml-1">{{ post.userName }}</div>
                <div class="ml-1 text-gray-400">@{{ post.userId }}</div>
            </div>
        </template>

        <div class="h-16 ">
            {{ post.content }}
        </div>

        <template #footer>
            <div class="h-8 flex ">
                <UButton>リプライ</UButton>
                <UButton>お気に入り</UButton>
            </div>
        </template>
    </UCard>
</template>
