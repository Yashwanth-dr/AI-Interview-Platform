import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

function Dashboard() {


const navigate = useNavigate();

const email =
    localStorage.getItem("email");

const username =
    email
        ? email.split("@")[0]
        : "User";

const logout = () => {

    localStorage.clear();

    navigate("/");
};

return (

    <div className="dashboard-container">

        <div className="dashboard-header">

            <div>

                <h1 className="main-title">
                    🤖 AI Interview Platform
                </h1>

                <h3 className="welcome-text">
                    Welcome, {username} 👋
                </h3>

                <p className="subtitle">
                    Practice interviews, improve your skills,
                    analyze resumes and track performance.
                </p>

            </div>

            <button
                className="btn btn-danger"
                onClick={logout}
            >
                Logout
            </button>

        </div>

        <div className="stats-section">

            <div className="stat-card">
                <h2>📄</h2>
                <h4>Resume Analysis</h4>
            </div>

            <div className="stat-card">
                <h2>🎯</h2>
                <h4>AI Interviews</h4>
            </div>

            <div className="stat-card">
                <h2>📊</h2>
                <h4>Performance Tracking</h4>
            </div>

        </div>

        <div className="feature-grid">

            <div className="feature-card">

                <img
                    src="https://cdn-icons-png.flaticon.com/512/337/337946.png"
                    alt="Resume"
                />

                <h3>Resume Upload</h3>

                <p>
                    Upload your resume and get
                    AI-powered suggestions.
                </p>

                <button
                    className="btn btn-primary w-100"
                    onClick={() => navigate("/resume")}
                >
                    Open
                </button>

            </div>

            <div className="feature-card">

                <img
                    src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                    alt="Interview"
                />

                <h3>AI Interview</h3>

                <p>
                    Practice technical interviews
                    with AI-generated questions.
                </p>

                <button
                    className="btn btn-success w-100"
                    onClick={() => navigate("/interview")}
                >
                    Open
                </button>

            </div>

            <div className="feature-card">

                <img
                    src="https://cdn-icons-png.flaticon.com/512/4149/4149647.png"
                    alt="Analytics"
                />

                <h3>Analytics</h3>

                <p>
                    View scores, feedback,
                    and performance reports.
                </p>

                <button
                    className="btn btn-warning w-100"
                    onClick={() => navigate("/analytics")}
                >
                    Open
                </button>

            </div>

        </div>

    </div>
);


}

export default Dashboard;
