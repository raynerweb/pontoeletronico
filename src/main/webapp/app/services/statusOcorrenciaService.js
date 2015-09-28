(function() {
	'use strict';

	angular
		.module('statusOcorrenciaServices', [])
		.factory('statusOcorrenciaService', statusOcorrenciaService);

	function statusOcorrenciaService($http, CONTEXTO) {

		return {
			recuperarStatusOcorrencia : recuperarStatusOcorrencia
		}
		
		function recuperarStatusOcorrencia(){
			return $http.get(CONTEXTO + '/status-ocorrencia').then(function(response){ return response.data}, errorCallback);
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
