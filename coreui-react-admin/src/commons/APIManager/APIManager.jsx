import Axios from 'axios';
import { ACCESS_TOKEN } from '../../constants'


const APIManager =  { 
  
  GET : (url, config, callback) => {
     Axios
      .get(url, config)
      .then(res => {
        callback(res);
      });
  },

  POST : (url, data, callback) => {

    const headers = { 'Authorization': `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };

    Axios
      .post(url, data, {headers})
      .then(res => {
        callback(res);
        if(res.headers['Token-Renew']){
          localStorage.setItem(ACCESS_TOKEN, res.headers['Token-Renew']);
        }
      });
  }

}

export default APIManager;
