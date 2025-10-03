import { defineStore } from 'pinia'

export const usePostStore = defineStore('post', {
    state: () => {
        return { posts:[] as Post[] }
    },

    actions: {
    },

    getters:{
        getPosts: (state) => {
            return state.posts;
        }
    },
})
