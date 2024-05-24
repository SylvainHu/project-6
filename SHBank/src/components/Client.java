//1.1.1 Creation of the client class

package components;

public class Client {

	private String Name;
	private String firstName;
	private int clientNumber;

	private static int numberOfClients = 0;

	public Client(String clientName, String clientFName) {
		Name = clientName;
		firstName = clientFName;

		clientNumber = ++numberOfClients;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	// toString() method
	@Override
	public String toString() {
		return "Client{" + "clientNumber=" + clientNumber + ", firstName='" + firstName + '\'' + ", Name='" + Name
				+ '\'' + '}';
	}
}
