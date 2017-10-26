var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('loginController',
    ['$scope', 'loginService', function ($scope, loginService) {

        $scope.autenticar = function () {
            loginService.autenticarUsuario($scope.usuario, CryptoJS.SHA256($scope.senha).toString());
        };
    }]
);