var myApp = angular.module('myApp');

myApp.config(function ($stateProvider) {

    $stateProvider.state('usuario.listagem', {
        url: '/listagem',
        templateUrl: './app/components/usuario/listagem/view.html',
        controller: 'usuarioListagemController'
    });

});