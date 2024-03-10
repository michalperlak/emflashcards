import React, {useEffect, useState} from "react";
import { set, get } from 'idb-keyval';
import {AppContext} from "./app-context";

const getInitialState = async () => {
    console.log("Loading state");
    const state = await get('context') || {};
    console.log("Loaded state: " + state);
    return state;
};

const initialState = await getInitialState();

const saveState  = async (state) => {
    await set('context', state);
    console.log("Saving state: " + JSON.stringify(state));
} 

export const AppContextProvider = (props) => {
    const [state, setState] = useState(initialState);
    const updateState = async (newState) => {
        setState({...state, ...newState});
    };
    useEffect(() =>  {
        saveState({...state});
    }, [state]);

    return (
        <AppContext.Provider value={{...state, updateState}}>
            {props.children}
        </AppContext.Provider>
    );
};