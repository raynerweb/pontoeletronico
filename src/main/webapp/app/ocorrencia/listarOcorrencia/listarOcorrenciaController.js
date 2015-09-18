(function() {
	'use strict';

	angular
		.module('listarOcorrenciaControllers', [])
		.controller('listarOcorrenciaController', listarOcorrenciaController);

	function listarOcorrenciaController(usuarioObject) {
		var vm = this;
		
		vm.alerts = [];
		
		vm.statusOcorrencia = {};
		
		vm.iniciar = iniciar;
		vm.iniciarCalendario = iniciarCalendario;
		vm.mostrarCalendarioInicial = mostrarCalendarioInicial;
		vm.mostrarCalendarioFinal = mostrarCalendarioFinal;
		vm.fecharAlert = fecharAlert;
		vm.listarOcorrencias = listarOcorrencias;
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
		}
		
		function iniciarCalendario(){
			vm.calendarioFormat = "dd/MM/yyyy";
			
			vm.calendarioInicial = {};
			vm.calendarioInicial.data = new Date();
			vm.calendarioInicial.maxDate = new Date();
			vm.calendarioInicial.isOpen = false;
			
			vm.calendarioFinal = {};
			vm.calendarioFinal.data = new Date();
			vm.calendarioFinal.maxDate = new Date();
			vm.calendarioFinal.isOpen = false;
		}
		
		function mostrarCalendarioInicial($event){
			vm.calendarioInicial.isOpen = true;
		}
		
		function mostrarCalendarioFinal($event){
			vm.calendarioFinal.isOpen = true;
		}
		
		function fecharAlert(index){
			vm.alerts.splice(index, 1);
		}
		
		function listarOcorrencias(){
			console.log('ocorrenciaService.listarOcorrencias(vm.statusOcorrencia, vm.calendarioInicial.getTime(), vm.calendarioInicial.getTime())');
		}

	}

})();
