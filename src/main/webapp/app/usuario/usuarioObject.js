(function() {
	'use strict';

	angular
		.module('usuarioObjects', [])
		.factory('usuarioObject', usuarioObject);

	function usuarioObject($http, $location, CONTEXTO) {
		var usuario = {};
		
		return {
			novo : novo,
			carregar : carregar,
			recuperar : recuperar,
			limpar : limpar
		}

		function novo() {
			usuario.nome = '';
			usuario.matricula = '';
			usuario.perfil = '';
			return usuario;
		}
		
		function carregar(u) {
			angular.copy(u, usuario);
			return usuario;
		}
		
		function recuperar(){
			return usuario;
		}
		
		function limpar(){
			usuario = {};
		}

	}

})();
