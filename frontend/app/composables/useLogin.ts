import { error } from "#build/ui";

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
    User: User,
}

export const useLogin = () => {
    const login = async (email: string, password: string): Promise<LoginResponse> => {
        try {
            const res: LoginResponse = await $fetch('http://localhost:8080/user/login', {
                method: 'POST',
                body: {
                    mail: email,
                    password: password,
                },
            })
            return res;
        } catch (error) {
            console.log(error);
            throw error;
        }
    }

    return { login };
}
