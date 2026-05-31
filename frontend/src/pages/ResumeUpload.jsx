import { useState } from "react";
import API from "../api/api";
import "./ResumeUpload.css";

function ResumeUpload() {


const [file, setFile] = useState(null);

const [resumeId, setResumeId] =
    useState(null);

const [analysis, setAnalysis] =
    useState("");

const uploadResume = async () => {

    try {

        const formData =
            new FormData();

        formData.append(
            "email",
            localStorage.getItem("email")
        );

        formData.append(
            "file",
            file
        );

        const token =
            localStorage.getItem("token");

        const response =
            await API.post(
                "/api/resume/upload",
                formData,
                {
                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

        setResumeId(
            response.data.id
        );

        alert(
            "Resume Uploaded Successfully ✅"
        );

    } catch (error) {

        console.log(error);

        console.log(
            error.response
        );

        alert(
            error.response?.data ||
            error.message
        );
    }
};

const analyzeResume = async () => {

    try {

        const token =
            localStorage.getItem(
                "token"
            );

        const response =
            await API.get(
                `/api/resume/analyze/${resumeId}`,
                {
                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

        console.log(
            response.data
        );

        setAnalysis(
            response.data
        );

    } catch (error) {

        console.log(error);

        alert(
            "Analysis Failed ❌"
        );
    }
};

return (

    <div className="resume-page">

        <div className="resume-card">

            <div className="resume-header">

                <div className="resume-icon">
                    📄
                </div>

                <h1>
                    AI Resume Analyzer
                </h1>

                <p>
                    Upload your resume and
                    receive AI-powered insights,
                    skill analysis, strengths,
                    weaknesses, and interview
                    recommendations.
                </p>

            </div>

            <div className="upload-section">

                <input
                    type="file"
                    className="resume-input"
                    onChange={(e) =>
                        setFile(
                            e.target.files[0]
                        )
                    }
                />

                {file && (

                    <div className="file-preview">

                        <span>
                            📎 {file.name}
                        </span>

                    </div>

                )}

            </div>

            <button
                className="upload-btn"
                onClick={uploadResume}
            >
                Upload Resume
            </button>

            {resumeId && (

                <button
                    className="analyze-btn"
                    onClick={analyzeResume}
                >
                    Analyze Resume
                </button>

            )}

            {analysis && (

                <div className="analysis-card">

                    <h2>
                        🤖 AI Analysis Report
                    </h2>

                    <div className="analysis-content">

                        <pre>
                            {analysis}
                        </pre>

                    </div>

                </div>

            )}

        </div>

    </div>
);


}

export default ResumeUpload;
