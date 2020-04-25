import Axios from 'axios';

const APIManager = { 
  
  methodGET(url, config, callback){
     Axios
      .get(url, config)
      .then(res => {
        callback(res);
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
