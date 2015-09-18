(function() {
	'use strict';

	angular
		.module('cadastrarUsuarioControllers', [])
		.controller('cadastrarUsuarioController', cadastrarUsuarioController);

	function cadastrarUsuarioController(usuarioObject) {
		var vm = this;

		vm.iniciar = iniciar;

		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
		}
	}

})();
