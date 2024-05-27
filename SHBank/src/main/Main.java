// 1.1.2 Creation of main class for tests
// 1.2.3 Creation of the tablea account

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

public class Main {

	public static void main(String[] args) {

		List<Client> clients = generateClients(3);

		displayClients(clients);

		List<Account> accounts = generateAccounts(clients);

		displayAccounts(accounts);

	}

	public static List<Client> generateClients(int numberOfClients) {

		List<Client> clients = new ArrayList<>();

		for (int i = 1; i <= numberOfClients; i++) {
			clients.add(new Client("name" + i, "firstname" + i));
		}

		return clients;
	}

	public static void displayClients(List<Client> clients) {

		String clientDetails = clients.stream().map(Client::toString).collect(Collectors.joining("\n"));
		System.out.println(clientDetails);
	}

	public static List<Account> generateAccounts(List<Client> clients) {

		List<Account> accounts = new ArrayList<>();

		for (Client client : clients) {
			accounts.add(new SavingsAccount("Savings", client));
			accounts.add(new CurrentAccount("Current", client));
		}

		return accounts;
	}

	public static void displayAccounts(List<Account> accounts) {

		String accountDetails = accounts.stream().map(Account::toString).collect(Collectors.joining("\n"));
		System.out.println(accountDetails);
	}

}
