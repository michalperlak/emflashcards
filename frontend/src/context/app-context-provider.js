import React, {useState} from "react";
import {AppContext} from "./app-context";

export const AppContextProvider = (props) => {
    const [state, setState] = useState({});
    const updateState = (newState) => {
        setState({...state, ...newState});
    };
    return (
        <AppContext.Provider value={{...state, updateState}}>
            {props.children}
        </AppContext.Provider>
    );
};