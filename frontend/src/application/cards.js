import axios from "axios";

const CARDS_PATH = `${process.env.REACT_APP_BACKEND_URL}/api/cards`;

export const getCards = async (user) => {
    const response = await axios.get(CARDS_PATH, {headers: {"Authorization": `Bearer ${user.token}`}});
    return response.data;
};

export const rateCard = async (user, cardId, rate) => {
    const response = await axios.put(`${CARDS_PATH}/${cardId}`,
        {rating: rate}, {headers: {"Authorization": `Bearer ${user.token}`}});
    return response.data;
};
