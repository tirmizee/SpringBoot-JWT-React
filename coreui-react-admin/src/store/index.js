import { createStore, combineReducers, applyMiddleware } from 'redux';
import reducer from './reducer/reducer'

const rootReducer = combineReducers({
    token: reducer,
});

const loggerMiddleware = store => {
    return next => {
        return action => {
            if(action.type === 'LOGIN_SUCCESS') {
            
            }
            console.log('Dispatch : ', action);
            console.log('[Middleware] : ', store.getState());
            return next(action);
        }
    }
}

const store = createStore(rootReducer, applyMiddleware(loggerMiddleware));

export default store;