var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('loginController',
    ['$rootScope', '$scope', '$state', '$timeout', 'jQuery', 'loginService',
        function ($rootScope, $scope, $state, $timeout, $, loginService) {
            $scope.$on('LOGIN_SUCCESS', function () {
                if (!$rootScope.usuarioLogado.ativado) {
                    $('#modalRedefinicaoSenha').modal({backdrop: 'static', keyboard: false});
                    $('#modalRedefinicaoSenha').show();
                } else {
                    $state.go('home');
                }
            });

            $scope.$on('LOGIN_ERROR', function () {
                $scope.erro = true;
            });

            $scope.$on('USUARIO_ATIVADO', function () {
                $('#modalRedefinicaoSenha').hide();
                $('#usuarioAtivado').modal({backdrop: 'static', keyboard: false});
                $('#usuarioAtivado').show();
                $timeout(function () {
                    $('#usuarioAtivado').hide();
                    $('.modal-backdrop').remove();
                    $state.go('home');
                }, 5000);
            });

            $scope.autenticar = function () {
                loginService.autenticarUsuario($scope.usuario, CryptoJS.SHA256($scope.senha).toString());
            };

            $scope.ativarUsuario = function () {
                loginService.ativarUsuario($scope.usuario, CryptoJS.SHA256($scope.novaSenha).toString())
            };

            function inicializar() {
                $scope.erro = false;
                console.clear();
            }

            inicializar();
        }
    ]
);