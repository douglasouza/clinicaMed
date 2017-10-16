var myApp = angular.module('myApp');

myApp.service('usuarioEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/usuario/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getUsuario = function (id) {
        return resource().get({id: id});
    };

    this.saveUsuario = function (usuario) {
        return resource().save(usuario).$promise.then(
            function (data) {
                $rootScope.$broadcast('USUARIO_SAVE_SUCCESS', data);
            },
            function () {
                $rootScope.$broadcast('USUARIO_SAVE_ERROR');
            }
        );
    };

    this.updateUsuario = function (id, usuario) {
        return resource().update({id: id}, usuario).$promise.then(
            function () {
                $rootScope.$broadcast('USUARIO_UPDATE_SUCCESS');
            },
            function () {
                $rootScope.$broadcast('USUARIO_UPDATE_ERROR');
            }
        );
    };

});