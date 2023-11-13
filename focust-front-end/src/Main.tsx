import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Home from './pages/Home';
import Users from './pages/Users';

import NoPage from './pages/NoPage';

export default function Main() {
    return (
        <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/users" element={<Users/>} />

            <Route path="*" element={<NoPage/>} />
        </Routes>
    );
}