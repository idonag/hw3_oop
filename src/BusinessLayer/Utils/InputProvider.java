package BusinessLayer.Utils;

import java.util.Scanner;

public class InputProvider {
    public String getInput(){
        Scanner scanner =  new Scanner(System.in);
        return scanner.nextLine();
    }
}
