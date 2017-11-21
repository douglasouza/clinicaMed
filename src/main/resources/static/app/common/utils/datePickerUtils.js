var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('datePickerUtils',
    ['$filter', 'jQuery', function ($filter, $) {

        this.config = function (options, scope) {
            var elemento = $(options.idElemento);
            elemento.datetimepicker();
            elemento.data('DateTimePicker').locale('pt-br');
            elemento.data('DateTimePicker').format('DD/MM/YYYY');
            elemento.data('DateTimePicker').useCurrent(false);

            if (options.dataMinima)
                elemento.data('DateTimePicker').minDate(options.dataMinima);

            if (options.fdsDisabled)
                elemento.data('DateTimePicker').daysOfWeekDisabled([0, 6]);

            elemento.on('dp.change', function (event) {
                if (options.model.indexOf('.') === -1) {
                    scope[options.model] = $filter('date')(event.date._d, 'dd/MM/yyyy');
                } else {
                    var caminhoModel = options.model.split('.');
                    scope[caminhoModel[0]][caminhoModel[1]] = $filter('date')(event.date._d, 'dd/MM/yyyy');
                }
                scope.$digest();
            });
        };
    }]
);