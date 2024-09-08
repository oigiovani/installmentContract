package models.entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Contract {
	private Integer number;
	private LocalDate date;
	private Double totalValue;
	List<Installment> list = new ArrayList<>();
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Contract(Integer number, LocalDate date, Double totalValue) {
		this.number = number;
		this.date = date;
		this.totalValue = totalValue;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}
	
	public void addList(LocalDate dueDate, Double amount) {
		list.add(new Installment(dueDate,amount));
	}
	
	public int getListSize() {
		return list.size();
	}
	
	public void printInstallment() {
		for(Installment item: list) {
			System.out.println(item.getDueDate().format(fmt) + " - " + String.format("%.2f", item.getAmount()));
		}
	}

	public void writeSummary(String path) {
		
		try(BufferedWriter bw = new BufferedWriter (new FileWriter(path + "\\Summary.txt"))){
			
			bw.write("Dados do contrato:");
			bw.newLine();
			bw.write("Numero: ");
			bw.write(getNumber().toString());
			bw.newLine();
			bw.write("Data: ");
			bw.write(getDate().format(fmt));
			bw.newLine();
			bw.write("Valor do contrato:");
			bw.write(String.format("%.2f", getTotalValue()));
			bw.newLine();
			bw.write("Parcelas:");
			bw.newLine();
			
			for(Installment item: list) {
				bw.write(item.getDueDate().format(fmt) + " - " + String.format("%.2f", item.getAmount()));
				bw.newLine();
			}
			
			System.out.println();
			System.out.println("Summary generated.");
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
				
	}
	
}
