package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    ArrayList<Mail> inbox;
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<Mail> trash;
    public Gmail(String emailId, int inboxCapacity) {

        super(emailId);
        inbox = new ArrayList<>();
        trash = new ArrayList<>();
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        /* If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
         It is guaranteed that:
         1. Each mail in the inbox is distinct.
         2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
         */
        Mail newMail = new Mail(date, sender, message);

        if(inbox.size() == this.inboxCapacity) {
            String oldest = findOldestMessage();
            deleteMail(oldest);
        }

        inbox.add(newMail);

    }

    public void deleteMail(String message){
        /* Each message is distinct
        If the given message is found in any mail in the inbox, move the mail to trash, else do nothing*/

        for(Mail mail : inbox) {

            if(mail.getMessage().equals(message)) {
                inbox.remove(mail);
                trash.add(mail);
                break;
            }
        }
    }

    public String findLatestMessage(){
        /* If the inbox is empty, return null
         Else, return the message of the latest mail present in the inbox */
        
        if(inbox.size() > 0) {

            int lastIndex = inbox.size() - 1;
            String message = inbox.get(lastIndex).getMessage();
            return message;
        }

        return null;

    }

    public String findOldestMessage(){
        /* If the inbox is empty, return null
         Else, return the message of the oldest mail present in the inbox */
        
        if(inbox.size() > 0) {
            String message = inbox.get(0).getMessage();
        }
        return null;

    }

    public int findMailsBetweenDates(Date start, Date end){
        /* find number of mails in the inbox which are received between given dates
         It is guaranteed that start date <= end date */

        int count = 0;
        boolean startFound = false;
        boolean endFound = false;
        for(Mail mail : inbox) {

            Date date = mail.getDate();

            if(date.compareTo(start) >= 0 && date.compareTo(end) <= 0) count++;
//            if(date.after(end)) break;
        }

        return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
