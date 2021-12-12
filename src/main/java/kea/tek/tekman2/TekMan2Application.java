package kea.tek.tekman2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TekMan2Application {

    public static void main(String[] args) {

        /*
        args = new String[3];
        args[0] = "jdbc:mysql://localhost:3306/tek-man-2?serverTimezone=UTC";
        args[1] = "mads";
        args[2] = "password";
        System.out.println("args:");
        printArgs(args);
         */

        SpringApplication.run(TekMan2Application.class, args);
    }

    public static void printArgs(String[] args){
        for (String arg:args) {
            System.out.println(arg);
        }
    }
}
