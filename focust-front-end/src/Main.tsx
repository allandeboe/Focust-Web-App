/**
 * Main.tsx
 * 
 * This handles the display of the pages for the site,
 * as well as routing.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 12th, 2023
 */
import { useState, useEffect } from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';

import Home from './pages/Home';
import About from './pages/About';
import Users from './pages/Users';
import UserPage from './pages/UserPage';

import NoPage from './pages/NoPage';

export default function Main() {

    const location = useLocation();

    const [displayLocation, setDisplayLocation] = useState(location);
    const [transitionStage, setTransitionStage] = useState("fade-in");

    useEffect(() => {
        if (location !== displayLocation) {
            setTransitionStage("fade-out");
        }
    }, [location, displayLocation])

    return (
        <div className={`main ${transitionStage}`} onAnimationEnd={() => {
            if (transitionStage === "fade-out") {
                setTransitionStage("fade-in");
                setDisplayLocation(location);
            }
        }}>
            <Routes location={displayLocation}>
                <Route path="/" element={<Home/>} />
                <Route path="/about-us" element={<About/>} />
                <Route path="/users" element={<Users/>} />
                <Route path="/users/:userId" element={<UserPage/>} />

                <Route path="*" element={<NoPage/>} />
            </Routes>
        </div>
    );
}