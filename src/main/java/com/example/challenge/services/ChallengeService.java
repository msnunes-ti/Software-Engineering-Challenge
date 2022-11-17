package com.example.challenge.services;

import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    public void calculateAverage() throws Exception {

        //table link: https://docs.google.com/spreadsheets/d/1V7vOXGjAMb0BNPdodEsUhhFsyn29Us-Z_K2s2Nt5znc/edit?usp=sharing
        Workbook wb = new Workbook("https://docs.google.com/spreadsheets/d/1V7vOXGjAMb0BNPdodEsUhhFsyn29Us-Z_K2s2Nt5znc/edit?usp=sharing");
        Worksheet worksheet = wb.getWorksheets().get(0);
        Cell cell = worksheet.getCells().get(1, 0);
        double totalClasses = cell.getDoubleValue();
        int rows = worksheet.getCells().getMaxDataRow();
        int cols = worksheet.getCells().getMaxDataColumn();
        // loop through the lines
        for (int i = 3; i < rows; i++) {
            // Loop through each column in the selected row
            for (int j = 0; j < cols; j++) {
                int absences = 0;
                int p1 = 0;
                int p2 = 0;
                int p3 = 0;
                if (j == 2) absences = (int) worksheet.getCells().get(i, j).getValue();
                if (j == 3) p1 = (int) worksheet.getCells().get(i, j).getValue();
                if (j == 4) p2 = (int) worksheet.getCells().get(i, j).getValue();
                if (j == 5) p3 = (int) worksheet.getCells().get(i, j).getValue();
                if (j == 6) {
                    double average  = calculateAverage(p1, p2, p3);
                    if (average < 50) {
                        worksheet.setActiveCell("Reprovado por Nota");
                    }
                    if (average >= 70) {
                        worksheet.setActiveCell("Aprovado");
                    }
                    if (50 <= average && average < 70) {
                        worksheet.setActiveCell("Exame Final");
                    }
                    if (isFailedForAbsences(absences, totalClasses)) worksheet.setActiveCell("Reprovado por Nota");
                }
            }
        }
    }

    public Boolean isFailedForAbsences(Integer absences, double totalClasses) {
        return (totalClasses / absences) > (totalClasses * 0.25);
    }

    public Double calculateAverage (int p1, int p2, int p3) {
        return (double) Math.round((double) (p1 + p2 + p3) / 3);
    }
}
