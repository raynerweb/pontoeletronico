(function() {
	'use strict';

	angular
		.module('cadastrarUsuarioControllers', [])
		.controller('cadastrarUsuarioController', cadastrarUsuarioController);

	function cadastrarUsuarioController(usuarioObject, perfilService, statusUsuarioService, usuarioService) {
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
				vm.perfis = response;
			});
		}
		
		function recuperarStatusUsuario(){
			vm.statusUsuario = [];
			statusUsuarioService.recuperarStatusUsuario().then(function(response){
				vm.statusUsuario = response;
			});
		}
		
		function cadastrarUsuario(){
			vm.alerts = [];
			usuarioService.cadastrarUsuario(vm.usuario).then(function(response){
				vm.alerts.push({type : 'info', msg : 'Usuario cadastrado...'});
				vm.usuario = {};
			}, function(response){
				angular.forEach(response.data, function(mensagem, key){
					vm.alerts.push({type : 'danger', msg : mensagem});
				});
			});
		}
	}

})();
