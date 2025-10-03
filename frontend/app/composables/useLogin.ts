
export interface User {
    uuid: string;
    userId: string;
    name: string;
    mail: string;
    profile: string;
    birthDate: string;
    iconImage: string;
    password: string;
}

export interface LoginResponse {
    success: boolean,
    message: string,
    user: User,
}

export const useLogin = () => {
    const config = useRuntimeConfig()
    const login = async (email: string, password: string): Promise<LoginResponse> => {
        try {
            const hashedPassword = await hashStringSHA256(password)
            const res: LoginResponse = await $fetch('/user/login', {
                baseURL: config.public.apiBase,
                method: 'POST',
                body: {
                    mail: email,
                    password: hashedPassword,
                },
            })
            return res;
        } catch (error: any) {
            console.log(error);
            throw new Error(error.data?.errorMessage);
        }
    }

    return { login };
}

async function hashStringSHA256(str: string) {
    const textEncoder = new TextEncoder();
    const data = textEncoder.encode(str);
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const hashedPassword = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
    return hashedPassword;
}
