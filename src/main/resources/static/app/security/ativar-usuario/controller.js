var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('ativarUsuarioController',
    ['$rootScope', '$scope', '$state', '$timeout', 'jQuery', 'ativarUsuarioService',
        function ($rootScope, $scope, $state, $timeout, $, ativarUsuarioService) {

            $scope.$on('USUARIO_ATIVADO', function () {
                $('#usuarioAtivado').modal({backdrop: 'static', keyboard: false});
                $('#usuarioAtivado').show();
                $timeout(function () {
                    $('#usuarioAtivado').hide();
                    $('.modal-backdrop').remove();
                    $state.go('home');
                }, 5000);
            });

            $scope.ativarUsuario = function () {
                ativarUsuarioService.ativarUsuario($rootScope.usuarioLogado.login, CryptoJS.SHA256($scope.novaSenha).toString());
            };
        }
    ]
);