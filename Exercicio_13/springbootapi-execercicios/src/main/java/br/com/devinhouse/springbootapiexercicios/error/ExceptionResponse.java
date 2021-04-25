package br.com.devinhouse.springbootapiexercicios.error;

import java.util.Date;

public class ExceptionResponse {
	private String httpCodeMessage;
	private String detalhes;
	private String mensagem;
	private Date timestamp;

	public ExceptionResponse(Date date, String message, String details, String httpCodeMessage) {
		this.httpCodeMessage = httpCodeMessage;
		this.detalhes = details;
		this.mensagem = message;
		this.timestamp = date;
	}

	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}
}
