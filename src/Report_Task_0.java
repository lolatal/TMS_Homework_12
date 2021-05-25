import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Report_Task_0 {
    public static void main(String[] args) {
        System.out.println("Введите путь к файлу и имя файла ");
        Scanner scanner = new Scanner(System.in);
        String pathAndNameOfFile;
        ArrayList<String> listOfFiles= new ArrayList<>();
        int endOfInput = 0;
        while (scanner.hasNextLine()){
            pathAndNameOfFile = scanner.nextLine();
            listOfFiles.add(pathAndNameOfFile);
            System.out.println("Введите путь и имя следующего файла. Для завершения ввода списка введите 0");
            if (scanner.hasNext(String.valueOf(endOfInput))){
                System.out.println("Вы прекратили ввод списка файлов");
                break;
            }
        }
        Set<String> linesInFiles = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(listOfFiles.get(0)));
            BufferedReader br1 = new BufferedReader(new FileReader(listOfFiles.get(1)));
            BufferedReader br2 = new BufferedReader(new FileReader(listOfFiles.get(2)))){
            String s;
            while((s = br.readLine()) != null) {
                linesInFiles.add(s);
            }
            while((s = br1.readLine()) != null) {
                linesInFiles.add(s);
            }
            while((s = br2.readLine()) != null) {
                linesInFiles.add(s);
            }
        }catch (IOException e){
            System.out.println("Error");
        }


        HashMap<String, String> reportedMessageLine = new HashMap<>();
        String message;

        for (String item: linesInFiles){
            if((item.length()==15) && (item.startsWith("docnum") || item.startsWith("contract"))){
                message = "Valid document number";
                reportedMessageLine.put(item, message);
            }else if(item.length() != 15){
                message = "Invalid document number length";
                reportedMessageLine.put(item, message);
            }else if(!item.startsWith("docnum") || !item.startsWith("contract")){
                message = "Invalid start of the document number";
                reportedMessageLine.put(item, message);
            }
        }

        System.out.println("Введите путь и имя файла, который будет содержать отчет");
        Scanner scanner1 = new Scanner(System.in);
        String pathToReportFile = scanner1.nextLine();
        try(FileWriter fw1 = new FileWriter(pathToReportFile)){
            for(Map.Entry<String, String> item: reportedMessageLine.entrySet()){
                fw1.write(item.getKey() + ": " + item.getValue() + "\n");
            }

        }catch (IOException e){
            System.out.println("Error");
        }

        scanner.close();
        scanner1.close();
    }
}
