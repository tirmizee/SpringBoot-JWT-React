import Axios from 'axios';

const APIManager = { 
  
  methodGET(){
    Axios
      .get('http://www.mocky.io/v2/5e9ca14830000059000a7f2d')
      .then(res => {

      });
  },
  methodPOST(url, data, config, callback) {
    Axios
      .post(url, data, config)
      .then(res => {
        callback(res);
      });
  }

}

export default APIManager;
