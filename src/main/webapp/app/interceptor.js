(function() {
	'use strict';

	angular
		.module('interceptors', [])
		.factory('interceptor', interceptor);

	function interceptor() {

		return {
			'request': requestInterceptor,
			'requestError': requestErrorInterceptor,
			'response' : responseInterceptor,
			'responseError': responseErrorInterceptor
		};
		
		function requestInterceptor(config){
			console.log(config);
		}
		
		function requestErrorInterceptor(rejection){
			console.log(rejection);
		}
		
		function responseInterceptor(response){
			console.log(response);
		}
		
		function responseErrorInterceptor(rejection){
			console.log(rejection);
		}
		
	}

})();
