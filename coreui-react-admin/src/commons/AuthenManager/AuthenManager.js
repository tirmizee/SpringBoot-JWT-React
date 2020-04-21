import APIManager from '../APIManager'

class AuthenManager { 

  constructor(props) {
    this.token = undefined;
  }

  login(usernameAndPasswordBase64, callback) {
    const headers = { 'Authorization' : `Basic ${usernameAndPasswordBase64}` };
    APIManager.methodPOST('http://localhost:8888/jwt/auth/token', {}, {headers}, callback);
  }

  setAuthenticated(token){
    localStorage.setItem('AccessToken', token);
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
