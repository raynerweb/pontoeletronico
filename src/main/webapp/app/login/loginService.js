(function() {
	'use strict';

	angular
		.module('loginServices', [])
		.factory('loginService', loginService);

	function loginService($http, $location, usuarioObject, CONTEXTO) {

		return {
			login : login,
			logout : logout
		}

		function login(usuario) {
			return $http.post(CONTEXTO + '/login', usuario)
			.then(
				function(response) {
					if (response.statusText == "OK") {
						usuarioObject.carregar(response.data);
						$location.path('/painel');
					}
				}, 
				errorCallback
			);
		}
		
		function logout(){
			return $http.get(CONTEXTO + '/login/logout')
			.then(
				function(response) {
					if (response.statusText == "OK") {
						usuarioObject.limpar()
						$location.path('/login');
					}
				}, 
				errorCallback
			);
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
