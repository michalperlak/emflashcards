import React, {useContext, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {logIn} from "../../application/auth";
import {AppContext} from "../../context/app-context";
import './login.css';

const Login = () => {
    const {updateState} = useContext(AppContext);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = () => {
        logIn(username, password)
            .then(result => {
                updateState({
                    user: {
                        username: username,
                        token: result.value
                    }
                });
                navigate("/review");
            });
    };

    return (
        <div className="login">
            <h1 className="subtitle is-3 login-text">Sign In</h1>
            <div className="field login-input mt-10">
                <p className="control has-icons-left has-icons-right">
                    <input className="input" type="text" placeholder="Username" value={username}
                           onInput={event => setUsername(event.target.value)}/>
                    <span className="icon is-small is-left">
                            <i className="fas fa-user"></i>
                        </span>
                    <span className="icon is-small is-right">
                            <i className="fas fa-check"></i>
                        </span>
                </p>
            </div>
            <div className="field login-input">
                <p className="control has-icons-left">
                    <input className="input" type="password" placeholder="Password" value={password}
                           onInput={event => setPassword(event.target.value)}/>
                    <span className="icon is-small is-left">
                            <i className="fas fa-lock"></i>
                        </span>
                </p>
            </div>
            <div className="field">
                <p className="control">
                    <button className="button is-success w-95 login-btn" onClick={handleLogin}>
                        Login
                    </button>
                </p>
            </div>
        </div>
    );
}


export default Login;