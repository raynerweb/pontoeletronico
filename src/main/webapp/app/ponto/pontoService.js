(function() {
	'use strict';

	angular
		.module('pontoServices', [])
		.factory('pontoService', pontoService);

	function pontoService($http, $location, CONTEXTO) {

		return {
			registrarPonto : registrarPonto
		}

		function registrarPonto(usuario) {
			
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
