package receipt;
import java.util.Scanner;
class Transaction{
	private String ID;
	private String name;
	private Double amount;
	
	Transaction(String ID,String name,Double amount){
		this.ID=ID;
		this.name=name;
		this.amount=amount;
	}
	
	private Double processingfee() {
		Double calculatedfee=0.025*amount;
		return calculatedfee;
		
		
		
	}
	private Double totalfee() {
		Double total=amount+processingfee();
		return total;
	}
	
	public void receipt() {
		Double cal=processingfee();
		Double tot=totalfee();
		System.out.println("Merchant ID: "+ID+"\nMerchant name :"+name+"\nAmount: "+amount+"\nProcessing fee: "+cal+"\nTotal Amount: "+tot);
	}
}
public class Transaction_Receipt {
public static void main(String[] args) {
	Scanner scan=new Scanner(System.in);
	String ID=scan.nextLine();
	String name=scan.nextLine();
	Double amt=scan.nextDouble();
	scan.nextLine();
	Transaction trans=new Transaction(ID,name,amt);
	trans.receipt();
	scan.close();
}
}
