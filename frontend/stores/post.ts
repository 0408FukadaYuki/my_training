import { defineStore } from 'pinia'

export const usePostStore = defineStore('post', {
    state: () => {
        return {
            posts: [] as Post[],
            deletePostId: null as number | null
        }
    },

    actions: {
    },

    getters: {
        getPosts: (state) => {
            return state.posts;
        },
        getDeletePostId: (state) => {
            return state.deletePostId;
        }
    },
})
