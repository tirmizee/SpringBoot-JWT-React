import APIManager from '../APIManager'

class AuthenManager { 

  login(usernameAndPasswordBase64, callback) {
    const headers = { 'Authorization' : `Basic ${usernameAndPasswordBase64}` };
    APIManager.methodPOST('http://localhost:8888/jwt/auth/token', {}, {headers}, callback);
  }

  logout(callback) {
    const headers = { 'Authorization' : `Bearer ${localStorage.getItem('AccessToken')}` };
    APIManager.methodGET('http://localhost:8888/jwt/auth/token/revoke', {headers}, callback);
  }

  setAuthenticated(token){
    localStorage.setItem('AccessToken', token);
  }

  getCurrentUser(){
    try {
      let token = localStorage.getItem('AccessToken');
      return JSON.parse(atob(token.split('.')[1]));
    } catch (error) {
      console.log(error)
      return null;
    }
  }

  isAuthenticated() {
    let isAuthenticated = false;
    let token = localStorage.getItem('AccessToken');
    if(token == null) return isAuthenticated;
    let tokenBody = JSON.parse(atob(token.split('.')[1]));
    let date = new Date(tokenBody.exp);

    isAuthenticated = date >= new Date() / 1000;
    return isAuthenticated;
  }

}

export default new AuthenManager();
