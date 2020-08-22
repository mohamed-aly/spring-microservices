package stationery.store.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {

    public static void main(String[] args) throws IOException {
        scriptEnhancer("guru_database_create.sql");
    }

    public static void scriptEnhancer(String sqlFileName) throws IOException {
        String path = System.getProperty("user.dir") + "\\" + sqlFileName;
        System.out.println(path);

        //read file data
        List<String> lines = new LinkedList<>();
        File file = null;
        try {
            file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine() + ";";
                lines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Rewrite the sql codes
        Path writePath = Paths.get(path);
        Files.write(writePath, lines, UTF_8);
    }

    public static Pageable pageable(Integer page, Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        return pageable;
    }

    public static Pageable pageable(int page, int pageSize){
        if(pageSize==0){
            pageSize=10;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        return pageable;
    }


}
