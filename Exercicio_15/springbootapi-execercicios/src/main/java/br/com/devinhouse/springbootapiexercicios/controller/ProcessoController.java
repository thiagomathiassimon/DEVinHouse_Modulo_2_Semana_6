package br.com.devinhouse.springbootapiexercicios.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.springbootapiexercicios.dto.ProcessoAtualizadoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoCriadoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoReturnResponseDTO;
import br.com.devinhouse.springbootapiexercicios.error.CustomErrorApi;
import br.com.devinhouse.springbootapiexercicios.service.ProcessoService;

@RestController
@RequestMapping(value = "/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService service;

	@RequestMapping(headers = "api-version=v1", value = "/v1"
			+ "/listar/json", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProcessoDTO> listarProcessos() {
		return service.listarProcessos();
	}

	@RequestMapping(headers = "api-version=v1", value = "/v1"
			+ "/listar/xml", method = GET, produces = APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseEntity<ProcessoReturnResponseDTO> listarProcessoXML() {
		ProcessoReturnResponseDTO response = new ProcessoReturnResponseDTO();
		response.setProcesso(service.listarProcessos());
		return new ResponseEntity<ProcessoReturnResponseDTO>(response, HttpStatus.OK);
	}

	@GetMapping(headers = "api-version=v1", value = "/v1" + "/listar/ano", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProcessoDTO> listarProcessosPorAno(@RequestParam String ano) {
		List<ProcessoDTO> processos = service.listarProcessosPorAno(ano);
//			if (processos.size() == 0) {
//			throw new CustomErrorApi("Processos não encontrados!", "Não foi possível completar a busca.",
//					"Verifique o ano ou a ulr.", "Tente novamente.", "Em caso de dúvida, procure a documentação.");
//			}
		return processos;
	}

	@ResponseStatus(value = CREATED)
	@PostMapping(headers = "api-version=v1", value = "/v1"
			+ "/cadastrar/processo", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public ProcessoCriadoDTO cadastrarProcesso(@RequestBody ProcessoDTO processo) {
		return service.cadastrarProcesso(processo);
		/*
		 * { "chaveProcesso" : "SOFT 11/2020", "sgOrgaoProcesso":"SOFT", "nuProcesso" :
		 * 11, "nuAnoProcesso" : "2020", "cdAssunto" : 11, "descricaoAssunto":
		 * "Assunto teste 11 DEV In House", "descricao":
		 * "Processo teste 11 DEV In House", "cdInteressado": 11, "nmInteressado":
		 * "DEV In House" }
		 */
	}

	@ResponseStatus(value = OK)
	@PutMapping(headers = "api-version=v1", value = "/v1"
			+ "/atualizar/processo/{nuProcesso}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public ProcessoAtualizadoDTO atualizarDescricaoDoProcesso(@PathVariable Integer nuProcesso,
			@RequestBody ProcessoDTO novaDescricao) {
		return service.atualizarProcesso(nuProcesso, novaDescricao);
	}

	@DeleteMapping(headers = "api-version=v1", value = "/v1" + "/deletar/processo/{nuProcesso}")
	public List<ProcessoDTO> deletarProcesso(@PathVariable Integer nuProcesso) {
		return service.deletarProcesso(nuProcesso);
	}

	@GetMapping(headers = "api-version=v1", value = "/v1"
			+ "/listar/processos/paginados", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProcessoDTO> listarProcessosPaginadosPorCursor(@RequestParam Integer id_inicial,
			@RequestParam Integer id_final) {
		return service.listarProcessosPaginadosPorCursor(id_inicial, id_final);
	}

}
