import APIManager from '../APIManager'

class AuthenManager { 

  constructor(props) {
    this.authenticated = false;
  }

  login(usernameAndPasswordBase64, callback) {
    const headers = { 
      'Authorization' : `Basic ${usernameAndPasswordBase64}`
    };
    APIManager.methodPOST('http://localhost:8888/jwt/auth/token', {}, {headers}, callback);
  }

  setAuthenticated(authenticated){
    this.authenticated = authenticated;
  }

  isAuthenticated(){
    return this.authenticated;
  }

}

export default new AuthenManager();
