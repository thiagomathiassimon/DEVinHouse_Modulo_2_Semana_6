package br.com.devinhouse.springbootapiexercicios.error;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String mensagem;
	private String detalhes;
	private String httpCodeMessage;

	public ExceptionResponse(Date date, String message, String details, String httpCodeMessage) {
		super();
		this.timestamp = date;
		this.mensagem = message;
		this.detalhes = details;
		this.httpCodeMessage = httpCodeMessage;
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
