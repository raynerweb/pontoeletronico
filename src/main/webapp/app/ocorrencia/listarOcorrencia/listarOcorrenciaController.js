(function() {
	'use strict';

	angular
		.module('listarOcorrenciaControllers', [])
		.controller('listarOcorrenciaController', listarOcorrenciaController);

	function listarOcorrenciaController(usuarioObject, ocorrenciaService, statusOcorrenciaService, $log) {
		var vm = this;
		
		vm.alerts = [];
		vm.apresentarFiltros = false;
		
		vm.justificativaDaOcorrencia = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras diam enim, ultricies sit amet orci a, placerat malesuada lacus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In pulvinar pharetra elit, ut euismod velit. Vestibulum non congue metus, quis feugiat lacus. Aliquam erat volutpat. Aliquam sodales convallis nibh, a cursus nulla facilisis eu. Cras id eros nisl. Vivamus tincidunt mi arcu, viverra luctus urna pulvinar in. ";
		
		vm.iniciar = iniciar;
		vm.mostrarCalendarioInicial = mostrarCalendarioInicial;
		vm.mostrarCalendarioFinal = mostrarCalendarioFinal;
		vm.fecharAlert = fecharAlert;
		vm.listarOcorrencias = listarOcorrencias;
		
		function iniciar(){
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
//				$location.path('/login');
			}
			iniciarCalendario();
			recuperarStatusOcorrencia();
			listarOcorrencias();
		}
		
		function iniciarCalendario(){
			vm.calendarioFormat = "dd/MM/yyyy";
			
			var anoAtual = moment().year();
			var mesAtual = moment().month();
			var dataInicial = moment([anoAtual, mesAtual]).toDate();
			var dataFinal = moment(dataInicial).endOf('month').toDate();
			
			vm.calendarioInicial = {};
			vm.calendarioInicial.data = dataInicial;
			vm.calendarioInicial.maxDate = dataFinal;
			vm.calendarioInicial.isOpen = false;
			
			vm.calendarioFinal = {};
			vm.calendarioFinal.data = dataFinal;
			vm.calendarioFinal.maxDate = dataFinal;
			vm.calendarioFinal.isOpen = false;
		}
		
		function recuperarStatusOcorrencia(){
			vm.statusOcorrencia = [];
			statusOcorrenciaService.recuperarStatusOcorrencia().then(function(response){
				vm.statusOcorrencia = response;
			});
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
			var status = [];
			var consulta = {};
			
			angular.forEach(vm.statusOcorrencia, function(value, key){
				if(value.selecionado){
					status.push(value.sigla);
				}
			});
			
			consulta.status = status;
			consulta.dataInicial = vm.calendarioInicial.data.getTime();
			consulta.dataFinal = vm.calendarioFinal.data.getTime();
			var usuario = usuarioObject.recuperar();
			if (objectUtils.isEmpty(usuario)){
				consulta.idUsuario = usuario.id;
			}
			
			vm.ocorrencias = [];
			ocorrenciaService.consultar(consulta).then(function(response){
				vm.ocorrencias = response.data;
			});
		}
		
	}

})();
