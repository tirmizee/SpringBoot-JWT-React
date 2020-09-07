import * as actionTypes from '../action/action';

const initialState = {
    isTokenExpired: false
};

const reducer = ( state = initialState, action ) => {
    switch ( action.type ) {

        case actionTypes.TOKEN_EXPIRED : {
            return {
                ...state,
                isTokenExpired : action.isTokenExpired
            }
        }
        
    }
    return state;
};

export default reducer;