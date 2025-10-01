
export interface Post {
    uuid: string;
    postId: number,
    userId: string;
    userName: string;
    content: string;
    replyTo: string;
    createdAt: string;
}


export const usePost = () => {
    const config = useRuntimeConfig()
    const getPost = async (): Promise<Post[]> => {
        try {
            const res: Post[] = await $fetch('/post/all', {
                baseURL: config.public.apiBase,
                method: 'GET',
            })
            return res;
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }
    return { getPost };
}
