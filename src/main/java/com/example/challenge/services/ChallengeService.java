package com.example.challenge.services;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class ChallengeService {

    public void calculateAverage() throws Exception {

        //table link: https://docs.google.com/spreadsheets/d/1V7vOXGjAMb0BNPdodEsUhhFsyn29Us-Z_K2s2Nt5znc/edit?usp=share_link
        Workbook workbook = new Workbook("c:/Engenharia de Software - Desafio - Marcelo da Silva Nunes.xlsx");
        Worksheet worksheet = workbook.getWorksheets().get(0);
        String text = String.valueOf(worksheet.getCells().get(1, 0));
        int totalClasses = searchNumInteger(text);
        int rows = worksheet.getCells().getMaxDataRow();
        int cols = worksheet.getCells().getMaxDataColumn();
        int absences = 0;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        // loop through the lines
        for (int i = 3; i <= rows; i++) {
            // Loop through each column in the selected row
            for (int j = 0; j < cols; j++) {
                Integer average = calculateAverage(p1, p2, p3);
                if (j == 2) absences = worksheet.getCells().get(i, j).getIntValue();
                if (j == 3) p1 = worksheet.getCells().get(i, j).getIntValue();
                if (j == 4) p2 = worksheet.getCells().get(i, j).getIntValue();
                if (j == 5) p3 = worksheet.getCells().get(i, j).getIntValue();
                if (j == 6) {
                    if (average < 5) {
                        worksheet.getCells().get(i, j).putValue("Reprovado por Nota");
                        worksheet.getCells().get(i, (j + 1)).putValue("Nota necessária: 0 ");
                    }
                    if (average >= 7) {
                        worksheet.getCells().get(i, j).putValue("Aprovado");
                        worksheet.getCells().get(i, (j + 1)).putValue("Nota necessária: 0 ");
                    }
                    if (5 <= average && average < 7) {
                        worksheet.getCells().get(i, j).putValue("Exame Final");
                        worksheet.getCells().get(i, (j + 1)).putValue("Nota necessária: " + (10 - average));
                    }
                    if (isFailedForAbsences(absences, totalClasses)) {
                        worksheet.getCells().get(i, j).putValue("Reprovado por Falta");
                        worksheet.getCells().get(i, (j + 1)).putValue("Nota necessária: 0 ");
                    }
                }
            }
        }
        workbook.save("c:/Engenharia de Software - Desafio - Marcelo da Silva Nunes.xlsx");
    }

    public Boolean isFailedForAbsences(int absences, int totalClasses) {
        return absences > (totalClasses * 0.25);
    }

    public Integer calculateAverage (int p1, int p2, int p3) {
        return (p1 + p2 + p3) / 30;
    }

    private Integer searchNumInteger(String text) {
        String result = "";
        for( int i = 52; i < text.length(); i++ ) {
            Integer aux;
            try {
                aux = Integer.parseInt(String.valueOf(text.charAt(i)));
            } catch( Exception e ) {
                aux = null;
            }
            if( aux != null ) {
                result = result.concat(String.valueOf( aux ));
            }
        }
        return Integer.parseInt(result);
    }
}
