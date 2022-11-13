import axios from "axios";

const instance = axios.create({
    baseURL: 'https://localhost:7167'
});

export { instance as axios };