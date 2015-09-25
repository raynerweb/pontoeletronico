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
		vm.todos = {sigla : '', descricao : 'Todos'};
		vm.statusUsuario = {sigla : '', descricao : 'Todos'};
		vm.perfilUsuario = {sigla : '', descricao : 'Todos'};
		vm.atualizarUsuario = atualizarUsuario;
		vm.editarUsuario = editarUsuario;
		vm.filtrarStatus = filtrarStatus;
		vm.filtrarPerfil = filtrarPerfil;
		
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
			usuarioService.recuperarUsuarios(statusUsuario.sigla, vm.perfilUsuario.sigla).then(function(response){
				vm.usuarios = response;
			});
		}
		
		function filtrarPerfil(perfilUsuario){
			usuarioService.recuperarUsuarios(vm.statusUsuario.sigla, perfilUsuario.sigla).then(function(response){
				vm.usuarios = response;
			});
		}
	}

})();
