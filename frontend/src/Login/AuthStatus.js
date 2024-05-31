import React, { useContext } from 'react';
import { AuthContext } from './AuthContext';

const AuthStatus = () => {
    const { isLoggedIn } = useContext(AuthContext);

    return (
        <div>
            {isLoggedIn ? (
                <p>You are logged in!</p>
            ) : (
                <p>Please log in to access protected content</p>
            )}
        </div>
    );
};

export default AuthStatus;
