(function() {
	'use strict';

	angular
		.module('loginControllers', [])
		.controller('loginController', loginController);

	function loginController($scope, loginService) {
		var vm = this;

		vm.usuario = {};
		vm.login = login;

		function login(form) {
			form.submitted = true;
			if (form.$valid) {
				loginService.login(vm.usuario);
			}
		}

	}

})();