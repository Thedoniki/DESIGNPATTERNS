package com.example.demo2.Repository;

import Interfaces.UserSaverStrategy;
import com.example.demo2.Models.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;



public class ExcelSaverStrategy implements UserSaverStrategy {



    public List<User>  showusers () {
        FileInputStream fStream;
        XSSFWorkbook workbook;
        List<User> usersList = new ArrayList<>(); //DTO NAME OF CLASS
        try{
            fStream = new FileInputStream(new File("test2.xlsx"));
            workbook = new XSSFWorkbook(fStream);
            XSSFSheet worksheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = worksheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();


                String name = cellIterator.next().getStringCellValue();
                String email = cellIterator.next().getStringCellValue();
                String password = cellIterator.next().getStringCellValue();

                User user = new User(name, email, password);
                usersList.add(user);

            }
            return usersList;
        }catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return usersList;
    }

        public void saveusers(User user){

        FileInputStream fStream;
        XSSFWorkbook workbook;

        try{
            fStream = new FileInputStream(new File("test2.xlsx"));
            workbook = new XSSFWorkbook(fStream);
            XSSFSheet worksheet = workbook.getSheetAt(0);
            XSSFRow newRow=worksheet.createRow(worksheet.getLastRowNum()+1);
            XSSFCell name=newRow.createCell(0);
            XSSFCell email=newRow.createCell(1);
            XSSFCell password=newRow.createCell(2);
            name.setCellValue(user.getName());
            email.setCellValue(user.getEmail());
            password.setCellValue(user.getPassword());

            fStream.close();

            FileOutputStream foStream = new FileOutputStream(new File("test2.xlsx"));
            workbook.write(foStream);
            foStream.close();

        }catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void updateusers (User user) {
        FileInputStream fStream;
        XSSFWorkbook workbook;
        try {
            fStream = new FileInputStream(new File("test2.xlsx"));
            workbook = new XSSFWorkbook(fStream);
            XSSFSheet worksheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = worksheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();

                if (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    if (nextCell.getStringCellValue().equals(user.getName())){
                        nextCell = cellIterator.next();
                        nextCell.setCellValue(user.getEmail());
                        nextCell = cellIterator.next();
                        nextCell.setCellValue(user.getPassword());
                        break;

                    }

                }
            }

            fStream.close();
            FileOutputStream foStream = new FileOutputStream(new File("test2.xlsx"));
            workbook.write(foStream);
            foStream.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        }


    @Override
    public void update(Observable o, Object arg) {
        User user = (User) arg;
       updateusers(user);
    }
}