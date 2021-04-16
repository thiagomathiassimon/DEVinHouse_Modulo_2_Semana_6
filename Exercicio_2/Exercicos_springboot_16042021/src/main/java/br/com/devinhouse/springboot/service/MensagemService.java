package br.com.devinhouse.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class MensagemService {

	public String olaMundo() {
		return "Olá Mundo!";
	}

	public String imprimirParametros(String nome, String idade, String profissao, String cidade) {

		return "Nome: " + nome + "\nIdade: " + idade + "\nProfissão: " + profissao + "\nCidade: " + cidade;
	}

}
