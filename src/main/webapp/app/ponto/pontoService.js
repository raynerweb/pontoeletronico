(function() {
	'use strict';

	angular
		.module('pontoServices', [])
		.factory('pontoService', pontoService);

	function pontoService($http, $location, CONTEXTO) {

		return {
			registrarPonto : registrarPonto
		}

		function registrarPonto(idUsuario, dataRegistro, alerts) {
			var registroPonto = {};
			registroPonto.idUsuario = idUsuario;
			registroPonto.dataRegistro = dataRegistro.getTime();
			
			return $http.post(CONTEXTO + '/registrar-ponto', registroPonto)
			.then(
				function(response) {
					console.log(response);
					if (response.statusText == "OK") {
						alerts.push({type:'success', msg : 'Registro realizado com sucesso'});
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
