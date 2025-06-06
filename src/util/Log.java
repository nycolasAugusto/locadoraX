package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Log {

    public static void setError() {

        try {
            List<String> errosAntigos = LerErros();
            File dir = new File("logs");
            dir.mkdir();
            errosAntigos.add(0, "Erros");
            
            PrintStream logErro = new PrintStream("logs/erro.txt");
            System.setErr(logErro);
           
            for (String erros : errosAntigos) {
                System.err.println(erros);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

      private static List<String> LerErros() throws IOException {
        List<String> erros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("logs/erro.txt"))) {
           
            reader.readLine();
            String linha = " ,";
            while ((linha = reader.readLine()) != null) {
                
                String[] partes = linha.split(",");
                for (String parte : partes) {
                    erros.add(parte);
                }
            }
        }
        return erros;
    }
}

