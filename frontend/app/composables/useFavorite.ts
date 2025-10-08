export const useFavorite = () => {
    const config = useRuntimeConfig()
    const getUserFavorite = async (uuid: string): Promise<Post[]> => {
        try {
            const res: Post[] = await $fetch(`/favorite/get/${uuid}`, {
                baseURL: config.public.apiBase,
                method: 'GET',
            })
            return res;
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }

    const createFavorite = async (uuid: string, postId: number): Promise<void> => {
        try {
            await $fetch(`/favorite/create`, {
                baseURL: config.public.apiBase,
                method: 'POST',
                body: {
                    uuid: uuid,
                    postId: postId
                }
            })
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }

    const deleteFavorite = async (uuid: string, postId: number): Promise<void> => {
        try {
            await $fetch(`/favorite/delete`, {
                baseURL: config.public.apiBase,
                method: 'DELETE',
                body: {
                    uuid: uuid,
                    postId: postId
                }
            })
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }

    return { getUserFavorite, createFavorite, deleteFavorite };
}
