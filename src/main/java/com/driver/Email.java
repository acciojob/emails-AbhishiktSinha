package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        /*Change password only if the oldPassword is equal to current password and the new password meets all of the following:
         1. It contains at least 8 characters
         2. It contains at least one uppercase letter
         3. It contains at least one lowercase letter
         4. It contains at least one digit
         5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        */

        if(oldPassword.equals(this.password)) {

            if(newPassword.length() >= 8) {

                boolean[] check = new boolean[4];
                // 0 uppercase, 1 lowercase, 2 digit, 3 special

                for(int i = 0; i < newPassword.length(); i++) {
                    char ch = newPassword.charAt(i);

                    if(ch >=65 && ch <= 90) {
                        if(!check[0]) check[0] = true;
                    }
                    if(ch >= 97 && ch <= 122) {
                        if(!check[1]) check[1] = true;
                    }
                    if(ch >= 48 && ch <= 57) {
                        if(!check[2]) check[2] = true;
                    }
                    else {
                        if(!check[3]) check[3] = true;
                    }
                }

                boolean valid = false;
                for(boolean b : check) {
                    valid = valid || b;
                }

                if(valid) {
                    this.password = newPassword;
                    System.out.println("Password set successfully");
                    return;
                }
            }
        }

            System.out.println("Invalid password");
            return;

    }
}
