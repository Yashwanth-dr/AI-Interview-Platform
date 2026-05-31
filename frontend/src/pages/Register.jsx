import { useState } from "react";
import API from "../api/api";
import { useNavigate } from "react-router-dom";

function Register() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const registerUser = async () => {

        try {

            await API.post(
                "/api/auth/register",
                {
                    name,
                    email,
                    password
                }
            );

            alert("Registration Successful");

            navigate("/");

        } catch (error) {

            console.log(error);

            alert("Registration Failed");
        }
    };

    return (

        <div className="container mt-5">

            <h2>Register</h2>

            <input
                className="form-control mb-3"
                placeholder="Name"
                value={name}
                onChange={(e) =>
                    setName(e.target.value)
                }
            />

            <input
                className="form-control mb-3"
                placeholder="Email"
                value={email}
                onChange={(e) =>
                    setEmail(e.target.value)
                }
            />

            <input
                type="password"
                className="form-control mb-3"
                placeholder="Password"
                value={password}
                onChange={(e) =>
                    setPassword(e.target.value)
                }
            />

            <button
                className="btn btn-success"
                onClick={registerUser}
            >
                Register
            </button>

        </div>
    );
}

export default Register;