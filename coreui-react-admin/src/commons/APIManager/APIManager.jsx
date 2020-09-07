import Axios from 'axios';
import store from '../../store';
import {saveTokenExpired} from '../../store/action/action';
import { ACCESS_TOKEN } from '../../constants'

let axiosInternal = () => {
  let axiosInstance  = Axios.create({ baseURL: 'http://localhost:8888/jwt' });
  axiosInstance.interceptors.response.use( 
    response => successHandler(response),
    error => errorHandler(error)
  );
  return axiosInstance;
}

const errorHandler = error => {
  console.log('ErrorHandler');
  switch(error.response.status){
    case 401 : 
      console.log('Token-Expired');
      store.dispatch(saveTokenExpired(true));
    default : return error;
  }
}

const successHandler = (response) => {
  console.log('Token-Renew : interceptors');
  if(response.headers['Token-Renew']){
    localStorage.setItem(ACCESS_TOKEN, response.headers['Token-Renew']);
  }
  return response
}

const GET = (url, config = {}) => {
  try {
    return axiosInternal().get(url, config);
  } catch(err) {
    throw err;
  } 
}

const POST = (url, data) => {
  try {
    const headers = { 'Authorization': `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };
    return axiosInternal().post(url, data, {headers});
  } catch(error) {
    return error;
    
  } 
}

const validateToken = () => {

}

export {
  GET,
  POST
}