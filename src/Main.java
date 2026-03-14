import data.models.Type;

import java.security.SecureRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    String type = "ENTRY";
    if (type.equals(Type.ENTRY.toString())) {
        System.out.println("Entered Successfully");
    }
    else if(type.equals(Type.EXIT.toString())) {
        System.out.println("Exited Successfully");
    }

    }
}


//https://github.com/odiliemmanuel/EstateSystem.git