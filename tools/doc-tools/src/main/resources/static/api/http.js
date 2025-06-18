import * as constants from './constants.js';
import * as config from '../../config/config.js';
import utils from '@/common/js/util.js';
import userApi from '@/api/user/user-api.js';

//判断是否可以调整注销页面
let firstFlag = true;
//定义一个公共的http请求对象封装所有的请求方法
export default {
	//get请求
	requestGet(url, param) {
		return new Promise((resolve, reject) => {
			uni.request({
				url: url, //真实接口地址。
				data: param || {}, //参数
				header: getRequestHeader(url, 'GET'),
				method: 'GET', //请求的方式
				// 成功使用resolve
				success: (res) => {
					//请求命令是成功的
					resolve(getResponseResult(res))
					//异常响应处理
					this.errorEvent(res, url);
				},
				//失败调用reject
				fail: (err) => {
					//请求命令是异常失败的
					reject(getResponseResult(err));
					//打印请求失败的错误日志
					if (constants.SHOW_LOG_FLAG) {
						console.log(err);
					}
				}
			});
		})
	},
	//post请求
	requestPost(url, param) {
		return new Promise((resolve, reject) => {
			uni.request({
				url: url, //真实接口地址。
				data: param || {}, //参数
				header: getRequestHeader(url, 'POST'),
				method: 'POST', //请求的方式
				// 成功使用resolve
				success: (res) => {
					//请求命令是成功的
					resolve(getResponseResult(res))
					//异常响应处理
					this.errorEvent(res, url);
				},
				//失败调用reject
				fail: (err) => {
					//请求命令是异常失败的
					reject(getResponseResult(err));
					//打印请求失败的错误日志
					if (constants.SHOW_LOG_FLAG) {
						console.log(err);
					}
				}
			});
		})
	},
	//自定义请求
	request(url, param, methodType, requestType) {
		return new Promise((resolve, reject) => {
			uni.request({
				url: url, //真实接口地址.
				data: param || {}, //参数
				header: getRequestHeader(url, methodType, requestType),
				method: methodType || 'POST', //请求的方式
				// 成功使用resolve
				success: (res) => {
					//请求命令是成功的
					resolve(getResponseResult(res));
					//异常响应处理
					this.errorEvent(res, url);
				},
				//失败调用reject
				fail: (err) => {
					//请求命令是异常失败的
					reject(getResponseResult(err));
					//打印请求失败的错误日志
					if (constants.SHOW_LOG_FLAG) {
						console.log(err);
					}
				}
			});
		})
	},
	uploadFile(url, param) {
		return new Promise((resolve, reject) => {})
	},
	downloadFile(url, param) {
		return new Promise((resolve, reject) => {
		})
	},
	errorEvent(res, url) {
		//注意这个位置会存在多个异步请求触发401错误事件
		if (res.statusCode == 401) {
			//获取登录页面
			const urlPath = utils.getLoginOutPath();
			//处理
			if (firstFlag) {
				//表示已经触发过了注销方法 不可以重复触发
				firstFlag = false;
				//提示
				console.log("触发注销方法", url);
				//触发401注销
				uni.reLaunch({
					url: urlPath,
					success: function(res) {
						//清空数据
						userApi.logout();
						//注销跳转页面
						console.log("注销跳转页面成功", urlPath);
					},
					fail: function(err) {
						if (constants.SHOW_LOG_FLAG) {
							console.log(err);
						}
					}
				})
			} else {
				console.log("重复触发页面注销方法", url);
			}
		}
	},
	//重置标识符
	resetFirstFlag() {
		firstFlag = true;
	}
}
//封装响应结果参数
function getResponseResult(res) {
	//响应格式缓存
	const result = {};
	//命令是发送成功的
	if (res.statusCode == 200) {
		//res是框架封装的参数 result是接口返回的参数
		const responseData = res.result || res.data;
		//判断响应的结果类型
		if (responseData.hasOwnProperty('code') || responseData.hasOwnProperty('status')) {
			//获取对应的响应结果状态
			if (responseData.hasOwnProperty('code')) {
				//同步
				result['code'] = responseData.code;
			} else if (responseData.hasOwnProperty('status')) {
				//同步
				result['code'] = responseData.status;
			}
			//code统一格式处理
			if (result['code'] == 0 || result['code'] == 200) {
				//格式统一
				result['code'] = 200;
			}
			//获取当前的响应对象
			if (responseData.hasOwnProperty('message')) {
				//同步
				result['message'] = responseData.message;
			} else if (responseData.hasOwnProperty('msg')) {
				//同步
				result['message'] = responseData.msg;
			}
			//缓存对应的响应结果
			if (responseData.hasOwnProperty('result')) {
				//同步
				result['data'] = responseData.result;
			} else if (responseData.hasOwnProperty('data')) {
				//同步
				result['data'] = responseData.data;
			} else {
				//没有对应的结果
				result['data'] = responseData;
			}
		} else if (responseData || responseData === 0) {
			//没有对应的code 直接返回结果的 也算请求成功
			result.code = 200;
			//结果对象
			result.data = responseData;
		} else {
			//响应数据data
			result.data = responseData;
		}
	} else {
		//失败
		result.code = res.statusCode;
		//结果
		result.data = {};
		//缓存对应的后台错误信息
		result.message = res.message ? res.message : (res.msg ? res.msg : '请求异常');
	}
	return result;
}

//获取网络请求的参数头封装
function getRequestHeader(url, method, requestType) {
	//创建缓存请求头参数
	const headerParam = {};
	//获取全局缓存的token参数
	const token = uni.getStorageSync(constants.SESSION_KEY);
	//携带请求token参数
	if (token) {
		//权限系统需要的token
		headerParam['Authentication'] = token;
		//wedora需要的ukey参数
		if (url.indexOf(constants.WALLE.API_PLAY_VIDEO) == -1) {
			//缓存
			headerParam['ukey'] = token;
		}
		//查询权限系统的分组的时候 需要携带systemid 不然查询的是全部
		if (url.indexOf("/auth") > -1) {
			//获取当前要进行分组查询的时候 app的模块选中类型
			let currentModule = utils.getCurrentAppItem();
			//缓存对应的systemid
			if (currentModule["systemid"]) {
				//缓存平台参数 用于查询权限系统中的分组信息
				headerParam['systemid'] = currentModule["systemid"];
			}
		}
	}
	//添加必须的请求头参数
	const authtimestamp = uni.getStorageSync('authtimestamp');
	//使用权限系统的登录 需要的参数
	if (authtimestamp) {
		//缓存加密时间参数
		headerParam['authtimestamp'] = authtimestamp;
	}
	headerParam['tenant'] = 'hd';
	//请求参数类型
	if (requestType) {
		//application/x-www-form-urlencoded 的数据，会将数据转换为 query string
		headerParam['content-type'] = requestType;
	} else {
		//header['content-type'] 为 application/json 的数据，会进行 JSON 序列化
		headerParam['content-type'] = 'application/json;charset=UTF-8';
	}
	return headerParam;
}