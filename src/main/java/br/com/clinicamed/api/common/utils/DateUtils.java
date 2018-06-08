package br.com.clinicamed.api.common.utils;

import br.com.clinicamed.api.modules.consulta.horarioconsulta.Horario;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static Date getInicioDoDia(Date data) {
        Calendar inicioDoDia = new GregorianCalendar();
        inicioDoDia.setTime(data);
        inicioDoDia.add(Calendar.DATE, -1);
        inicioDoDia.set(Calendar.HOUR_OF_DAY, 23);
        inicioDoDia.set(Calendar.MINUTE, 59);
        inicioDoDia.set(Calendar.SECOND, 59);
        inicioDoDia.set(Calendar.MILLISECOND, 0);
        return inicioDoDia.getTime();
    }

    public static Date getFinalDoDia(Date data) {
        Calendar fimDoDia = new GregorianCalendar();
        fimDoDia.setTime(data);
        fimDoDia.add(Calendar.DATE, 1);
        fimDoDia.set(Calendar.HOUR_OF_DAY, 0);
        fimDoDia.set(Calendar.MINUTE, 0);
        fimDoDia.set(Calendar.SECOND, 0);
        fimDoDia.set(Calendar.MILLISECOND, 0);
        return fimDoDia.getTime();
    }

    public static Date getDataHoraConsulta(Date dataConsulta, Horario horaConsulta) {
//        Calendar dataHoraConsulta = new GregorianCalendar();
//        dataHoraConsulta.add(Calendar.LONG, (int) horaConsulta.getHoraConsulta().getTime());
//        dataHoraConsulta.setTime(dataConsulta);
//        dataHoraConsulta.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaConsulta.getHoraConsulta().substring(0, horaConsulta.getHoraConsulta().indexOf(":"))));
//        dataHoraConsulta.set(Calendar.MINUTE, 0);
//        dataHoraConsulta.set(Calendar.SECOND, 0);
//        dataHoraConsulta.set(Calendar.MILLISECOND, 0);
        return new Date(dataConsulta.getTime() + horaConsulta.getHoraConsulta().getTime());
    }
}
