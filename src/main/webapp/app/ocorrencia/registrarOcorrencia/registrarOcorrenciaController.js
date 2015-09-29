(function() {
	'use strict';

	angular
		.module('registrarOcorrenciaControllers', [])
		.controller('registrarOcorrenciaController', registrarOcorrenciaController);

	function registrarOcorrenciaController(usuarioObject, $log, ocorrenciaService) {
		var vm = this;
		
		vm.alerts = [];
		vm.ocorrencia = {};
		
		vm.iniciar = iniciar;
		vm.iniciarCalendario = iniciarCalendario;
		vm.mostrarCalendario = mostrarCalendario;
		vm.registrarOcorrencia = registrarOcorrencia;
		vm.fecharAlert = fecharAlert;
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
			
			iniciarCalendario();
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
			if (form.$valid) {
				vm.ocorrencia.dataRegistro = vm.calendario.data;
				var usuario = usuarioObject.recuperar();
				vm.ocorrencia.idUsuario = usuario.id;
				ocorrenciaService.registrarOcorrencia(vm.ocorrencia).then(
						function(response){
							vm.alerts.push({type : 'info', msg : 'Ocorrência registrada...'});
							vm.ocorrencia = {};
						},
						function(response){
							angular.forEach(response.data, function(mensagem, key){
								vm.alerts.push({type : 'danger', msg : mensagem});
							});
						}
				)
			}
		}
		
		function fecharAlert(index){
			vm.alerts.splice(index, 1);
		}

	}

})();
