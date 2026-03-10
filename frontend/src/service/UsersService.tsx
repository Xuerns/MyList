import axios from "axios";

const REST_API_BASE_URL = import.meta.env.API_BASE_URL;

export const listUsers = () => {
    return axios.get(REST_API_BASE_URL);
}