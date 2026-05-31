import { useState } from "react";
import API from "../api/api";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {

const [email, setEmail] =
    useState("");

const [password, setPassword] =
    useState("");

const navigate =
    useNavigate();

const loginUser = async () => {

    try {

        const response =
            await API.post(
                "/api/auth/login",
                {
                    email,
                    password
                }
            );

        localStorage.setItem(
            "token",
            response.data.token
        );

        localStorage.setItem(
            "email",
            email
        );

        navigate(
            "/dashboard"
        );

    } catch (error) {

        console.log(error);

        alert(
            "Invalid Email or Password"
        );
    }
};

return (

    <div className="login-page">

        <div className="login-card">

            <div className="login-header">

                <div className="logo-circle">
                    🤖
                </div>

                <h1>
                    AI Interview Platform
                </h1>

                <p>
                    Prepare smarter.
                    Practice confidently.
                    Get AI-powered feedback.
                </p>

            </div>

            <div className="input-group-custom">

                <input
                    type="email"
                    placeholder="Enter your Email"
                    value={email}
                    onChange={(e) =>
                        setEmail(
                            e.target.value
                        )
                    }
                />

            </div>

            <div className="input-group-custom">

                <input
                    type="password"
                    placeholder="Enter your Password"
                    value={password}
                    onChange={(e) =>
                        setPassword(
                            e.target.value
                        )
                    }
                />

            </div>

            <button
                className="login-btn"
                onClick={loginUser}
            >
                Login
            </button>

            <div className="register-section">

                <span>
                    Don't have an account?
                </span>

                <a href="/register">
                    Register Here
                </a>

            </div>

        </div>

    </div>
);


}

export default Login;
