(function() {
	'use strict';

	angular
		.module('loginServices', [])
		.factory('loginService', loginService);

	function loginService($http, $location, usuarioObject, CONTEXTO) {

		return {
			login : login
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

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
