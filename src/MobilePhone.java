import java.text.NumberFormat;
import java.util.ArrayList;

public class MobilePhone {

    private final String myNumber;
    private double airtimeBalance;
    private final ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.airtimeBalance = 0.0;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) < 0) {
            this.myContacts.add(contact);
            System.out.println("Contact added successfully: ");
            return true;
        }
        System.out.println("Contact is already on file");
        return false;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (findContact(oldContact) > -1) {
            this.myContacts.set(findContact(oldContact), newContact);
            System.out.println(oldContact.getName() + " contact was updated to new contact: " + newContact.getName());
            return true;
        } else if (findContact(newContact.getName()) != -1) {
            System.out.println("Contact with name " + newContact.getName() + " already exists");
        }
        System.out.println(oldContact.getName() + " was " + "not found");
        return false;
    }

    public boolean removeContact(Contact contact) {
        if (findContact(contact) > -1) {
            this.myContacts.remove(findContact(contact));
            return true;
        }
        return false;
    }

    private int findContact(Contact contact) {
       return this.myContacts.indexOf(contact);
    }

    private int findContact(String name) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            if (contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        if (findContact(name) > -1) {
            return this.myContacts.get(findContact(name));
        } else return null;
    }

    public void printContacts() {

        System.out.println("Contact List: ");
        for (Contact contact : this.myContacts) {
            System.out.println(contact.getName() + " -> " + contact.getPhoneNumber());
        }
    }

    public String queryContact(Contact contact) {
        if (findContact(contact) >= 0) {
            return contact.getName();
        } else return null;
    }

    public double getAirtimeBalance() {
        return this.airtimeBalance;
    }

    public void setAirtimeBalance(double amount) {
        this.airtimeBalance += amount;
    }

    public String currencyFormatter(double money) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(money);
    }
}
