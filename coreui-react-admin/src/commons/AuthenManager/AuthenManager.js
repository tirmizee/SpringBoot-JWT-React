import Axios from 'axios';
import {ACCESS_TOKEN, API_LOGIN_URI, API_LOGOUT_URI} from '../../constants'

class AuthenManager { 

  login(usernameAndPasswordBase64, successCallback, errorCallback) {
    const headers = { 'Authorization' : `Basic ${usernameAndPasswordBase64}` };
    Axios
      .post(API_LOGIN_URI, {}, {headers})
      .then(res => {
        successCallback(res);
      })
      .catch(error => {
        if (typeof errorCallback !== 'undefined') {
          errorCallback(error);
        }
      });
  }

  logout(successCallback, errorCallback) {
    const headers = { 'Authorization' : `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };
    Axios
      .get(API_LOGOUT_URI, {headers})
      .then(res => {
        localStorage.removeItem(ACCESS_TOKEN);
        successCallback(res);
      })
      .catch(error => {
        if (typeof errorCallback !== 'undefined') {
          errorCallback(error);
        }
      });
  }

  setAuthenticated(token){
    localStorage.setItem(ACCESS_TOKEN, token);
  }

  getCurrentUser(){
    try {
      let token = localStorage.getItem(ACCESS_TOKEN);
      return JSON.parse(atob(token.split('.')[1]));
    } catch (error) {
      console.log(error)
      return null;
    }
  }

  isAuthenticated() {
    let isAuthenticated = false;
    let token = localStorage.getItem(ACCESS_TOKEN);
    if(token == null) return isAuthenticated;
    let tokenBody = JSON.parse(atob(token.split('.')[1]));
    let date = new Date(tokenBody.exp);

    isAuthenticated = date >= new Date() / 1000;
    return isAuthenticated;
  }

}

export default new AuthenManager();
