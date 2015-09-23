(function() {
	'use strict';

	angular
		.module('listarUsuarioControllers', [])
		.controller('listarUsuarioController', listarUsuarioController);

	function listarUsuarioController(usuarioObject) {
		var vm = this;

		vm.iniciar = iniciar;
		vm.apresentarFiltros = false;
		vm.statusUsuario = 'TODOS';
		vm.perfilUsuario = 'TODOS';
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
		}
	}

})();
