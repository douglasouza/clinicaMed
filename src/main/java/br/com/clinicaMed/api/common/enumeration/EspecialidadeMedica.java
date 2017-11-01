package br.com.clinicaMed.api.common.enumeration;

public enum EspecialidadeMedica {
    ALERGIA_E_IMUNOLOGIA("Alergia e Imunologia"),
    CARDIOLOGIA("Cardiologia"),
    CLINICO_GERAL("Clínico Geral"),
    DERMATOLOGIA("Dermatologia"),
    GERIATRIA("Geriatria"),
    GINECOLOGIA("Ginecologia"),
    NEUROLOGIA("Neurologia"),
    OBSTETRICIA("Obstetricia"),
    OFTALMOLOGIA("Oftalmologia"),
    ORTOPEDIA("Ortopedia"),
    OTORRINOLARINGOLOGIA("Otorrinolaringologia"),
    PEDIATRIA("Pediatria"),
    PSIQUIATRIA("Psiquiatria"),
    REUMATOLOGIA("Reumatologia"),
    UROLOGIA("Urologia");

    private String especialidadeMedica;

    EspecialidadeMedica(String especialidadeMedica) {
        this.especialidadeMedica = especialidadeMedica;
    }

    @Override
    public String toString() {
        return especialidadeMedica;
    }
}
