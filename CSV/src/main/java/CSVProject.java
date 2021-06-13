import java.io.*;
import java.util.*;

//Used this link to get a CSV reader API: https://www.youtube.com/watch?v=U4xcgda32us

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;


public class CSVProject {

    public static HashMap<String, List<Customer>> hashMap;

    public static void main(String[] args) throws IOException
    {
        String fileName = "OriginalCSV.csv";
        hashMap = new HashMap<String, List<Customer>>();

        Reader inputFile = new FileReader(fileName);

        CSVParser records = new CSVParser(inputFile,CSVFormat.DEFAULT.withHeader("userID", "fName", "lName", "versionNumber", "insuranceCompany").withIgnoreHeaderCase());

        //Use API to parse by column
        for (CSVRecord record : records)
        {
            boolean newCustomer = true;
            String userID = record.get("userID");
            String fName = record.get("fName");
            String lName = record.get("lName");
            int versionNumber = Integer.parseInt(record.get("versionNumber"));
            String insuranceCompany = record.get("insuranceCompany");

            //Set values
            Customer newClient = new Customer();
            newClient.setUserID(userID);
            newClient.setFirstName(fName);
            newClient.setLastName(lName);
            newClient.setVersionNumber(versionNumber);
            newClient.setInsuranceCompany(insuranceCompany);


            //Check for pre-existing insurance company
            if (hashMap.containsKey(insuranceCompany))
            {
                for (int i = 0; i < hashMap.get(insuranceCompany).size(); i++)
                {
                    if (hashMap.get(insuranceCompany).get(i).getUserID().equals(newClient.getUserID()))
                    {
                        if (hashMap.get(insuranceCompany).get(i).getVersion() < newClient.getVersion())
                        {
                            hashMap.get(insuranceCompany).set(i, newClient);
                            newCustomer = false;
                            break;
                        }
                    }
                }
                if(newCustomer == true)
                {
                    hashMap.get(insuranceCompany).add(newClient);
                }
            }
            else
            {
                List<Customer> newList = new Vector<Customer>();
                newList.add(newClient);
                hashMap.put(insuranceCompany,newList);
            }
        }
        //Creating separate files
        int fileNumber = 1;
        for (Map.Entry<String, List<Customer>> entry : hashMap.entrySet())
        {
            Collections.sort(hashMap.get(entry.getValue().get(0).getInsuranceCompany()), sortNames);

            String fileN = "C:\\Users\\simon\\IdeaProjects\\CSV\\OutputFile"+fileNumber+".csv";
            FileWriter fw = new FileWriter(fileN);
            BufferedWriter writer = new BufferedWriter(fw);

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("userID", "fName", "lName", "versionNumber", "insuranceCompany"));

            for (int i = 0; i < hashMap.get(entry.getValue().get(0).getInsuranceCompany()).size(); i++)
            {
                csvPrinter.printRecord(entry.getValue().get(i).getUserID(), entry.getValue().get(i).getFirstName(), entry.getValue().get(i).getLastName(), entry.getValue().get(i).getVersion(), entry.getValue().get(i).getInsuranceCompany());
            }
            fileNumber++;
            csvPrinter.flush();
        }
    }

    //Comparator overwriting
    static Comparator<Customer> sortNames = new Comparator<Customer>() {
        @Override
        public int compare(Customer customer1, Customer customer2) {
            return customer1.getLastName().compareTo(customer2.getLastName());
        }
    };
}
