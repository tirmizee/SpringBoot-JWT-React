import React, { Component } from 'react';
import { BrowserRouter,HashRouter, Route, Switch } from 'react-router-dom';
import { renderRoutes } from 'react-router-config';
import './App.scss';
import ProtectedRoute from './commons/ProtectedRoute'
import Axios from 'axios';

const loading = () => <div className="animated fadeIn pt-3 text-center">Loading...</div>;

// Containers
const DefaultLayout = React.lazy(() => import('./containers/DefaultLayout'));

// Pages
const Login = React.lazy(() => import('./views/Login'));
const Register = React.lazy(() => import('./views/Pages/Register'));
const Page404 = React.lazy(() => import('./views/Pages/Page404'));
const Page500 = React.lazy(() => import('./views/Pages/Page500'));

class App extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <BrowserRouter>
          <React.Suspense fallback={loading()}>
            <Switch>
              <Route exact path="/login" name="Login Page" render={props => <Login {...props}/>} />
              <Route exact path="/register" name="Register Page" render={props => <Register {...props}/>} />
              <Route exact path="/404" name="Page 404" render={props => <Page404 {...props}/>} />
              <Route exact path="/500" name="Page 500" render={props => <Page500 {...props}/>} />
              <ProtectedRoute path="/" name="Home" component={DefaultLayout} />
            </Switch>
          </React.Suspense>
      </BrowserRouter>
    );
  }
}

export default App;
