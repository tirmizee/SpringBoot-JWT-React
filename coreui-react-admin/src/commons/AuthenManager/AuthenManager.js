import APIManager from '../APIManager'
import {ACCESS_TOKEN, API_LOGIN_URI, API_LOGOUT_URI} from '../../constants'

class AuthenManager { 

  login(usernameAndPasswordBase64, callback) {
    const headers = { 'Authorization' : `Basic ${usernameAndPasswordBase64}` };
    APIManager.methodPOST(API_LOGIN_URI, {}, {headers}, callback);
  }

  logout(callback) {
    const headers = { 'Authorization' : `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };
    APIManager.methodGET(API_LOGOUT_URI, {headers}, callback);
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
