(function() {
	'use strict';

	angular
		.module('app', [

			'ngRoute',
			'ui.bootstrap',

			//app
			'constants',
			'loginControllers',
			'loginServices',
			'painelControllers',
			'pontoControllers',
			'pontoServices',
			'registrarOcorrenciaControllers',
			'listarOcorrenciaControllers',
			'usuarioObjects'

		])
		.factory('requestResponseInterceptor', requestResponseInterceptor)
//		.factory('usuarioObject', usuarioObject)
		.config([
			'$httpProvider',
			'$routeProvider',
			appConfig
		])
//		.run(['$rootScope',
//		      '$location',
//		      
//		      authorizationFilter]);
		.run([function(){
			angular.noop;
		}]);
	
	function usuarioObject() {
		var usuario = {};
		
		return {
			novo : novo,
			carregar : carregar,
			recuperar : recuperar
		}

		function novo() {
			usuario.nome = '';
			usuario.matricula = '';
			usuario.perfil = '';
			return usuario;
		}
		
		function carregar(u) {
			angular.copy(u, usuario);
			return usuario;
		}
		
		function recuperar(){
			return usuario;
		}

	}
	
	function authorizationFilter($rootScope, $location){
		$rootScope.$on('$routeChangeStart', function (event, next) {
			console.log(event);
			console.log(next);
			console.log(usuarioObject);
			if (next.access){
				console.log(next.access);
			}
//            var authorised;
//            if (next.access !== undefined) {
//                authorised = authorization.authorize(next.access.loginRequired,
//                                                     next.access.permissions,
//                                                     next.access.permissionCheckType);
//                if (authorised === jcs.modules.auth.enums.authorised.loginRequired) {
//                    $location.path(jcs.modules.auth.routes.login);
//                } else if (authorised === jcs.modules.auth.enums.authorised.notAuthorised) {
//                    $location.path(jcs.modules.auth.routes.notAuthorised).replace();
//                }
//            }
        });
	}
	
	function requestResponseInterceptor($q, $rootScope) {
	
		return {
			request : requestInterceptor,
			requestError : requestErrorInterceptor,
			response : responseInterceptor,
			responseError: responseErrorInterceptor
		};
		
		function requestInterceptor(config){
			$rootScope.errors = null;
			return config;
		}
		
		function requestErrorInterceptor(rejection){
			return $q.reject(rejection);
		}
		
		function responseInterceptor(response){
			return response;
		}
		
		function responseErrorInterceptor(rejection){
			var errors = [];
			angular.forEach(rejection.data, function(value, key){
				errors.push(value);
			});
			if (!objectUtils.isEmpty(errors)){
				$rootScope.errors = errors;
			}
			return $q.reject(rejection);
		}
		
	}

	function appConfig($httpProvider, $routeProvider){
		$httpProvider.defaults.withCredentials = true;
		$httpProvider.defaults.useXDomain = true;
		
		$httpProvider.interceptors.push('requestResponseInterceptor');

		$routeProvider.
			when('/login', {
				templateUrl  : 'app/login/login.html',
				controller   : 'loginController',
				controllerAs : 'vm'
			}).
			when('/painel', {
				templateUrl  : 'app/painel/painel.html',
				controller   : 'painelController',
				controllerAs : 'vm'
			}).
			otherwise({
				redirectTo: '/login'
			});
	}

})(angular);