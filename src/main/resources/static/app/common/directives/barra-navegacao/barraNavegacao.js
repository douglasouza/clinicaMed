var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('barraNavegacao',
    ['$rootScope', '$state', 'jQuery', 'loginService', function ($rootScope, $state, $, loginService) {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: './app/common/directives/barra-navegacao/template.html',
            link: function (scope) {
                scope.logout = function () {
                    loginService.logout();
                };

                function estaGerenciandoCadastros() {
                    if (scope.estadoAtual.indexOf('medicamento') !== -1
                        || scope.estadoAtual.indexOf('medico') !== -1
                        || scope.estadoAtual.indexOf('paciente') !== -1
                        || scope.estadoAtual.indexOf('recepcionista') !== -1)
                        $('#cadastrosNavBarMenu').addClass('active');

                    if (scope.estadoAtual.indexOf('medicamento') !== -1)
                        $('#medicamentosNavBarMenu').addClass('active');
                    else if (scope.estadoAtual.indexOf('medico') !== -1)
                        $('#medicosNavBarMenu').addClass('active');
                    else if (scope.estadoAtual.indexOf('paciente') !== -1)
                        $('#pacientesNavBarMenu').addClass('active');
                    else if (scope.estadoAtual.indexOf('recepcionista') !== -1)
                        $('#recepcionistasNavBarMenu').addClass('active');
                }

                function estaGerenciandoConsultas() {
                    if (scope.estadoAtual.indexOf('consulta') !== -1)
                        $('#consultasNavBarMenu').addClass('active');
                }

                function estaGerenciandoPrescricoes() {
                    if (scope.estadoAtual.indexOf('prescricao') !== -1)
                        $('#prescricoesNavBarMenu').addClass('active');
                }

                function estaGerenciandoSolicitacoesExame() {
                    if (scope.estadoAtual.indexOf('solicitacaoExame') !== -1)
                        $('#solicitacoesExameNavBarMenu').addClass('active');
                }

                function inicializar() {
                    scope.estadoAtual = $state.current.name;
                    scope.administrador = false;
                    scope.medico = false;
                    scope.recepcionista = false;

                    $('#navbar').find('*').removeClass('active');
                    estaGerenciandoCadastros();
                    estaGerenciandoConsultas();
                    estaGerenciandoPrescricoes();
                    estaGerenciandoSolicitacoesExame();

                    if (!$rootScope.usuarioLogado)
                        return;

                    scope.login = $rootScope.usuarioLogado.login;

                    if ($rootScope.usuarioLogado.tipoUsuario === 'ADMINISTRADOR')
                        scope.administrador = true;
                    else if ($rootScope.usuarioLogado.tipoUsuario === 'MEDICO')
                        scope.medico = true;
                    else if ($rootScope.usuarioLogado.tipoUsuario === 'RECEPCIONISTA')
                        scope.recepcionista = true;
                }

                inicializar();
            }
        }
    }]
);
