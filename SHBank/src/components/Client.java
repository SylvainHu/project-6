// 1.1.1 Creation of the client class

package components;

public class Client {

	private String name;
	private String firstName;
	private int clientNumber;

	private static int numberOfClients = 0;

	public Client(String firstName, String name) {

		this.firstName = firstName;
		this.name = name;

		clientNumber = ++numberOfClients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

//	Unnecessary as this attribute is auto-incremented
	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	// toString() method
	@Override
	public String toString() {
		return "Client number " + clientNumber + " is " + firstName + " " + name;
	}

}
