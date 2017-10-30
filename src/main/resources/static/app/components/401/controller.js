var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('acessoNegadoController',
    ['$state', '$timeout', function ($state, $timeout) {
        function inicializar() {
            $timeout(function () {
                $state.go('home');
            }, 5000);
        }

        inicializar();
    }]
);