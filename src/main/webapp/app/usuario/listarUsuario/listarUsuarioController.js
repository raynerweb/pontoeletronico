(function() {
	'use strict';

	angular
		.module('listarUsuarioControllers', [])
		.controller('listarUsuarioController', listarUsuarioController);

	function listarUsuarioController(usuarioObject, usuarioService, perfilService, statusUsuarioService, $log, $filter) {
		var vm = this;

		vm.iniciar = iniciar;
		vm.apresentarFiltros = false;
		vm.statusUsuario = '';
		vm.perfilUsuario = '';
		vm.atualizarUsuario = atualizarUsuario;
		vm.editarUsuario = editarUsuario;
		vm.limparFormularioAlteracao = limparFormularioAlteracao;
		
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
				$log.log(vm.usuarios);
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
				$log.log(response);
			});
		}
		
		function limparFormularioAlteracao(){
			vm.usuarioAlteracao = {};
		}
		
		function editarUsuario(usuario){
			usuario.modoEdicao = true;
		}
		
	}

})();
