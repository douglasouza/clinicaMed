var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('tabelaListagem', function () {
    return {
        scope: {
            registros: '=',
            colunas: '=',
            acaoEditar: '&',
            acaoExcluir: '&',
            paginaAtual: '='
        },
        templateUrl: './app/common/directives/tabela-listagem/template.html',
        link: function (scope) {
            scope.isNumRegistroMaiorZero = function () {
                if (scope.registros)
                    return scope.registros.length > 0;
            };

            scope.$watch('paginaAtual', function (novoValor) {
                scope.paginaAtual = novoValor;
                if (scope.paginaAtual && scope.registros)
                    getRegistrosPaginaAtual();
            });

            scope.$watch('registros', function (novoValor) {
                scope.registros = novoValor;
                if (scope.registros)
                    getRegistrosPaginaAtual();
            });

            function getRegistrosPaginaAtual() {
                var inicio = 1 + ((scope.paginaAtual - 1) * 10);
                var fim = scope.registros.length > 10 ? 10 + ((scope.paginaAtual - 1) * 10) : scope.registros.length;
                scope.registrosPagAtual = scope.registros.slice(inicio - 1, fim);
            }
        }
    }
});
