package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import stockanalyzer.ctrl.Controller;
import stockanalyzer.exception.YException;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		try {
			ctrl.process("ACN");
		} catch (YException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl2(){
		try {
			ctrl.process("DXC");
		} catch (YException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl3(){
		try {
			ctrl.process("AAPL");
		} catch (YException e) {
			e.printStackTrace();
		}
	}
	public void getDataFromCtrl4(){
		List tickers = new ArrayList<String>();
		tickers.add("APPL");
		tickers.add("DXC");
		tickers.add("ACN");
		try {
			ctrl.seqDownloadTickers(tickers);
		} catch (YException e) {
			e.printStackTrace();
		}
	}
	public void getDataFromCtrl5(){
		List tickers = new ArrayList<String>();
		tickers.add("APPL");
		tickers.add("DXC");
		tickers.add("ACN");
		try {
			ctrl.parallelDownloadTickers(tickers);
		} catch (YException e) {
			e.printStackTrace();
		}
	}



	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice ACN", this::getDataFromCtrl1);
		menu.insert("b", "Choice DXC", this::getDataFromCtrl2);
		menu.insert("c", "Choice APPL", this::getDataFromCtrl3);
		menu.insert("d", "Choice Download tickers Seq", this::getDataFromCtrl4);
		menu.insert("e", "Choice Download tickers Parallel", this::getDataFromCtrl5);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		ctrl.closeConnection();
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
