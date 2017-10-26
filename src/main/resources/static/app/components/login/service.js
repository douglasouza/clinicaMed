var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('loginService', function ($rootScope, $http, $state) {

    this.autenticarUsuario = function (usuario, senha) {
        var config = {
            params: {
                usuario: usuario,
                senha: senha
            }
        };

        $http.post('/autenticar', '', config)
            .success(function (data, status, headers, config) {
                $rootScope.authenticated = true;
                $state.go('home');
            })
            .error(function (data, status, headers, config) {
                $rootScope.authenticationError = true;
                Session.invalidate();
            });
    };

});