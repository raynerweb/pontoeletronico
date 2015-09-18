(function() {
	'use strict';

	angular
		.module('cadastrarUsuarioControllers', [])
		.controller('cadastrarUsuarioController', cadastrarUsuarioController);

	function cadastrarUsuarioController(usuarioObject, perfilService) {
		var vm = this;

		vm.usuario = {};
		vm.iniciar = iniciar;

		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
			
			recuperarPerfis();
			
		}
		
		function recuperarPerfis(){
			vm.perfis = [];
			perfilService.recuperarPerfils().then(function(response){
				vm.perfis = response.data;
			});
		}
	}

})();
