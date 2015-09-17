(function() {
	'use strict';

	angular
		.module('ocorrenciaControllers', [])
		.controller('ocorrenciaController', ocorrenciaController);

	function ocorrenciaController() {
		var vm = this;

		vm.template = 'app/ocorrencia/listarOcorrencia.html';
		vm.menus = [];
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}

			var listarOcorrencia = {titulo : 'Listar Ocorrência', template : 'app/ocorrencia/listarOcorrencia.html', active: true, disabled: false};
			var novaOcorrencia   = {titulo : 'Nova Ocorrência', template : 'app/ocorrencia/novaOcorrencia.html', active: false, disabled: false};
			
			vm.menus.push(listarOcorrencia);
			vm.menus.push(novaOcorrencia);

//			alterarView(ponto);
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
