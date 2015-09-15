(function() {
	'use strict';

	angular
		.module('loginServices', [])
		.factory('loginService', loginService);

	function loginService($http, CONTEXTO) {

		return {
			login : login
		}

		function login(usuario) {
			return $http.post(CONTEXTO + '/login', usuario)
			.then(
				function(response) {
					console.log(response);
//					angular.copy(response.data);
				}, 
				errorCallback
			);
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
