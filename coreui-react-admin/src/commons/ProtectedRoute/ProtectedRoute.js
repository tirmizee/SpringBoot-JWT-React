import React from 'react';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import AuthenManager from '../AuthenManager';

export const ProtectedRoute = ({
  component: Component,
  ...rest
}) => {
  return (
    <Route {...rest}
      render={ props => 
        AuthenManager.isAuthenticated() 
        ? <Component {...props} />
        : (<Redirect to={{ pathname: "/login", state: {from: props.location }}} /> )
      }
    />
  );
};

export default ProtectedRoute