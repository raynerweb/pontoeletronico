(function() {
	'use strict';

	angular
		.module('statusUsuarioServices', [])
		.factory('statusUsuarioService', statusUsuarioService);

	function statusUsuarioService($http, CONTEXTO) {

		return {
			recuperarStatusUsuario : recuperarStatusUsuario
		}
		
		function recuperarStatusUsuario(){
			return $http.get(CONTEXTO + '/status-usuario').then(function(response){ return response.data}, errorCallback);
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
