var clinicaMed = angular.module('clinicaMed');

clinicaMed.constant('constants', function () {
    return {
        ENUM: {
            ESPECIALIDADE_MEDICA: [
                {id: 'ALERGIA_E_IMUNOLOGIA', value: 'Alergia e Imunologia'},
                {id: 'CARDIOLOGIA', value: 'Cardiologia'},
                {id: 'CLINICO_GERAL', value: 'Clínico Geral'},
                {id: 'DERMATOLOGIA', value: 'Dermatologia'},
                {id: 'GERIATRIA', value: 'Geriatria'},
                {id: 'GINECOLOGIA', value: 'Ginecologia'},
                {id: 'NEUROLOGIA', value: 'Neurologia'},
                {id: 'OBSTETRICIA', value: 'Obstetricia'},
                {id: 'OFTALMOLOGIA', value: 'Oftalmologia'},
                {id: 'ORTOPEDIA', value: 'Ortopedia'},
                {id: 'OTORRINOLARINGOLOGIA', value: 'Otorrinolaringologia'},
                {id: 'PEDIATRIA', value: 'Pediatria'},
                {id: 'PSIQUIATRIA', value: 'Psiquiatria'},
                {id: 'REUMATOLOGIA', value: 'Reumatologia'},
                {id: 'UROLOGIA', value: 'Urologia'}
            ]
        },
        ACOES: {
            NOVO: 'novo',
            VISUALIZAR: 'visualizar',
            EDITAR: 'editar'
        }
    };
});