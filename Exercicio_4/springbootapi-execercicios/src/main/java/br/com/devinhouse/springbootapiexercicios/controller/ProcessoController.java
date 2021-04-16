package br.com.devinhouse.springbootapiexercicios.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.springbootapiexercicios.dto.ProcessoDTO;
import br.com.devinhouse.springbootapiexercicios.service.ProcessoService;

@RestController
@RequestMapping(value = "/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService service;

	@RequestMapping(headers = "api-version=v1", value = "/v1"
			+ "/listar", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProcessoDTO> listarProcessos() {
		return service.listarProcessos();
	}

}
