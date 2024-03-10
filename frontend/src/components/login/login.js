import React, {useContext, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {AppContext} from "../../context/app-context";
import './login.css';

const Login = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const {updateState} = useContext(AppContext);
    const navigate = useNavigate();
    const handleLogin = () => {
        setIsLoggedIn(true);
        const user = {id: 'abc', username: 'Test', token: '123abc'};
        updateState({user})
        navigate("/review");
    };
    if (isLoggedIn) {
        navigate("/review");
    }

    return (
        <div className="login">
            <h1>Log In</h1>
            <form>
                <div>
                    <label title="username">Username</label>
                    <input type="text"/>
                </div>
                <div>
                    <label title="password">Password</label>
                    <input type="password"/>
                </div>
                <button onClick={handleLogin}>Log In</button>
            </form>
        </div>
    );
}


export default Login;