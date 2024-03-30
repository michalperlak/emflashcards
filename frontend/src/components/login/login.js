import React, {useContext, useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {AppContext} from "../../context/app-context";
import './login.css';

const Login = () => {
    const {updateState} = useContext(AppContext);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();
    const handleLogin = () => {
        setIsLoggedIn(true);
        const newUser = {id: 'abc', username: 'Test', token: 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWNoYcWCIiwiZXhwIjo0ODY1MzM3NDE5fQ.WYRluJnx579C3C6P-Gb3yXakDTNCeYvVgWWFoiQhE4U'};
        updateState({user: newUser})
        navigate("/review");
    };

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