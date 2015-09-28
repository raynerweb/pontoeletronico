(function() {
	'use strict';

	angular
		.module('ocorrenciaServices', [])
		.factory('ocorrenciaService', ocorrenciaService);

	function ocorrenciaService($http, CONTEXTO, $log) {

		return {
			consultar : consultar,
			registrarOcorrencia : registrarOcorrencia
		}
		
		function consultar(consulta){
			var parametros = {
				idUsuario : consulta.idUsuario,
				dataInicial : consulta.dataInicial,
				dataFinal : consulta.dataFinal,
				status : consulta.status
			}
			return $http.get(CONTEXTO + '/ocorrencia/consultar', {params : parametros})
		}
		
		function registrarOcorrencia(ocorrencia){
			return $http.post(CONTEXTO + '/ocorrencia/registrarOcorrencia', ocorrencia)
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
