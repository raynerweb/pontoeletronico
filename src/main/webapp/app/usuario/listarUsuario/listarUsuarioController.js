(function() {
	'use strict';

	angular
		.module('listarUsuarioControllers', [])
		.controller('listarUsuarioController', listarUsuarioController);

	function listarUsuarioController(usuarioObject, usuarioService, perfilService, statusUsuarioService, $filter) {
		var vm = this;

		vm.iniciar = iniciar;
		vm.apresentarFiltros = false;
		vm.usuario = {};
		vm.usuario.status = '';
		vm.usuario.perfil = '';
		vm.atualizarUsuario = atualizarUsuario;
		vm.editarUsuario = editarUsuario;
		vm.filtrarStatus = filtrarStatus;
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
			recuperarStatusUsuario();
			recuperarPerfis();
			recuperarUsuarios();
		}
		
		function recuperarUsuarios(){
			vm.usuarios = []
			usuarioService.recuperarUsuarios('', '').then(function(response){
				vm.usuarios = response;
			});
		}
		
		function recuperarStatusUsuario(){
			vm.status = [];
			statusUsuarioService.recuperarStatusUsuario().then(function(response){
				angular.copy(response, vm.status);
			});
		}
		
		function recuperarPerfis(){
			vm.perfis = [];
			perfilService.recuperarPerfils().then(function(response){
				angular.copy(response, vm.perfis);
			});
		}
		
		function atualizarUsuario(usuario){
			usuarioService.atualizarUsuario(usuario).then(function(response){
				usuario.modoEdicao = false;
			});
		}
		
		function editarUsuario(usuario){
			usuario.modoEdicao = true;
		}
		
		function filtrarStatus(statusUsuario){
			vm.usuario.status = statusUsuario;
		}
		
		function filtrarPerfil(perfilUsuario){
			vm.usuario.perfil = perfilUsuario;
		}
		
	}

})();
