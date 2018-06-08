var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('ativarUsuarioService',
    ['$rootScope', '$http', function ($rootScope, $http) {
        this.ativarUsuario = function (usuario, senha) {
            var config = {
                params: {
                    login: usuario,
                    novaSenha: senha
                }
            };

            $http.put('/usuario/ativarUsuario', '', config).success(function (data) {
                $rootScope.usuarioLogado = data;
                $rootScope.$broadcast('USUARIO_ATIVADO');
            });
        };
    }]
);