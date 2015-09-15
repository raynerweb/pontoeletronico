(function() {
	'use strict';

	angular
		.module('app', [

			//$routeProvider
			'ngRoute',

			//'ui.bootstrap',

			//Disponibilizando modulos internos
			//'constantesApps',

			//app
			'constants',
			'loginControllers',
			'loginServices'

		])
		.factory('myInterceptor', interceptor)
		.config([
			'$httpProvider',
			'$routeProvider',
			appConfig
		])
		.run([function(){
			angular.noop;
		}]);
	
		function interceptor($q) {
		
			return {
//				request : requestInterceptor,
				requestError : requestErrorInterceptor,
//				response : responseInterceptor,
				responseError: responseErrorInterceptor
			};
			
			function requestInterceptor(config){
//				console.log(JSON.stringify(config));
			}
			
			function requestErrorInterceptor(rejection){
				console.log(JSON.stringify(rejection));
				return $q.reject(rejection);
			}
			
			function responseInterceptor(response){
//				console.log(JSON.stringify(response));
			}
			
			function responseErrorInterceptor(rejection){
				console.log(JSON.stringify(rejection));
				return $q.reject(rejection);
			}
			
		}

		function appConfig($httpProvider, $routeProvider){
//			$httpProvider.defaults.withCredentials = true;
//			$httpProvider.defaults.useXDomain = true;
			
//			function httpInterceptor($q){
//				return {
//					'request': requestInterceptor,
//					'requestError': requestErrorInterceptor,
//					'response' : responseInterceptor,
//					'responseError': responseErrorInterceptor
//				};
//				
//				function requestInterceptor(config){
//					console.log(config);
//				}
//				
//				function requestErrorInterceptor(rejection){
//					console.log(rejection);
//				}
//				
//				function responseInterceptor(response){
//					console.log(response);
//				}
//				
//				function responseErrorInterceptor(rejection){
//					console.log(rejection);
//				}
//				
//			};
//			
			$httpProvider.interceptors.push('myInterceptor');
			

			$routeProvider.
				when('/login', {
					templateUrl  : 'app/login/login.html',
					controller   : 'loginController',
					controllerAs : 'vm'
				}).
				otherwise({
					redirectTo: '/login'
				});
		}

})(angular);