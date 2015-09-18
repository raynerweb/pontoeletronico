(function() {
	'use strict';

	angular
		.module('listarOcorrenciaControllers', [])
		.controller('listarOcorrenciaController', listarOcorrenciaController);

	function listarOcorrenciaController(usuarioObject) {
		var vm = this;
		
		vm.alerts = [];
		vm.apresentarFiltros = false;
		vm.statusOcorrencia = {};
		
		vm.justificativaDaOcorrencia = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras diam enim, ultricies sit amet orci a, placerat malesuada lacus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In pulvinar pharetra elit, ut euismod velit. Vestibulum non congue metus, quis feugiat lacus. Aliquam erat volutpat. Aliquam sodales convallis nibh, a cursus nulla facilisis eu. Cras id eros nisl. Vivamus tincidunt mi arcu, viverra luctus urna pulvinar in. ";
		
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
			
			vm.alerts.push({type : 'info' , msg : 'A intenção é que a pesquisa ocorra a cada alteracao do formulario'});
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
		
		function toogleFiltros(){
			vm.apresentarFiltros = !vm.apresentarFiltros;
		}
		
		function listarOcorrencias(){
			console.log('ocorrenciaService.listarOcorrencias(vm.statusOcorrencia, vm.calendarioInicial.getTime(), vm.calendarioInicial.getTime())');
		}

	}

})();
