package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import models.entities.Contract;
import models.services.ContractService;
import models.services.PaypalService;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter contract data: ");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		sc.nextLine();
		LocalDate date = LocalDate.parse(sc.nextLine(),fmt);
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		
		Contract obj = new Contract(number, date, totalValue);
		
		ContractService service = new ContractService(new PaypalService());
		
		System.out.println("Enter the number of installments: ");
		int n = sc.nextInt();
		
		service.processContract(obj,n);
		
		System.out.println("Installments:");
		obj.printInstallment();
		
		System.out.println();
		System.out.print("Enter the path: ");
		sc.nextLine();
		String path = sc.nextLine();
		
		obj.writeSummary(path);
		
		sc.close();
	}

}
