package password.enryption;

import java.util.Scanner;

/**
 *
 * @author Jakaza G Chauke
 */
public class FrontEnd {

    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);
        String password;
        
        System.out.print("Please enter your password : ");
        password = sc.nextLine();
        
        String salt = PasswordUtils.getSalt(30);
        String securedPassword = PasswordUtils.generateSecuredPassword(password, salt);
        
        
        System.out.println("Original Password : " + password);
        System.out.println("Secured Password : " + securedPassword);
        System.out.println("Salt : " + salt);

//        String sPassword = "J1S5QTBTV0w6tEb6SBdkL2d4v3bozlpxNp7BppBFdxc=";
//        String salt = "YTsMADv6bPz8IVbLXcTzmn5Lh";
//        
//        boolean verifyPassword = PasswordUtils.verifyPassword(password, sPassword, salt);
//        
//        if(verifyPassword){
//            System.out.println("Entered Password Is Correct");
//        }
//        else{
//            System.out.println("Incorrect Password...");
//        }
// 
        
     
    }
}
