var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('loading', function () {
    return {
        restrict: 'E',
        template: '<div ng-if="showLoading" class="loading-div">' +
                    '<div class="spinner">' +
                        '<div class="cube1"></div>' +
                        '<div class="cube2"></div>' +
                    '</div>' +
                  '</div>',
        link: function (scope) {
            var requests = 0;
            scope.$on('REQUEST_SENT', function () {
                scope.showLoading = true;
                requests++;
            });

            scope.$on('RESPONSE_SUCCESS', function () {
                requests--;
                if (requests === 0)
                    scope.showLoading = false;
            });

            scope.$on('RESPONSE_ERROR', function () {
                requests--;
                if (requests === 0)
                    scope.showLoading = false;
            });
        }
    };
});