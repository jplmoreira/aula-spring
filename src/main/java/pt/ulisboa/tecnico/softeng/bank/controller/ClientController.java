package pt.ulisboa.tecnico.softeng.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ulisboa.tecnico.softeng.bank.domain.Bank;
import pt.ulisboa.tecnico.softeng.bank.domain.Client;
import pt.ulisboa.tecnico.softeng.bank.exception.BankException;

@Controller
@RequestMapping(value = "/banks/bank/{code}/clients")
public class ClientController {
	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String clientForm(Model model, @PathVariable String code) {
		logger.info("clientForm");
        Bank bank = Bank.getBankByCode(code);
        model.addAttribute("bank", bank);
		model.addAttribute("client", new Client());
		model.addAttribute("clients", bank.getClients());
		return "bank";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String clientSubmit(Model model, @ModelAttribute Client client, @PathVariable String code) {
		logger.info("clientSubmit name:{}, id:{}, age:{}, bankcode:{}", client.getName(), client.getId(), client.getAge(), code);
        Bank bank = Bank.getBankByCode(code);
        model.addAttribute("bank", bank);
		try {
			new Client(bank, client.getId(), client.getName(), client.getAge());
		} catch (BankException be) {
			model.addAttribute("error", "Error: it was not possible to create the client");
			model.addAttribute("client", bank);
			model.addAttribute("clients", bank.getClients());
			return "bank";
		}

		return "redirect:/bank";
	}
}
