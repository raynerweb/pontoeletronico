(function() {
	'use strict';

	angular
		.module('pontoServices', [])
		.factory('pontoService', pontoService);

	function pontoService($http, $location, CONTEXTO) {

		return {
			registrarPonto : registrarPonto
		}

		function registrarPonto(idUsuario, dataRegistro) {
			return $http.post(CONTEXTO + '/registrar-ponto', {idUsuario : idUsuario, dataRegistro : dataRegistro})
			.then(
				function(response) {
					console.log(response);
					if (response.statusText == "OK") {
//						usuarioObject.carregar(response.data);
//						$location.path('/painel');
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
