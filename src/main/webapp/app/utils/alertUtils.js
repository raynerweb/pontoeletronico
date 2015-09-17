(function() {
	'use strict';

	angular
		.module('alertModule', [])
		.factory('alertUtils', alertUtils);

	function alertUtils() {

		return {
			success : success,
			info : info,
			warning : warning,
			danger : danger,
			limpar : limpar
		}

		function success(alerts) {
			return $http.post(CONTEXTO + '/login', usuario)
			.then(
				function(response) {
					console.log(response);
//					angular.copy(response.data);
				}, 
				errorCallback
			);
		}

		function errorCallback(response) {
			console.log(JSON.stringify(response));
		}
	}

})();
