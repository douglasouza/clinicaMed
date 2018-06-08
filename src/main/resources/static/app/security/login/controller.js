var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('loginController',
    ['$rootScope', '$scope', '$state', '$timeout', 'jQuery', 'loginService',
        function ($rootScope, $scope, $state, $timeout, $, loginService) {
            $scope.$on('LOGIN_SUCCESS', function () {
                if (!$rootScope.usuarioLogado.ativado) {
                    $state.go('ativarUsuario');
                } else {
                    $state.go('home');
                }
            });

            $scope.$on('LOGIN_ERROR', function () {
                $scope.erro = true;
            });

            $scope.autenticar = function () {
                loginService.autenticarUsuario($scope.usuario, CryptoJS.SHA256($scope.senha).toString());
            };

            function inicializar() {
                $scope.erro = false;
            }

            inicializar();
        }
    ]
);