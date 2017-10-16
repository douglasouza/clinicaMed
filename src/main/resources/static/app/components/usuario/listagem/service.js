var myApp = angular.module('myApp');

myApp.service('usuarioListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/usuario/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function () {
        resource().query().$promise.then(
            function (data) {
                $rootScope.$broadcast('USUARIOS_FETCHED', data);
            }
        );
    };

});