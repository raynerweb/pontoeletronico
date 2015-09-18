(function() {
	'use strict';

	angular
		.module('painelControllers', [])
		.controller('painelController', painelController);

	function painelController(usuarioObject, $location, $route, loginService) {
		var vm = this;

		vm.menus = [];
		vm.template = '';

		vm.iniciar = iniciar;
		vm.alterarView = alterarView;
		vm.logout = logout;

		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}

			var ponto = {titulo : 'Ponto', template : 'app/ponto/ponto.html', active: true, disabled: false};
			
			var menuUsuario = {titulo : 'Usuários', active: false, disabled: false, filhos : []};
			var listarUsuarios = {titulo : 'Listar Usuários', template : 'app/usuario/listarUsuario/listarUsuario.html', active: false, disabled: false};
			var cadastrarUsuario = {titulo : 'Cadastrar Usuários', template : 'app/usuario/cadastrarUsuario/cadastrarUsuario.html', active: false, disabled: false};
			menuUsuario.filhos.push(listarUsuarios);
			menuUsuario.filhos.push(cadastrarUsuario);

			var ocorrencia = {titulo : 'Ocorrências', active: false, disabled: false, filhos : []};
			var listarOcorrencia = {titulo : 'Listar Ocorrências', template : 'app/ocorrencia/listarOcorrencia/listarOcorrencia.html', active: false, disabled: false};
			var novaOcorrencia   = {titulo : 'Nova Ocorrências', template : 'app/ocorrencia/registrarOcorrencia/registrarOcorrencia.html', active: false, disabled: false};
			ocorrencia.filhos.push(listarOcorrencia);
			ocorrencia.filhos.push(novaOcorrencia);
			
			vm.menus.push(ponto);
			vm.menus.push(ocorrencia);
			vm.menus.push(menuUsuario);

			alterarView(ponto);
		}

		function alterarView(menu){
			vm.template = menu.template;
			angular.forEach(vm.menus, function(itemMenu){
				itemMenu.active = false;
			});
			menu.active = true;
		}
		
		function logout(){
			loginService.logout();
		}

	}

})();
