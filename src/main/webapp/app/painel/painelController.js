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
			var ocorrencia = {titulo : 'Ocorrências', template : 'app/ocorrencia/ocorrencia.html', active: false, disabled: false};
			vm.menus.push(ponto);
			vm.menus.push(ocorrencia);

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
