import {
    Routes,
    Route
} from "react-router-dom";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import ResumeUpload from "./pages/ResumeUpload";
import Interview from "./pages/Interview";
import Analytics from "./pages/Analytics";


function App() {

    return (

        <Routes>
          <Route
    path="/register"
    element={<Register />}
/>

            <Route
                path="/"
                element={<Login />}
            />

            <Route
                path="/dashboard"
                element={<Dashboard />}
            />
            <Route
                path="/dashboard"
                element={<Dashboard />}
            />

            <Route
                path="/resume"
                element={<ResumeUpload />}
            />
            <Route
                path="/interview"
               element={<Interview />}
            />
            <Route
    path="/analytics"
    element={<Analytics />}
/>

        </Routes>

    );
}

export default App;