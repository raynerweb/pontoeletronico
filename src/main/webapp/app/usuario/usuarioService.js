(function() {
	'use strict';

	angular
		.module('usuarioServices', [])
		.factory('usuarioService', usuarioService);

	function usuarioService($http, CONTEXTO, $log) {

		return {
			recuperarUsuarios : recuperarUsuarios,
			atualizarUsuario : atualizarUsuario,
			cadastrarUsuario : cadastrarUsuario,
			limparSenha : limparSenha
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
			return $http.post(CONTEXTO + '/usuario/atualizar-usuario', usuario);
		}
		
		function cadastrarUsuario(usuario){
			var usuarioDto = {
					perfil : usuario.perfil.descricao,
					status : usuario.status.descricao,
					matricula : usuario.matricula,
					nome : usuario.nome
			};
			return $http.post(CONTEXTO + '/usuario/cadastrar', usuarioDto);
		}
		
		function limparSenha(usuario){
			var parametros = {
				idUsuario : usuario.id
			};
			return $http.get(CONTEXTO + '/usuario/limpar-senha', {params : parametros});
		}

		function errorCallback(response) {
			$log.log(JSON.stringify(response));
			return response;
		}
	}

})();
