import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static final MobilePhone mobilePhone = new MobilePhone("081234567");

    public static void main(String[] args) {

       boolean quit = false;
       startPhone();
       printActions();

       while (!quit) {
           System.out.println("Enter action: ");
           int action = scanner.nextInt();
           scanner.nextLine();

           switch (action) {
               case 0 -> {
                   System.out.println("Shutting down!");
                   quit = true;
               }
               case 1 -> viewContactList();
               case 2 -> addContact();
               case 3 -> updateContact();
               case 4 -> deleteContact();
               case 5 -> queryContact();
               case 6 -> viewAirtimeBalance();
               case 7 -> loadAirtime();
               case 8 -> printActions();
           }

       }

       scanner.close();
    }

    private static void startPhone() {
        System.out.println("Starting phone...");
    }

    private static void printActions() {
        System.out.println("\nAvailable actions: \npress");
        System.out.println("""
                0 - to shutdown
                1 - to print contacts
                2 - to add contact
                3 - to update contact
                4 - to delete contact
                5 - query contact
                6 - view airtime balance
                7 - load airtime
                8 - print actions
                """);
        System.out.println("Choose your action: ");
    }

    private static void addContact() {
        System.out.println("Enter contact Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter contact number: ");
        String phoneNumber = scanner.nextLine();

        if (mobilePhone.addNewContact(Contact.createContact(name,phoneNumber))) {
            System.out.println("New Contact was added: " + name + " phoneNumber " + phoneNumber);
        } else {
            System.out.println("Contact is already on file");
        }
    }

    private static void viewContactList() {
        mobilePhone.printContacts();
    }

    private static void updateContact() {

        System.out.println("Enter existing contact name: ");
        String oldContact = scanner.nextLine();

        if (mobilePhone.queryContact(oldContact) == null) {
            System.out.println("Contact not found");
            return;
        }

        System.out.println("Enter new contact name: ");
        String newContact = scanner.nextLine();

        System.out.println("Enter new contact number: ");
        String newPhoneNumber = scanner.nextLine();

        if (mobilePhone.updateContact(mobilePhone.queryContact(oldContact),
                Contact.createContact(newContact, newPhoneNumber))) {
            System.out.println("Contact updated successfully");
        } else {
            System.out.println("Error updating contact");
        }
    }

    private static void deleteContact() {
        System.out.println("Enter contact name: ");
        String contactName = scanner.nextLine();
        if (mobilePhone.queryContact(contactName) == null) {
            System.out.println("Contact not found");
            return;
        }

        if (mobilePhone.removeContact(mobilePhone.queryContact(contactName))) {
            System.out.println(contactName + " Was deleted");
        } else {
            System.out.println("Error deleting contact");
        }
    }

    private static void queryContact() {
        System.out.println("Query contact name: ");
        String contactName = scanner.nextLine();
        if (mobilePhone.queryContact(contactName) == null) {
            System.out.println("Contact not found");
        } else {
            System.out.println("Name : " + mobilePhone.queryContact(contactName).getName() +
                                " -> phone number : " + mobilePhone.queryContact(contactName).getPhoneNumber());
        }
    }

    private static void loadAirtime() {
        System.out.println("Load airtime: ");
        double amount = scanner.nextDouble();
        mobilePhone.setAirtimeBalance(amount);
        System.out.println(mobilePhone.currencyFormatter(amount) + " airtime was loaded. Your airtime balance is: " +
                mobilePhone.currencyFormatter(mobilePhone.getAirtimeBalance()));
    }

    private static void viewAirtimeBalance() {
        System.out.println(mobilePhone.currencyFormatter(mobilePhone.getAirtimeBalance()));
    }
}
