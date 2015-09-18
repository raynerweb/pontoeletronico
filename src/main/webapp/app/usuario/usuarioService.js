(function() {
	'use strict';

	angular
		.module('usuarioServices', [])
		.factory('usuarioService', usuarioService);

	function usuarioService() {

		return {
			
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
