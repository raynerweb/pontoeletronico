(function() {
	'use strict';

	angular
		.module('perfilServices', [])
		.factory('perfilService', perfilService);

	function perfilService($http, CONTEXTO) {

		return {
			recuperarPerfils : recuperarPerfils
		}
		
		function recuperarPerfils(){
			return $http.get(CONTEXTO + '/perfil').then(function(response){ return response}, errorCallback);
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
