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
			var abaPonto = {titulo : 'Registrar Ponto', template : 'app/ponto/ponto.html', active: true, disabled: false};
			var abaOcorrencia = {titulo : 'Ocorrências', template : 'app/ocorrencia/ocorrencia.html', active: false, disabled: false};
			vm.tabs.push(abaPonto);
			vm.tabs.push(abaOcorrencia);
		}

	}

})();