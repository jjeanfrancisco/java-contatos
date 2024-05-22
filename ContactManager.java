import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;
    private static final String FILE_NAME = "contacts.txt";

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(String name, String phoneNumber) {
        Contact contact = new Contact(name, phoneNumber);
        contacts.add(contact);
        saveContacts();
        System.out.println("Contato adicionado: " + contact);
    }

    public void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println("Contato encontrado: " + contact);
                return;
            }
        }
        System.out.println("Contato não encontrado.");
    }

    private void saveContacts() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Contact contact : contacts) {
                writer.write(contact.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contacts.add(Contact.fromCSV(line));
            }
        } catch (FileNotFoundException e) {
            // O arquivo ainda não existe, então não há nada para carregar.
        } catch (IOException e) {
            System.out.println("Erro ao carregar contatos: " + e.getMessage());
        }
    }
}
