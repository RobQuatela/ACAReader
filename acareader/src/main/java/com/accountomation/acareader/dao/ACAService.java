package com.accountomation.acareader.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.accountomation.acareader.model.ACAStatus;
import com.accountomation.acareader.model.DateMap;
import com.accountomation.acareader.model.Employee;
import com.opencsv.CSVReader;

public class ACAService {

	@SuppressWarnings("deprecation")
	public static void createACA(InputStream file) throws IOException {
		
		for(int i = 0; i < 946; i += 5 ) {
			Employee emp = new Employee();
			List<DateMap> dates = new ArrayList<>();
			dates.add(new DateMap(201701));
			dates.add(new DateMap(201702));
			dates.add(new DateMap(201703));
			dates.add(new DateMap(201704));
			dates.add(new DateMap(201705));
			dates.add(new DateMap(201706));
			dates.add(new DateMap(201707));
			dates.add(new DateMap(201708));
			dates.add(new DateMap(201709));
			dates.add(new DateMap(201710));
			dates.add(new DateMap(201711));
			dates.add(new DateMap(201712));
			List<ACAStatus> status = new ArrayList<>();
			ACAStatus s = new ACAStatus();
			try (CSVReader reader = new CSVReader(new InputStreamReader(file), ';', '\'', i)) {
				String[] nextLine;
				nextLine = reader.readNext();
				emp.setId(nextLine[0]);
				s.setSeries1(nextLine[5]);
				reader.readNext();
				//s.setempshare(nextline)
				reader.readNext();
				//s.setseries2(nextline)
				reader.readNext();
				//ss.setenrolled(nextLine)
				reader.readNext();
				//s.setentity(nextline)		
			}
			
			for(DateMap date : dates) {
				//status.setdate(date)
				status.add(s);
			}
			
			for(ACAStatus stat : status) {
				//insert each one
			}
		}
	}
}
