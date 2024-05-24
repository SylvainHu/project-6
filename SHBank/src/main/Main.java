package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import components.Client;

public class Main {

	public static void main(String[] args) {
//		List<Client> clients = new ArrayList<Client>();
		List<Client> clients;

		clients = generateClients(3);

		displayClients(clients);

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

}
