import { create } from "zustand";

interface userStore {
    username: string,
    theme: string,
    isLogin: boolean,

    setUsername: (username: string) => void;
    setTheme: (theme: string) => void;
    setIsLogin: () => void;
}

export const useUserStore = create<userStore>()((set) => ({
    username: "",
    theme: "light",
    isLogin: false,

    setUsername: (username) => set({username: username}),
    setTheme: (theme) => set({theme: theme}),
    setIsLogin: () => set((state) => ({isLogin: !state.isLogin}))
}))