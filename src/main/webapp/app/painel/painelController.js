(function() {
	'use strict';

	angular
		.module('painelControllers', [])
		.controller('painelController', painelController);

	function painelController(usuarioObject, $location, $route) {
		var vm = this;
		
		vm.iniciar = iniciar;
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
			
			vm.tabs = [];
			var abaPonto = {titulo : 'Registro de Ponto', template : 'app/ponto/ponto.html', active: true, disabled: false};
			vm.tabs.push(abaPonto);
		}

	}

})();