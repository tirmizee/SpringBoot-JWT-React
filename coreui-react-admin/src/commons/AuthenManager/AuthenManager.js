import APIManager from '../APIManager'

class AuthenManager { 

  constructor(props) {
    this.authenticated = false;
  }

  login(callback) {
    APIManager.methodGET();
    this.authenticated = true;
    localStorage.setItem('AccessToken', 'AAAAAA');
    callback();
  }

  isAuthenticated(){
    return this.authenticated;
  }

}

export default new AuthenManager();
