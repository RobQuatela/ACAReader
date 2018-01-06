package com.accountomation.acareader.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.accountomation.acareader.model.ACAStatus;
import com.accountomation.acareader.model.DateMap;
import com.accountomation.acareader.model.Employee;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ACAService {

	public static void createACA(InputStream file) throws IOException {
		
		InputStreamReader stream = new InputStreamReader(file);
		CSVReader reader = new CSVReader(stream);
		CSVWriter writer = new CSVWriter(Files.newBufferedWriter(Paths.get("C:/Users/Rob Quatela/Desktop/test.csv")),
				CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER,
				CSVWriter.DEFAULT_LINE_END
				);
		List<String[]> csv = reader.readAll();
		List<String[]> writtenLines = new ArrayList<>();
		//for(int i = 5; i < csv.size(); i += 5 ) {
		int i = 0;
		while(i < csv.size()) {
			Employee emp = new Employee();
			LinkedHashMap<Integer, ACAStatus> statuses = new LinkedHashMap<>();
			for(int t = 201701; t < 201713; t++)
				statuses.put(t, new ACAStatus());

			//CSVReader reader = new CSVReaderBuilder(stream).withSkipLines(i).build();
				String[] nextLine;
				nextLine = csv.get(i);
				emp.setId(nextLine[0]);
				int counter = 0;
				
				for(Entry<Integer, ACAStatus> entry : statuses.entrySet()) {
					entry.getValue().setDate(new DateMap(entry.getKey()));
					entry.getValue().setEmp(new Employee(nextLine[0]));
					entry.getValue().setSeries1(nextLine[counter + 6]);
					counter++;
				}
				
				counter = 0;
				nextLine = csv.get(i + 1);
				for(Entry<Integer, ACAStatus> entry : statuses.entrySet()) {
					entry.getValue().setEmpShare(nextLine[counter + 6]);
					counter++;
				}
				
				counter = 0;
				nextLine = csv.get(i + 2);
				for(Entry<Integer, ACAStatus> entry : statuses.entrySet()) {
					entry.getValue().setSeries2(nextLine[counter + 6]);
					counter++;
				}
				
				counter = 0;
				nextLine = csv.get(i + 3);
				for(Entry<Integer, ACAStatus> entry : statuses.entrySet()) {
					entry.getValue().setEnrolledInSelf(nextLine[counter + 6]);
					counter++;
				}
				
				counter = 0;
				nextLine = csv.get(i + 4);
				for(Entry<Integer, ACAStatus> entry : statuses.entrySet()) {
					entry.getValue().setEntity(nextLine[counter + 6]);
					counter++;
				}
				
				for(Entry<Integer, ACAStatus> entry : statuses.entrySet()) {
					System.out.println("Emp: " + entry.getValue().getEmp().getId() + 
							" Month: " + entry.getValue().getDate().getId() +
							" Series1: " + entry.getValue().getSeries1() +
							" Series2: " + entry.getValue().getSeries2() +
							" Empshare: " + entry.getValue().getEmpShare() +
							" enrolled: " + entry.getValue().getEnrolledInSelf() +
							" entity: " + entry.getValue().getEntity());
					writtenLines.add(new String[] {
							entry.getValue().getEmp().getId(),
							String.valueOf(entry.getValue().getDate().getId()),
							entry.getValue().getSeries1(),
							entry.getValue().getEmpShare(),
							entry.getValue().getEnrolledInSelf(),
							entry.getValue().getSeries2(),
							entry.getValue().getEnrolledInSelf()
					});
				}
			
				
			i += 5;
			
		}
		
		reader.close();
		for(String[] line : writtenLines)
			writer.writeNext(line);
		writer.close();
	}
}
