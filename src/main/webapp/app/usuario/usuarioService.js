(function() {
	'use strict';

	angular
		.module('usuarioServices', [])
		.factory('usuarioService', usuarioService);

	function usuarioService($http, CONTEXTO, $log) {

		return {
			recuperarUsuarios : recuperarUsuarios,
			atualizarUsuario : atualizarUsuario
		}
		
		function recuperarUsuarios(siglaStatus, siglaPerfil){
			var parametros = {
				status : siglaStatus, 
				perfil : siglaPerfil
			};
			
			return $http.get(CONTEXTO + '/usuario/recuperar-usuario', {params : parametros})
				.then(
					function(response){
						return response.data;
					}, 
					errorCallback);
		}
		
		function atualizarUsuario(usuario){
			return $http.post(CONTEXTO + '/usuario/atualizar-usuario', usuario)
			.then(
				function(response){
					return response.data;
				}, 
				errorCallback);
		}
		
		function limparSenha(usuario){
			
		}

		function errorCallback(response) {
			$log.log(JSON.stringify(response));
		}
	}

})();
