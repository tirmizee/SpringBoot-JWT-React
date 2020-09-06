import * as actions from './action/action';

const initialState = {
    counter: 0
};

const reducer = ( state = initialState, action ) => {
    switch ( action.type ) {
        case actions.ADD:
            return {
                ...state,
                counter: state.counter + action.val
            }
    }
    return state;
};

export default reducer;