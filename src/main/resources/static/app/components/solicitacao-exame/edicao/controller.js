var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('solicitacaoExameEdicaoController',
    ['$rootScope', '$scope', '$state', '$stateParams', 'jQuery', 'solicitacaoExameEdicaoService', 'pacienteListagemService', 'medicoListagemService',
        function ($rootScope, $scope, $state, $stateParams, $, solicitacaoExameEdicaoService, pacienteListagemService, medicoListagemService) {
            $scope.salvar = function (formularioValido) {
                if (!formularioValido)
                    return;

                if ($scope.acao === 'NOVO') {
                    solicitacaoExameEdicaoService.saveSolicitacaoExame($scope.solicitacaoExame);
                } else {
                    solicitacaoExameEdicaoService.updateSolicitacaoExame($stateParams.id, $scope.solicitacaoExame);
                }
            };

            $scope.selecionarExame = function (exame) {
                var index = $scope.solicitacaoExame.exames.findIndex(function (ex) {
                    return ex.id === exame.id
                });

                if (index == -1)
                    $scope.solicitacaoExame.exames.push(exame);
                else
                    $scope.solicitacaoExame.exames.splice(index, 1);
            };

            $scope.$on('SOLICITACAO_EXAME_SAVE_SUCCESS', function (event, data) {
                $state.go('solicitacaoExame.edicao', {id: data.id});
            });

            $scope.$on('SOLICITACAO_EXAME_UPDATE_SUCCESS', function (event, data) {
                $state.go('solicitacaoExame.edicao', {id: data.id});
            });

            $scope.$on('SOLICITACAO_EXAME_UPLOAD_SUCCESS', function () {
                $state.reload();
            });

            $scope.$on('SOLICITACAO_EXAME_REMOVE_UPLOAD_SUCCESS', function () {
                $state.reload();
            });

            $scope.$on('PACIENTES_FETCHED_SUCCESS', function (e, data) {
                $scope.pacientes = data;
                medicoListagemService.fetchAll();
            });

            $scope.$on('MEDICOS_FETCHED_SUCCESS', function (e, data) {
                $scope.medicos = data;
                $scope.medicos.forEach(function (medico) {
                    medico.especialidade = undefined;
                });
                solicitacaoExameEdicaoService.fetchAllExames();
            });

            $scope.$on('EXAMES_FETCHED_SUCCESS', function (e, data) {
                $scope.exames = data;

                if ($scope.acao === 'EDICAO') {
                    solicitacaoExameEdicaoService.getSolicitacaoExame($stateParams.id).$promise.then(
                        function (data) {
                            $scope.solicitacaoExame = data;
                            $scope.acaoFinalizada = true;
                            $rootScope.$broadcast('SOLICITACAO_EXAME_FETCHED');
                        }
                    );
                }
            });

            function operacaoSucesso() {
                $scope.mostrarAlertaSucesso = true;
                $scope.acaoFinalizada = true;
            }

            function inicializar() {
                $scope.acao = $state.current.name === 'solicitacaoExame.novo' ? 'NOVO' : 'EDICAO';
                pacienteListagemService.fetchAll();
                if ($scope.acao !== 'EDICAO') {
                    $scope.solicitacaoExame = {};
                }
            }

            inicializar();
        }
    ]
);