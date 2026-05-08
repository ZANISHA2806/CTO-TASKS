package service;

import java.io.*;
import java.util.*;

import model.Transaction;
import model.Transactiontype;
import parser.Transactionparser;

public class Transactionservice {

    public void fileprocessing(String inputpath, String outputpath) {

        int totallines = 0;
        double totalcredit = 0;
        double totaldebit = 0;

        List<Transaction> creditList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputpath))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Transaction tnx = Transactionparser.parse(line);

                    if (tnx == null) continue;

                    totallines++;

                    if (tnx.getType() == Transactiontype.CREDIT) {
                        totalcredit += tnx.getAmount();
                        creditList.add(tnx);
                    } else {
                        totaldebit += tnx.getAmount();
                    }

                } catch (Exception e) {
                    System.out.println("Error processing line: " + line);
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputpath))) {
                for (Transaction t : creditList) {
                    bw.write(t.toString());
                    bw.newLine();
                }
            }

            System.out.println("total lines:"+totallines); 
            System.out.println("total credit:"+totalcredit);
            System.out.println("total debit:"+totaldebit);
            System.out.println("credits_only.txt written with " + creditList.size() + " lines.");

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + inputpath);
        } catch (IOException e) {
            System.out.println("Error reading or writing file.");
        }
    }
}