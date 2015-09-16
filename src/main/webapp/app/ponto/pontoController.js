(function() {
	'use strict';

	angular
		.module('pontoControllers', [])
		.controller('pontoController', pontoController);

	function pontoController($timeout, $location, pontoService, usuarioObject) {
		var vm = this;
		
		vm.relogio = new Date();
		vm.iniciar = iniciar;
		vm.atualizarHora = atualizarHora;
		vm.registrarPonto = registrarPonto;
		
		function iniciar(){
			vm.usuario = usuarioObject.recuperar();
			console.log(vm.usuario);
			if (objectUtils.isEmpty(vm.usuario)){
				$location.path('/login');
			}
			atualizarHora();
		}
		
		function atualizarHora(){
			vm.relogio = new Date();
			$timeout(vm.atualizarHora, 500);
		}
		
		function registrarPonto(){
			pontoService.registrarPonto(vm.usuario.id, new Date());
		}
	}

})();