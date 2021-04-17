package br.com.devinhouse.springbootapiexercicios.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.springbootapiexercicios.dto.ProcessoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoReturnResponseDTO;
import br.com.devinhouse.springbootapiexercicios.service.ProcessoService;

@RestController
@RequestMapping(value = "/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService service;

	@RequestMapping(headers = "api-version=v1", value = "/v1" + "/listar"
			+ "/json", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProcessoDTO> listarProcessos() {
		return service.listarProcessos();
	}

	@RequestMapping(headers = "api-version=v1", value = "/v1" + "/listar"
			+ "/xml", method = GET, produces = APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseEntity<ProcessoReturnResponseDTO> listarProcessoXML() {
		ProcessoReturnResponseDTO response = new ProcessoReturnResponseDTO();
		response.setProcesso(service.listarProcessos());
		return new ResponseEntity<ProcessoReturnResponseDTO>(response, HttpStatus.OK);
	}
	
	@GetMapping(headers = "api-version=v1", value = "/v1" + "/listar" + "/ano", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProcessoDTO> listarProcessosPorAno(@RequestParam String ano){
		return service.listarProcessosPorAno(ano);
	}

}
