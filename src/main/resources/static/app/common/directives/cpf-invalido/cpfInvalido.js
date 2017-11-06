var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('cpfInvalido', function () {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function (scope, elem, attr, ngModel) {
            ngModel.$validators.cpfInvalido = function (modelValue, viewValue) {
                var cpf = modelValue || viewValue;
                var Soma = 0;
                var Resto;

                if (!cpf || cpf.length === 0)
                    return true;

                if (cpf.length !== 11)
                    return false;

                if (cpfContemTodosOsDigitosIguais(cpf))
                    return false;

                for (var i = 1; i <= 9; i++)
                    Soma = Soma + parseInt(cpf.substring(i - 1, i)) * (11 - i);
                Resto = (Soma * 10) % 11;

                if ((Resto == 10) || (Resto == 11))
                    Resto = 0;

                if (Resto != parseInt(cpf.substring(9, 10)))
                    return false;

                Soma = 0;
                for (var i = 1; i <= 10; i++)
                    Soma = Soma + parseInt(cpf.substring(i - 1, i)) * (12 - i);
                Resto = (Soma * 10) % 11;

                if ((Resto == 10) || (Resto == 11))
                    Resto = 0;

                if (Resto != parseInt(cpf.substring(10, 11)))
                    return false;

                return true;
            };

            function cpfContemTodosOsDigitosIguais(cpf) {
                return cpf == '00000000000' || cpf === '11111111111'
                    || cpf === '22222222222' || cpf === '33333333333'
                    || cpf === '44444444444' || cpf === '55555555555'
                    || cpf === '66666666666' || cpf === '77777777777'
                    || cpf === '88888888888' || cpf === '99999999999';
            }
        }
    };
});