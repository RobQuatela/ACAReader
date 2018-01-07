package com.accountomation.acareader.dao;

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

	public static int[] createACA(InputStream file) throws IOException {
		
		InputStreamReader stream = new InputStreamReader(file);
		CSVReader reader = new CSVReader(stream);
		CSVWriter writer = new CSVWriter(Files.newBufferedWriter(
				Paths.get("C:/Users/Rob Quatela/Desktop/ACA Series Codes Import.csv")),
				CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER,
				CSVWriter.DEFAULT_LINE_END
				);
		List<String[]> csv = reader.readAll();
		List<String[]> writtenLines = new ArrayList<>();
		
		writtenLines.add(new String[] {
				"Employee Identifier",
				" ",
				"Date (YYYMM Format - Leaving month blank applies to all current year)",
				" ",
				"Series Code 1 (1A - 1I)",
				"Employee Share (Up to 2 decimals)",
				"Series Code 2 (2A - 2I)",
				"Enrolled in self coverage (1 - checked 0 - not checked)"
		});

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
					String[] formatter = nextLine[0].split("\\(");
					String name = formatter[1].substring(0, 4);
					entry.getValue().setDate(new DateMap(entry.getKey()));
					entry.getValue().setEmp(new Employee(name));
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
					entry.getValue().setEnrolledInSelf("0");
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
							" ",
							String.valueOf(entry.getValue().getDate().getId()),
							entry.getValue().getSeries1() + "|"
									+ entry.getValue().getEmpShare() + "|"
									+ entry.getValue().getSeries2() + "|"
									+ entry.getValue().getEnrolledInSelf(),
							entry.getValue().getSeries1(),
							entry.getValue().getEmpShare(),
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
		int[] processedLines = new int[] {writtenLines.size(), i};
		
		return processedLines;
	}
}
