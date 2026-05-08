package main;
import java.io.*;
import service.Transactionservice;
import parser.Transactionparser;
import model.Transaction;
import model.Transactiontype;
public class Main {

	public static void main(String[] args) {
		String inpath="src/sampledata/input.txt";
		
		String outpath="src/sampledata/credit_only";
		
		Transactionservice service=new Transactionservice();
		service.fileprocessing(inpath, outpath);
	}
	}

