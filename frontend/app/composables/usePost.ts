
export interface Post {
    uuid: string;
    postId: number,
    userId: string;
    userName: string;
    content: string;
    replyTo: string;
    createdAt: string;
    favorite: boolean;
}


export const usePost = () => {
    const config = useRuntimeConfig()
    const getPost = async (uuid: string): Promise<Post[]> => {
        try {
            const res: Post[] = await $fetch(`/post/all/${uuid}`, {
                baseURL: config.public.apiBase,
                method: 'GET',
            })
            return res;
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }

    const createPost = async (userId: string, content: string, replyTo: number | null): Promise<void> => {
        try {
            await $fetch('/post/create', {
                baseURL: config.public.apiBase,
                method: 'POST',
                body: {
                    userId: userId,
                    content: content,
                    replyTo: replyTo,
                }
            })
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }

    const deletePost = async (postId: number): Promise<void> => {
        try {
            await $fetch(`/post/delete/${postId}`, {
                baseURL: config.public.apiBase,
                method: 'DELETE',
            })
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }
    return { getPost, createPost, deletePost };
}
