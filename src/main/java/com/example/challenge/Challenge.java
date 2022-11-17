package com.example.challenge;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import org.springframework.stereotype.Service;


public class Challenge {
    //table link: https://docs.google.com/spreadsheets/d/1V7vOXGjAMb0BNPdodEsUhhFsyn29Us-Z_K2s2Nt5znc/edit?usp=sharing

    Workbook wb = new Workbook("https://docs.google.com/spreadsheets/d/1V7vOXGjAMb0BNPdodEsUhhFsyn29Us-Z_K2s2Nt5znc/edit?usp=sharing");
    WorksheetCollection collection = wb.getWorksheets();

    Worksheet worksheet = collection.get(0);

    int rows = worksheet.getCells().getMaxDataRow();
    int cols = worksheet.getCells().getMaxDataColumn();

    for (int i = 0; i < rows; i++) {

        // Percorrer cada coluna na linha selecionada
        for (int j = 0; j < cols; j++) {
            // Valor da célula de Pring
            System.out.print(worksheet.getCells().get(i, j).getValue() + " | ");
        }
    }

    public Challenge() throws Exception {
    }
}
