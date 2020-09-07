import 'react-app-polyfill/ie9'; // For IE 9-11 support
import 'react-app-polyfill/stable';
import './polyfill' // import 'react-app-polyfill/ie11'; // For IE 11 support

import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import App from './App';
import * as serviceWorker from './serviceWorker';

import store from './store';

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
 document.getElementById('root'));

serviceWorker.unregister();
