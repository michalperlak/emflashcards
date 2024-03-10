import React, {useContext} from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import {AppContext} from "./context/app-context";
import {AppContextProvider} from "./context/app-context-provider";
import LoginPage from "./pages/login/login-page";
import ReviewPage from "./pages/review/review-page";

const AppRouting = () => {
    const {user} = useContext(AppContext);
    console.log(JSON.stringify(user));
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginPage/>}/>)
                <Route path="/review" element={<ReviewPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

const App = () => {
    return (
        <AppContextProvider>
            <AppRouting/>
        </AppContextProvider>
    );
}


export default App;
