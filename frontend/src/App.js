import React, {useContext} from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import 'bulma/css/bulma.min.css';
import '@fortawesome/fontawesome-free/css/all.css';
import {AppContext} from "./context/app-context";
import {AppContextProvider} from "./context/app-context-provider";
import LoginPage from "./pages/login/login-page";
import ReviewPage from "./pages/review/review-page";

const AppRouting = () => {
    const {user} = useContext(AppContext);
    return (
        <BrowserRouter>
            <Routes>
                {!user && (<Route path="/" element={<LoginPage/>}/>) }
                {user && (<Route path="/" element={<ReviewPage/>}/>)}
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
