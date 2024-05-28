// 1.1.2 Creation of main class for tests
// 1.2.3 Creation of the tablea account
// 1.3.1 Adaptation of the table of accounts
// 1.3.4 Creation of the flow array
// 1.3.5 Updating accounts

package main;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfert;

public class Main {

	public static void main(String[] args) {

		List<Client> clients = generateClients(3);
		displayClients(clients);

		List<Account> accounts = generateAccounts(clients);
		displayAccounts(accounts);

		Hashtable<Integer, Account> accountTable = createAccountTable(accounts);
		displayAccountTable(accountTable);

//		List<Flow> flows = loadFlows(accounts);

		List<Flow> flows = loadFlowsFromJson("src/flows.json");

		updateBalances(flows, accountTable);
		displayAccountTable(accountTable);
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
		System.out.println(clientDetails + "\n");
	}

	// 1.2.3 Creation of the tablea account
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
		System.out.println(accountDetails + "\n");
	}

	// 1.3.1 Adaptation of the table of accounts
	public static Hashtable<Integer, Account> createAccountTable(List<Account> accounts) {
		Hashtable<Integer, Account> accountTable = new Hashtable<>();

		for (Account account : accounts) {
			accountTable.put(account.getAccountNumber(), account);
		}

		return accountTable;
	}

	public static void displayAccountTable(Hashtable<Integer, Account> accountTable) {
		String tableDetails = accountTable.values().stream().sorted((a1, a2) -> {
			int balanceComparison = Double.compare(a1.getBalance(), a2.getBalance());
			return balanceComparison != 0 ? balanceComparison
					: Integer.compare(a1.getAccountNumber(), a2.getAccountNumber());
		}).map(Account::toString).collect(Collectors.joining("\n"));
		System.out.println(tableDetails + "\n");
	}

	// 1.3.4 Creation of the flow array
	public static List<Flow> loadFlows(List<Account> accounts) {
		List<Flow> flows = new ArrayList<>();

		flows.add(new Debit("Debit of 50€", 50, accounts.get(0).getAccountNumber(), true, LocalDate.now().plusDays(2)));

		for (Account account : accounts) {
			if (account instanceof CurrentAccount) {
				flows.add(new Credit("Credit of 100.50€", 100.50, account.getAccountNumber(), true,
						LocalDate.now().plusDays(2)));
			}

			if (account instanceof SavingsAccount) {
				flows.add(new Credit("Credit of 1500€", 1500, account.getAccountNumber(), true,
						LocalDate.now().plusDays(2)));
			}
		}

		flows.add(new Transfert("Transfer of 50€", 50, accounts.get(1).getAccountNumber(), true,
				LocalDate.now().plusDays(2), accounts.get(0).getAccountNumber()));

		return flows;
	}

	// 1.3.5 Updating accounts
	public static void updateBalances(List<Flow> flows, Map<Integer, Account> accountTable) {
		for (Flow flow : flows) {
			Account targetAccount = accountTable.get(flow.getTargetAccountNumber());
			targetAccount.setBalance(flow);

			if (flow instanceof Transfert) {
				Transfert transfer = (Transfert) flow;
				Account issuingAccount = accountTable.get(transfer.getIssuingAccountNumber());
				issuingAccount.setBalance(flow);
			}
		}

		Optional<Account> negativeBalanceAccount = accountTable.values().stream()
				.filter(account -> account.getBalance() < 0).findAny();
		negativeBalanceAccount.ifPresent(account -> System.out.println("Account with negative balance: " + account));
	}

	// 2.1 JSON file of flows
	public static List<Flow> loadFlowsFromJson(String filePath) {
		List<Flow> flows = new ArrayList<>();
		JSONParser parser = new JSONParser();
		Path path = Paths.get(filePath);
		try (FileReader reader = new FileReader(path.toFile())) {
			Object obj = parser.parse(reader);
			JSONArray jsonArray = (JSONArray) obj;

			for (Object o : jsonArray) {
				JSONObject flowObject = (JSONObject) o;
				String type = (String) flowObject.get("type");
				String comment = (String) flowObject.get("comment");
				double amount = ((Number) flowObject.get("amount")).doubleValue();
				int targetAccountNumber = ((Number) flowObject.get("targetAccountNumber")).intValue();
				boolean effect = (Boolean) flowObject.get("effect");
				LocalDate dateOfFlow = LocalDate.parse((String) flowObject.get("dateOfFlow"));

				switch (type) {
				case "Debit":
					flows.add(new Debit(comment, amount, targetAccountNumber, effect, dateOfFlow));
					break;
				case "Credit":
					flows.add(new Credit(comment, amount, targetAccountNumber, effect, dateOfFlow));
					break;
				case "Transfert":
					int issuingAccountNumber = ((Number) flowObject.get("issuingAccountNumber")).intValue();
					flows.add(new Transfert(comment, amount, targetAccountNumber, effect, dateOfFlow,
							issuingAccountNumber));
					break;
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return flows;
	}

}
