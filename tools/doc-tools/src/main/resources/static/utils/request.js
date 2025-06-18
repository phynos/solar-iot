// 请求
let http = axios.create({
	baseURL: "",
	timeout: 6000
})
// 请求拦截
http.interceptors.request.use(config => {
	//请求头设置
	config.headers.Authorization = "Bearer " + localStorage.getItem('token') || ''
	return config
}, err => {
	console.log(err);
})
// 响应拦截
http.interceptors.response.use(arr => {
	return arr.data
}, err => {
	console.log(err);
})
