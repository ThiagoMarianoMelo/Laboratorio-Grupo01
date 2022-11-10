import axios from "axios";

const instance = axios.create({
    baseURL: 'https://localhost:7176'
});

export { instance as axios };