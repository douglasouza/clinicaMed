var clinicaMed = angular.module('clinicaMed');

clinicaMed.filter('enumEspecialidadeMedica', function () {
    return function (input) {
        switch (input) {
            case 'ALERGIA_E_IMUNOLOGIA':
                return 'Alergia e Imunologia';
            case 'CARDIOLOGIA':
                return 'Cardiologia';
            case 'CLINICO_GERAL':
                return 'Clínico Geral';
            case 'DERMATOLOGIA':
                return 'Dermatologia';
            case 'GERIATRIA':
                return 'Geriatria';
            case 'GINECOLOGIA':
                return 'Ginecologia';
            case 'NEUROLOGIA':
                return 'Neurologia';
            case 'OBSTETRICIA':
                return 'Obstetrícia';
            case 'OFTALMOLOGIA':
                return 'Oftalmologia';
            case 'ORTOPEDIA':
                return 'Ortopedia';
            case 'OTORRINOLARINGOLOGIA':
                return 'Otorrinolaringologia';
            case 'PEDIATRIA':
                return 'Pediatria';
            case 'PSIQUIATRIA':
                return 'Psiquiatria';
            case 'REUMATOLOGIA':
                return 'Reumatologia';
            case 'UROLOGIA':
                return 'Urologia';
            default:
                return '--';
        }
    }
});