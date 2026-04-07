import { withDelay } from "../utils/delayHelper";

const API_BASE_URL = import.meta.env.VITE_API_URL;

const getAuthHeader = (): Record<string, string> => {
    const token = localStorage.getItem("token");
    return token ? {Authorization: `Bearer ${token}`} : {};
}

export const sectionListAPI = {
    async createSection(title: string, imageFile?: File) {

        const formData = new FormData();
        formData.append("title", title);
    
        if (imageFile) {
            formData.append("image", imageFile);
        }

        const res = await fetch(`${API_BASE_URL}/sections`, {
            method: 'POST',
            headers: { ...getAuthHeader()},
            body: formData
        })

        if (!res.ok) {
            const errorText = await res.text();
            throw new Error(errorText || "Failed load section");
        }

        return withDelay(res.json());
    }
}