package br.com.devinhouse.springboot.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.springboot.service.MensagemService;

@RestController
@RequestMapping(value = "/mensagem")
public class MensagemController {

	@Autowired
	MensagemService service;

	@RequestMapping(value = "/v1" + "/imprimir", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public String imprimirMensagem() {
		return service.olaMundo();
	}

	@RequestMapping(value = "/v1" + "/parametros", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public String imprimirParametros(@RequestParam String nome, @RequestParam String idade,
			@RequestParam String profissao, @RequestParam String cidade) {
		return service.imprimirParametros(nome, idade, profissao, cidade);
	}

}
