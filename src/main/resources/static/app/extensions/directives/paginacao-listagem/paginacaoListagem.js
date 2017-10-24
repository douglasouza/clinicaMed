var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('paginacaoListagem', function () {
    return {
        scope: {
            numRegistros: '=',
            paginaAtual: '='
        },
        templateUrl: './app/extensions/directives/paginacao-listagem/template.html',
        link: function (scope) {
            scope.paginaAnterior = function () {
                if (scope.paginaAtual - 1 >= 1)
                    scope.paginaAtual--;
            };

            scope.proximaPagina = function () {
                if (scope.paginaAtual + 1 <= scope.qtdPaginas)
                    scope.paginaAtual++;
            };

            scope.irParaPagina = function (pagina) {
                scope.paginaAtual = pagina;
            };

            scope.getPaginaClass = function (numPagina) {
                return scope.paginaAtual === numPagina ? 'active' : '';
            };

            scope.getAnteriorClass = function () {
                return scope.paginaAtual - 1 < 1 ? 'disabled' : '';
            };

            scope.getProximoClass = function () {
                return scope.paginaAtual === scope.qtdPaginas ? 'disabled' : '';
            };

            scope.isNumRegistroMaiorZero = function () {
                return scope.numRegistros > 0;
            };

            scope.$watch('numRegistros', function (novoValor) {
                scope.numRegistros = novoValor;
                if (scope.numRegistros)
                    inicializar();
            });

            scope.$watch('paginaAtual', function (novoValor) {
                scope.paginaAtual = novoValor;
                if (scope.paginaAtual && scope.numRegistros) {
                    atualizarValores();
                    atualizarArrayPaginas();
                }
            });

            function inicializar() {
                scope.qtdPaginas = Math.ceil(scope.numRegistros / 10);
                atualizarValores();
                atualizarArrayPaginas();
            }

            function atualizarValores() {
                scope.inicio = 1 + ((scope.paginaAtual - 1) * 10);
                scope.fim = scope.inicio + 9 < scope.numRegistros ? scope.inicio + 9 : scope.numRegistros;
                scope.existePaginasAnteriores = scope.paginaAtual > 3;
                scope.existePaginasPosteriores = scope.qtdPaginas > 5 && scope.paginaAtual < (scope.qtdPaginas - 3);
            }

            function atualizarArrayPaginas() {
                scope.arrayPaginas = [];
                if (scope.paginaAtual <= 3) {
                    for (var i = 1; i <= (scope.paginaAtual + 5 > scope.qtdPaginas ? scope.qtdPaginas : 5); i++)
                        scope.arrayPaginas.push(i);
                } else if (scope.qtdPaginas - 3 <= scope.paginaAtual) {
                    for (var i = scope.qtdPaginas; i >= (scope.paginaAtual - 5 < 1 ? 1 : scope.qtdPaginas - 4); i--)
                        scope.arrayPaginas.push(i);
                    scope.arrayPaginas.sort(function sortNumber(a, b) {
                        return a - b;
                    });
                } else {
                    for (var i = scope.paginaAtual - 2; i <= scope.paginaAtual + 2; i++)
                        scope.arrayPaginas.push(i);
                }
            }
        }
    }
});
