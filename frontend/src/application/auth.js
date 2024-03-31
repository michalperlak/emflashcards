import axios from "axios";

const AUTH_PATH = `${process.env.REACT_APP_BACKEND_URL}/api/auth`;

export const logIn = async (username, password) => {
    alert("Performing login")

    try {
        const response = await axios.post(`${AUTH_PATH}/login`, {username, password});
        alert(response);
        return response.data;
    } catch (e) {
        console.error(e);
    }

};