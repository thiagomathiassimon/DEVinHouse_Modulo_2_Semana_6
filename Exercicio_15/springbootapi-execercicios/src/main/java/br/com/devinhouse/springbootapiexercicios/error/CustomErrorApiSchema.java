package br.com.devinhouse.springbootapiexercicios.error;

public class CustomErrorApiSchema {

	private String message;
	private String details;
	private String hint;
	private String nextActions;
	private String support;

	protected CustomErrorApiSchema() {
	}

	public CustomErrorApiSchema(String message, String details, String hint, String nextActions, String support) {
		this.message = message;
		this.details = details;
		this.hint = hint;
		this.nextActions = nextActions;
		this.support = support;
	}

}