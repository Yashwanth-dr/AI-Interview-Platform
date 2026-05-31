import { useEffect, useState } from "react";
import API from "../api/api";
import "./Analytics.css";

function Analytics() {

const [history, setHistory] =
    useState([]);

const [total, setTotal] =
    useState(0);

const [average, setAverage] =
    useState(0);

useEffect(() => {

    loadAnalytics();

}, []);

const loadAnalytics =
    async () => {

        try {

            const email =
                localStorage.getItem(
                    "email"
                );

            const token =
                localStorage.getItem(
                    "token"
                );

            const historyResponse =
                await API.get(
                    `/api/analytics/history?email=${email}`,
                    {
                        headers: {
                            Authorization:
                                `Bearer ${token}`
                        }
                    }
                );

            const totalResponse =
                await API.get(
                    `/api/analytics/total?email=${email}`,
                    {
                        headers: {
                            Authorization:
                                `Bearer ${token}`
                        }
                    }
                );

            const averageResponse =
                await API.get(
                    `/api/analytics/average?email=${email}`,
                    {
                        headers: {
                            Authorization:
                                `Bearer ${token}`
                        }
                    }
                );

            setHistory(
                historyResponse.data
            );

            setTotal(
                totalResponse.data
            );

            setAverage(
                averageResponse.data
            );

        } catch (error) {

            console.log(error);

            alert(
                "Failed to Load Analytics"
            );
        }
    };

return (

    <div className="analytics-page">

        <div className="analytics-container">

            <div className="analytics-header">

                <h1>
                    📊 Performance Analytics
                </h1>

                <p>
                    Track your interview performance,
                    monitor progress and improve your
                    technical skills with AI insights.
                </p>

            </div>

            <div className="stats-grid">

                <div className="stat-card">

                    <div className="stat-icon">
                        📝
                    </div>

                    <h3>
                        Total Answers
                    </h3>

                    <h1>
                        {total}
                    </h1>

                </div>

                <div className="stat-card">

                    <div className="stat-icon">
                        🎯
                    </div>

                    <h3>
                        Average Score
                    </h3>

                    <h1>
                        {average.toFixed(2)}
                    </h1>

                </div>

            </div>

            <div className="history-card">

                <h2>
                    📚 Interview History
                </h2>

                {

                    history.length === 0 ? (

                        <div className="empty-state">

                            No interview records found.

                        </div>

                    ) : (

                        <table
                            className="analytics-table"
                        >

                            <thead>

                            <tr>

                                <th>
                                    Domain
                                </th>

                                <th>
                                    Question
                                </th>

                                <th>
                                    Score
                                </th>

                            </tr>

                            </thead>

                            <tbody>

                            {

                                history.map(
                                    (item) => (

                                        <tr
                                            key={item.id}
                                        >

                                            <td>
                                                {
                                                    item.domain
                                                }
                                            </td>

                                            <td>
                                                {
                                                    item.question
                                                }
                                            </td>

                                            <td>

                                                <span
                                                    className={

                                                        item.score >= 80

                                                            ? "score good"

                                                            : item.score >= 50

                                                                ? "score average"

                                                                : "score poor"
                                                    }
                                                >

                                                    {
                                                        item.score
                                                    }

                                                </span>

                                            </td>

                                        </tr>
                                    )
                                )
                            }

                            </tbody>

                        </table>

                    )
                }

            </div>

        </div>

    </div>
);


}

export default Analytics;
