(function() {
	'use strict';

	angular
		.module('cadastrarUsuarioControllers', [])
		.controller('cadastrarUsuarioController', cadastrarUsuarioController);

	function cadastrarUsuarioController(usuarioObject, perfilService, statusUsuarioService) {
		var vm = this;

		vm.usuario = {};
		vm.iniciar = iniciar;
		vm.cadastrarUsuario = cadastrarUsuario;
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
			
			recuperarPerfis();
			recuperarStatusUsuario();
			
		}
		
		function recuperarPerfis(){
			vm.perfis = [];
			perfilService.recuperarPerfils().then(function(response){
				vm.perfis = response.data;
			});
		}
		
		function recuperarStatusUsuario(){
			vm.statusUsuario = [];
			statusUsuarioService.recuperarStatusUsuario().then(function(response){
				vm.statusUsuario = response.data;
			});
		}
		
		function cadastrarUsuario(){
			vm.alerts = [];
			vm.alerts.push({type : 'info', msg : 'Implementar...'});
		}
	}

})();
