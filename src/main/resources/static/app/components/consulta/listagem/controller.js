var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('consultaListagemController',
    ['$rootScope', '$scope', '$state', 'constants', 'datePickerUtils', 'consultaListagemService', 'consultaEdicaoService', function ($rootScope, $scope, $state, constants, datePickerUtils, consultaListagemService, consultaEdicaoService) {

        $scope.pesquisar = function () {
            if ($scope.filtro.nomeMedicoPaciente || $scope.filtro.dataInicial || $scope.filtro.dataFinal) {
                consultaListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = {nomeMedicoPaciente: '', dataInicial: null, dataFinal: null};
            consultaListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.editarConsulta = function (id) {
            consultaEdicaoService.getConsulta(id).$promise.then(
                function (consulta) {
                    var dataArray = consulta.dataConsulta.split('-');
                    var dataHoraConsulta = new Date(dataArray[0], dataArray[1] - 1, dataArray[2], consulta.horarioConsulta.horaConsulta.substr(0, 2));
                    if (Date.now() > dataHoraConsulta.getTime())
                        $rootScope.$broadcast('RESPONSE_ERROR', {data: {message: 'Não é possível editar a consulta pois a mesma já foi realizada.'}});
                }
            );
        };

        $scope.excluirConsulta = function (id) {
            $scope.idConsulta = id;
        };

        $scope.confirmarExcluirConsulta = function () {
            consultaListagemService.deleteConsulta($scope.idConsulta);
        };

        $scope.$on('CONSULTAS_FETCHED_SUCCESS', function (e, data) {
            $scope.consultas = data;
        });

        $scope.$on('CONSULTA_DELETE_SUCCESS', function () {
            consultaListagemService.fetchAll($scope.filtro);
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
        }

        function inicializarDadosTabelaListagem() {
            $scope.paginaAtual = 1;
            $scope.colunas = [
                {
                    caminhoNoObjeto: 'nomePaciente',
                    classeCol: 'col-md-4',
                    nome: 'Paciente'
                },
                {
                    caminhoNoObjeto: 'nomeMedico',
                    classeCol: 'col-md-4',
                    nome: 'Médico'
                },
                {
                    caminhoNoObjeto: 'dataHoraConsulta',
                    classeCol: 'col-md-2',
                    nome: 'Data/Horário Consulta'
                }
            ];
        }

        function configurarDatePickerDataInicial() {
            var options = {
                idElemento: '#dataInicial',
                model: 'filtro.dataInicial',
                fdsDisabled: false
            };

            datePickerUtils.config(options, $scope);
        }

        function configurarDatePickerDataFinal() {
            var options = {
                idElemento: '#dataFinal',
                model: 'filtro.dataFinal',
                fdsDisabled: false
            };

            datePickerUtils.config(options, $scope);
        }

        function initizialize() {
            inicializarDadosTabelaListagem();
            $scope.filtro = {nomeMedicoPaciente: '', dataInicial: null, dataFinal: null};
            configurarDatePickerDataInicial();
            configurarDatePickerDataFinal();
            consultaListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);