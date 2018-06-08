var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('homeController',
    ['$rootScope', '$scope', 'loginService', function ($rootScope, $scope, loginService) {

        $scope.$on('USUARIO_LOGADO_CARREGADO', function () {
            $scope.usuario = $rootScope.usuarioLogado.login;
        });

        function inicializar() {
            if ($rootScope.usuarioLogado)
                $scope.usuario = $rootScope.usuarioLogado.login;
            else
                loginService.getUsuarioLogado();
        }

        inicializar();
    }]
);