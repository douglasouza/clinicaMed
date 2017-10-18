var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('usuarioListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/usuario/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function () {
        resource().query().$promise.then(
            function (data) {
                $rootScope.$broadcast('USUARIOS_FETCHED_SUCCESS', data);
            },
            function () {
                $rootScope.$broadcast('USUARIOS_FETCHED_ERROR');
            }
        );
    };

    this.deleteUsuario = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('USUARIO_DELETE_SUCCESS');
            },
            function () {
                $rootScope.$broadcast('USUARIO_DELETE_ERROR');
            }
        );
    };

});