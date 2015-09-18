(function() {
	'use strict';

	angular
		.module('ocorrenciaServices', [])
		.factory('ocorrenciaService', ocorrenciaService);

	function ocorrenciaService() {

		return {
			
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
