import 'react-app-polyfill/ie9'; // For IE 9-11 support
import 'react-app-polyfill/stable';
import './polyfill' // import 'react-app-polyfill/ie11'; // For IE 11 support

import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, combineReducers, applyMiddleware } from 'redux';
import reducer from './store/reducer'

import App from './App';
import * as serviceWorker from './serviceWorker';

const rootReducer = combineReducers({
    ctr: reducer,
});

const store = createStore(rootReducer);

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
 document.getElementById('root'));

serviceWorker.unregister();
