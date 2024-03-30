import axios from 'axios';
axios.defaults.baseURL = ``
axios.defaults.headers.common = {'Authorization': `bearer ${token}`}
export default axios;