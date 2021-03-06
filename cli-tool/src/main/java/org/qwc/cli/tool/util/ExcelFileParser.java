package org.qwc.cli.tool.util;

import org.apache.poi.ss.usermodel.*;
import org.qwc.cli.tool.dao.SIEntity;
import org.qwc.cli.tool.service.FebService.Feb;
import org.qwc.cli.tool.dao.FebEntity;
import org.qwc.cli.tool.service.SIService.SI;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelFileParser {


    public static List<Feb> parseFeb(String filename){
        Workbook workbook = null;
        List<Feb> febList = new ArrayList<>();
        try {
            workbook = WorkbookFactory.create(new File(filename));

        }catch (IOException e){
            System.out.println(e.getMessage());
            return febList;
        }


        Sheet sheet = workbook.getSheet("Feb20 Acct");
        DataFormatter formatter = new DataFormatter();
        
        int[] colList = new int[9];
        boolean headerRow=true;
        for(Row row : sheet){
            int lastColumn = row.getLastCellNum();
            if(headerRow){
                for (int col = 0; col<lastColumn; col++){
                    Cell cell = row.getCell(col, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String text = formatter.formatCellValue(cell);

                    switch (text){
                        case "MSISDN":
                            colList[0] = col;
                            break;
                        case "BRN":
                            colList[1] = col;
                            break;
                        case "CUST_FULL_NAME":
                            colList[2] = col;
                            break;
                        case "ACCT_LVL_CMPNT_ID":
                            colList[3] = col;
                            break;
                        case "ACCR_LVL_CMPNT_DESC":
                            colList[4] = col;
                            break;
                        case "BILL_ACCT_NO":
                            colList[5] = col;
                            break;
                        case "CUST_CLASFN_DESC":
                            colList[6] = col;
                            break;
                        case "Type":
                            colList[7] = col;
                            break;
                        case "Start Date":
                            colList[8] = col;
                            break;
                    }

                }

                            
            }else{
                Feb feb = new Feb();

                for (int col = 0;col<colList.length;col++){
                    Cell cell = row.getCell(colList[col], Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if(cell==null){
                        continue;
                    }
                    String text = formatter.formatCellValue(cell);
                    
                    
                    switch(colList[col]){
                        case 0:
                            feb.setMsisdn(text.trim());
                            break;
                        case 1:
                            feb.setBrn(text);
                            break;
                        case 2:
                            feb.setCustFullName(text);
                            break;
                        case 3:
                            feb.setAcctLvlCmpntId(text);
                            break;
                        case 4:
                            feb.setAcctLvlCmpntDesc(text);
                            break;
                        case 5:
                            feb.setBillAcctNo(text);
                            break;
                        case 6:
                            feb.setCustClasfnDesc(text);
                            break;
                        case 7:
                            feb.setType(text);
                            break;
                        case 8:
                            feb.setStartDate(text);
                            break;


                    }

                }
                if (feb.getMsisdn()!=null) {
                    febList.add(feb);
                }
            }
            headerRow=false;
        }
        return febList;
    }


    public static List<SI> parseSI(String filename){

        Workbook workbook = null;
        List<SI> siList = new ArrayList<>();
        try {
            workbook = WorkbookFactory.create(new File(filename));

        }catch (IOException e){
            System.out.println(e.getMessage());
            return siList;
        }


        Sheet sheet = workbook.getSheet("SI level");
        DataFormatter formatter = new DataFormatter();

        int[] colList = new int[8];
        boolean headerRow=true;
        for(Row row : sheet){
            int lastColumn = row.getLastCellNum();
            if(headerRow){
                for (int col = 0; col<lastColumn; col++){
                    Cell cell = row.getCell(col, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String text = formatter.formatCellValue(cell);

                    switch (text){
                        case "MSISDN":
                            colList[0] = col;
                            break;
                        case "BRN":
                            colList[1] = col;
                            break;
                        case "CUST_FULL_NAME":
                            colList[2] = col;
                            break;
                        case "SI_LVL_CMPNT_ID":
                            colList[3] = col;
                            break;
                        case "CMPNT_DESC":
                            colList[4] = col;
                            break;
                        case "BILL_ACCT_NO":
                            colList[5] = col;
                            break;
                        case "CUST_CLASFN_DESC":
                            colList[6] = col;
                            break;
                        case "Type":
                            colList[7] = col;
                            break;
                    }

                }


            }else {
                SI si = new SI();

                for (int col = 0; col < colList.length; col++) {
                    Cell cell = row.getCell(colList[col], Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (cell == null) {
                        continue;
                    }
                    String text = formatter.formatCellValue(cell);


                    switch (colList[col]) {
                        case 0:
                            si.setMsisdn(text.trim());
                            break;
                        case 1:
                            si.setBrn(text);
                            break;
                        case 2:
                            si.setCustFullName(text);
                            break;
                        case 3:
                            si.setSiLvlCmpntId(text);
                            break;
                        case 4:
                            si.setSiCmpntDesc(text);
                            break;
                        case 5:
                            si.setBillAcctNo(text);
                            break;
                        case 6:
                            si.setCustClasfnDesc(text);
                            break;
                        case 7:
                            si.setType(text);
                            break;
                    }


                }
                if (si.getMsisdn() != null) {
                    siList.add(si);
                }
            }
            headerRow=false;
        }
        return siList;
    }

}
