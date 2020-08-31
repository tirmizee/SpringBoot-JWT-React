import Axios from 'axios';
import { ACCESS_TOKEN } from '../../constants'

let AxiosInternal = () => {
  let axiosInstance  = Axios.create({ baseURL: 'http://localhost:8888/jwt' });
  axiosInstance.interceptors.response.use( 
    response => successHandler(response),
    error => { 
      if (401 === error.response.status) {
        console.log('Token-Expired');
        window.location = '/login';
      } else return error;
    }
  );
  return axiosInstance;
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
    return AxiosInternal().get(url, config);
  } catch(err) {
    throw err;
  } 
}

const POST = (url, data) => {
  try {
    const headers = { 'Authorization': `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };
    return AxiosInternal().post(url, data, {headers});
  } catch(err) {
    throw err;
  } 
}

export {
  GET,
  POST
}