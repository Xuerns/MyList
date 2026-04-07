import { withDelay } from "../utils/delayHelper";

const API_BASE_URL = import.meta.env.VITE_API_URL;

export const authUserAPI = {
    async register(data: {username?: string, email: string, password: string}) {
        const res = await fetch(`${API_BASE_URL}/auth/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        return withDelay(res.json());
    },

    async login(data: {email: string, password: string}) {
        const res = await fetch(`${API_BASE_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        })
        return withDelay(res.json());
    }
}