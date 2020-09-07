export const TOKEN_EXPIRED = 'TOKEN_EXPIRED';

export const saveTokenExpired = (isExpired) => {
    return {
       type : TOKEN_EXPIRED,
       isTokenExpired : isExpired
    }
}

export const storeTokenExpired = (isExpired) => {
    return dispatch => {
        dispatch(saveTokenExpired(isExpired));
    }
}