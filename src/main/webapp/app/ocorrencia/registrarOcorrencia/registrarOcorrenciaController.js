(function() {
	'use strict';

	angular
		.module('registrarOcorrenciaControllers', [])
		.controller('registrarOcorrenciaController', registrarOcorrenciaController);

	function registrarOcorrenciaController(usuarioObject) {
		var vm = this;
		
		vm.alerts = [];
		vm.justificativa = '';
		
		vm.iniciar = iniciar;
		vm.iniciarCalendario = iniciarCalendario;
		vm.mostrarCalendario = mostrarCalendario;
		vm.fecharAlert = fecharAlert;
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
			
			recuperarPonto(new Date());
		}
		
		function iniciarCalendario(){
			vm.calendario = {};
			vm.calendario.data = new Date();
			vm.calendario.maxDate = new Date();
			vm.calendario.isOpen = false;
			vm.calendario.format = "dd/MM/yyyy";
		}
		
		function mostrarCalendario($event){
			vm.calendario.isOpen = true;
		}
		
		function registrarOcorrencia(){
			console.log('chamar ocorrenciaService.registrarOcorrencia()');
		}
		
		function recuperarPonto(data){
			console.log('chamar ocorrenciaService.recuperarPonto(usuario, new Date().getTime())');
			vm.alerts.push({type:'warning', msg : 'Não há registro de ponto no dia!'});
			
		}
		
		function fecharAlert(index){
			vm.alerts.splice(index, 1);
		}

	}

})();
