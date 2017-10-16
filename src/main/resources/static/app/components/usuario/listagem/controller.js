var myApp = angular.module('myApp');

myApp.controller('usuarioListagemController',
    ['$scope', 'usuarioListagemService', function ($scope, usuarioListagemService) {

        $scope.$on('USUARIOS_FETCHED', function (e, data) {
            $scope.usuarios = data;
        });

        function initizialize() {
            usuarioListagemService.fetchAll();
        }

        initizialize();
    }]
);