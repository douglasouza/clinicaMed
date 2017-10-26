var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('loginController',
    ['$scope', '$stateParams', function ($scope, $stateParams) {
        $scope.error = $stateParams.error;
    }]
);