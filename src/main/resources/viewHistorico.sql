SELECT HISTORICO.*
FROM (
       SELECT
         PA_ID             AS PA_ID,
         PA_DT_HR_CADASTRO AS DT_HR,
         NULL              AS HORA,
         'PACIENTE'        AS TIPO
       FROM PACIENTE

       UNION

       SELECT
         PA_ID                  AS PA_ID,
         SE_DT_HR_CADASTRO      AS DT_HR,
         NULL                   AS HORA,
         'SOLIC_EXAME_CADASTRO' AS TIPO
       FROM SOLICITACAO_EXAME

       UNION

       SELECT
         PA_ID                  AS PA_ID,
         SE_DT_HR_ENTREGUE      AS DT_HR,
         NULL                   AS HORA,
         'SOLIC_EXAME_ENTREGUE' AS TIPO
       FROM SOLICITACAO_EXAME

       UNION

       SELECT
         CO.PA_ID            AS PA_ID,
         CO.CO_DATA_CONSULTA + PARSEDATETIME(HO.HO_HORA_CONSULTA, 'HH:MM') AS HORA,
         'CONSULTA'          AS TIPO
       FROM CONSULTA CO
         JOIN HORARIO HO ON CO.CO_ID = HO.HO_ID
     ) HISTORICO
ORDER BY HISTORICO.DT_HR DESC