import {get, set} from 'idb-keyval';
import React, {useEffect, useState} from "react";
import {AppContext} from "./app-context";

const getInitialState = async () => {
    console.log("Loading state");
    const state = await get('context') || {};
    console.log("Loaded state: " + state);
    return state;
};

const initialState = await getInitialState();

export const AppContextProvider = (props) => {
    const [state, setState] = useState(initialState);
    const updateState = async (newState) => {
        setState({...state, ...newState});
    };
    useEffect(() => {
        const saveState = async (state) => {
            await set('context', state);
        }
        saveState({...state})
            .catch(console.error);
    }, [state]);

    return (
        <AppContext.Provider value={{...state, updateState}}>
            {props.children}
        </AppContext.Provider>
    );
};