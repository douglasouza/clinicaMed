var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('historicoPacienteListagemController',
    ['$scope', '$state', 'historicoPacienteListagemService', function ($scope, $state, historicoPacienteListagemService) {

        $scope.$on('HISTORICO_PACIENTE_FETCHED_SUCCESS', function (e, data) {
            $scope.historicoPaciente = data;
        });

        $scope.$watch('idPaciente', function (novoValor) {
            if (novoValor)
                historicoPacienteListagemService.fetchHistoricoPaciente(novoValor);
            else
                $scope.idPaciente = undefined;
        });


        function initizialize() {
            historicoPacienteListagemService.fetchAllPacientes().then(
                function (data) {
                    $scope.pacientes = data;
                }
            )
        }

        initizialize();
    }]
);