import { useState } from "react";
import API from "../api/api";
import "./Interview.css";

function Interview() {

const [domain, setDomain] =
    useState("Java");

const [questions, setQuestions] =
    useState([]);

const [currentQuestion,
    setCurrentQuestion] =
    useState(0);

const [answer, setAnswer] =
    useState("");

const [evaluation, setEvaluation] =
    useState("");

const generateQuestions =
    async () => {

        try {

            const token =
                localStorage.getItem(
                    "token"
                );

            const response =
                await API.get(
                    `/api/questions/${domain}`,
                    {
                        headers: {
                            Authorization:
                                `Bearer ${token}`
                        }
                    }
                );

            const questionList =
                response.data
                    .split("\n")
                    .filter(
                        q => q.trim() !== ""
                    );

            setQuestions(
                questionList
            );

            setCurrentQuestion(0);

            setAnswer("");

            setEvaluation("");

        } catch (error) {

            console.log(error);

            alert(
                "Question Generation Failed"
            );
        }
    };

const evaluateAnswer =
    async () => {

        try {

            const token =
                localStorage.getItem(
                    "token"
                );

            const response =
                await API.post(
                    "/api/evaluation/answer",
                    {
                        email:
                            localStorage.getItem(
                                "email"
                            ),

                        domain:
                            domain,

                        question:
                            questions[
                                currentQuestion
                            ],

                        answer:
                            answer
                    },
                    {
                        headers: {
                            Authorization:
                                `Bearer ${token}`
                        }
                    }
                );

            setEvaluation(
                response.data
            );

        } catch (error) {

            console.log(error);

            alert(
                "Evaluation Failed"
            );
        }
    };

const nextQuestion = () => {

    setCurrentQuestion(
        currentQuestion + 1
    );

    setAnswer("");

    setEvaluation("");
};

return (

    <div className="interview-page">

        <div className="interview-container">

            <div className="interview-header">

                <h1>
                    🎤 AI Mock Interview
                </h1>

                <p>
                    Practice technical interviews
                    and receive instant AI feedback.
                </p>

            </div>

            <div className="domain-card">

                <label>
                    Select Interview Domain
                </label>

                <select
                    className="domain-select"
                    value={domain}
                    onChange={(e) =>
                        setDomain(
                            e.target.value
                        )
                    }
                >

                    <option>
                        Java
                    </option>

                    <option>
                        Spring Boot
                    </option>

                    <option>
                        React
                    </option>

                    <option>
                        DBMS
                    </option>

                    <option>
                        Operating System
                    </option>

                </select>

                <button
                    className="generate-btn"
                    onClick={
                        generateQuestions
                    }
                >
                    Generate Questions
                </button>

            </div>

            {questions.length > 0 && (

                <div className="question-card">

                    <div
                        className="question-number"
                    >

                        Question
                        {" "}
                        {currentQuestion + 1}
                        {" / "}
                        {questions.length}

                    </div>

                    <h3>

                        {
                            questions[
                                currentQuestion
                            ]
                        }

                    </h3>

                    <textarea
                        className="answer-box"
                        rows="8"
                        placeholder="Type your answer here..."
                        value={answer}
                        onChange={(e) =>
                            setAnswer(
                                e.target.value
                            )
                        }
                    />

                    <button
                        className="evaluate-btn"
                        onClick={
                            evaluateAnswer
                        }
                    >
                        Evaluate Answer
                    </button>

                </div>

            )}

            {evaluation && (

                <div
                    className="feedback-card"
                >

                    <h2>
                        🤖 AI Feedback
                    </h2>

                    <pre>
                        {evaluation}
                    </pre>

                    {

                        currentQuestion <
                        questions.length - 1 && (

                            <button
                                className="next-btn"
                                onClick={
                                    nextQuestion
                                }
                            >
                                Next Question →
                            </button>
                        )
                    }

                    {

                        currentQuestion ===
                        questions.length - 1 && (

                            <div
                                className="completion-card"
                            >

                                🎉 Interview Completed Successfully!

                            </div>
                        )
                    }

                </div>

            )}

        </div>

    </div>
);


}

export default Interview;
