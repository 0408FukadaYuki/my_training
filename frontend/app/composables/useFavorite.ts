export const useFavorite = () => {
    const config = useRuntimeConfig()
    const getUserFavorite = async (uuid:string): Promise<Post[]> => {
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

    return { getUserFavorite };
}
