var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('loginService',
    ['$rootScope', '$http', '$state', 'jQuery', function ($rootScope, $http, $state, $) {

        this.autenticarUsuario = function (usuario, senha) {
            var config = {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };

            var params = {
                usuario: usuario,
                senha: senha
            };

            $http.post('/autenticar', $.param(params), config)
                .success(function (data) {
                    $rootScope.usuarioLogado = data;
                    $rootScope.$broadcast('LOGIN_SUCCESS');
                })
                .error(function () {
                    $rootScope.$broadcast('LOGIN_ERROR');
                });
        };

        this.usuarioTemPermissao = function (usuariosAutorizados) {
            if (!usuariosAutorizados)
                return true;
            if (usuariosAutorizados.indexOf($rootScope.usuarioLogado.tipoUsuario) != -1)
                return true;
            return false;
        };

        this.getUsuarioLogado = function () {
            $http.get('/seguranca/usuarioLogado').success(function (data) {
                $rootScope.usuarioLogado = data;
                $rootScope.$broadcast('USUARIO_LOGADO_CARREGADO');
            });
        };

        this.ativarUsuario = function (usuario, senha) {
            var config = {
                params: {
                    login: usuario,
                    novaSenha: senha
                }
            };

            $http.put('/seguranca/ativarUsuario', '', config).success(function (data) {
                $rootScope.usuarioLogado = data;
                $rootScope.$broadcast('USUARIO_ATIVADO');
            });
        };
    }]
);