var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('loginService',
    ['$rootScope', '$http', '$state', '$window', 'jQuery', function ($rootScope, $http, $state, $window, $) {

        this.autenticarUsuario = function (usuario, senha) {
            var config = {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };

            var params = {
                usuario: usuario,
                senha: senha
            };

            $http.post('/autenticar', $.param(params), config).success(function (data) {
                $window.localStorage.setItem('usuarioAutenticado', 'true');
                $window.localStorage.setItem('usuario', JSON.stringify(data));
                $rootScope.$broadcast('AUTENTICACAO_REALIZADA');
            });
        };

        this.usuarioEstaLogado = function () {
            if ($window.localStorage.getItem('usuarioAutenticado') == null)
                return false;
            if ($window.localStorage.getItem('usuarioAutenticado') === 'true')
                return true;
        };

        this.usuarioTemPermissao = function (autorizacao) {
            if ($window.localStorage.getItem('usuario') == null)
                return false;
            if (!autorizacao)
                return true;
            if (autorizacao === JSON.parse($window.localStorage.getItem('usuario')).tipoUsuario)
                return true;
            return false;
        };
    }]
);