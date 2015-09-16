(function() {
	'use strict';

	angular
		.module('painelControllers', [])
		.controller('painelController', painelController);

	function painelController(usuarioObject, $location, $route) {
		var vm = this;
		
		vm.iniciar = iniciar;
		
		function iniciar(){
			console.log($route.perfil);
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
				$location.path('/login');
			}
		}

	}

})();