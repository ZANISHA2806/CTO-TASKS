package util;
import java.io.*;
import model.Transaction;
import java.util.*;
public class Fileutil {

	public static void writecredits(List<Transaction> credits,String filepath) {
		try(BufferedWriter bw=new BufferedWriter(new FileWriter(filepath))){
			for(Transaction tnx: credits) {
				String line=tnx.getTid()+" | "+tnx.getAcc()+" | "+tnx.getType()+" | "+tnx.getAmount()+" | "+tnx.getDate();
				
				bw.write(line);
				bw.newLine();
			}
		}
		catch(IOException e) {
			System.out.println("writing file"+e.getMessage());
		}
	}
}
