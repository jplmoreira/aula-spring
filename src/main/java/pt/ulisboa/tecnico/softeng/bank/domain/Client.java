package pt.ulisboa.tecnico.softeng.bank.domain;

import pt.ulisboa.tecnico.softeng.bank.exception.ClientException;

public class Client {
	private Bank bank;
	private String id;
	private String name;
	private int age;

	public Client() {
	}

	public Client(Bank bank, String id, String name, int age) {
        checkDuplicateClient(bank, id);
		this.bank = bank;
		this.id = id;
		this.name = name;
		this.age = age;

		bank.addClient(this);
	}

	public void checkDuplicateClient(Bank bank, String id) {
		if (bank.getClientById(id) != null)
			throw new ClientException();
	}

	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
