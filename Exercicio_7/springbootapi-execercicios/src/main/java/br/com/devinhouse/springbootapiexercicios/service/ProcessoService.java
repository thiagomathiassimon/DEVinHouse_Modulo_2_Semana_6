package br.com.devinhouse.springbootapiexercicios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.springbootapiexercicios.dto.ProcessoDTO;
import br.com.devinhouse.springbootapiexercicios.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository repository;

	public List<ProcessoDTO> listarProcessos() {
		return repository.findAllProcessos();
	}

	public List<ProcessoDTO> listarProcessosPorAno(String ano) {

		List<ProcessoDTO> todosOsProcessos = repository.findAllProcessos();
		List<ProcessoDTO> listaDeProcessos = new ArrayList<ProcessoDTO>();

		for (ProcessoDTO processoDTO : todosOsProcessos) {
			if (ano.equals(processoDTO.getNuAnoProcesso())) {
				listaDeProcessos.add(processoDTO);
			}
		}

		return listaDeProcessos;
	}
}
