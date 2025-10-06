import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            LoginUser: {
                uuid: '',
                userId: '',
                name: '',
                mail: '',
                profile: '',
                birthDate: '',
                iconImage: '',
                password: '',
            } as User
        }
    },

    actions: {
        setLoginUserInfo(user: User) {
            this.LoginUser.uuid = user.uuid;
            this.LoginUser.userId = user.userId;
            this.LoginUser.name = user.name;
            this.LoginUser.mail = user.mail;
            this.LoginUser.profile = user.profile;
            this.LoginUser.birthDate = user.birthDate;
            this.LoginUser.iconImage = user.iconImage;
            this.LoginUser.password = user.password;
        }
    },

    getters: {
        getLoginUserUuid: (state) => {
            return state.LoginUser.uuid;
        },
        getLoginUserName: (state) => {
            return state.LoginUser.name;
        },
        getLoginUserId: (state) => {
            return state.LoginUser.userId;
        },
        getLoginUserProfile:(state)=>{
            return state.LoginUser.profile;
        }
    },
})
