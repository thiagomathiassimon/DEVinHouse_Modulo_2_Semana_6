package br.com.devinhouse.springbootapiexercicios.dto;

import static java.time.LocalDateTime.now;

public class ProcessoCriadoDTO {

	private String chaveProcesso;
	private String dataDeCriacaoDoProcesso = now().getMonthValue() + "/" + now().getDayOfMonth() + "/"
			+ now().getYear();
	private String horaDeCriacaoDoProcesso = now().getHour() + ":" + now().getMinute() + ":" + now().getSecond();

	public String getChaveProcesso() {
		return chaveProcesso;
	}

	public void setChaveProcesso(String chaveProcesso) {
		this.chaveProcesso = chaveProcesso;
	}

	public String getDataDeCriacaoDoProcesso() {
		return dataDeCriacaoDoProcesso;
	}

	public void setDataDeCriacaoDoProcesso(String dataDeCriacaoDoProcesso) {
		this.dataDeCriacaoDoProcesso = dataDeCriacaoDoProcesso;
	}

	public String getHoraDeCriacaoDoProcesso() {
		return horaDeCriacaoDoProcesso;
	}

	public void setHoraDeCriacaoDoProcesso(String horaDeCriacaoDoProcesso) {
		this.horaDeCriacaoDoProcesso = horaDeCriacaoDoProcesso;
	}

}
