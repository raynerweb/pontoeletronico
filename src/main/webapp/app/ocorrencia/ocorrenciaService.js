(function() {
	'use strict';

	angular
		.module('ocorrenciaServices', [])
		.factory('ocorrenciaService', ocorrenciaService);

	function ocorrenciaService($http, CONTEXTO, $log) {

		return {
			consultar : consultar
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

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
