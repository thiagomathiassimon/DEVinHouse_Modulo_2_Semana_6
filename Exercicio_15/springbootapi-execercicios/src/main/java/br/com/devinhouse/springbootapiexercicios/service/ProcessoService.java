package br.com.devinhouse.springbootapiexercicios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.springbootapiexercicios.dto.ProcessoAtualizadoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoCriadoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoDTO;
import br.com.devinhouse.springbootapiexercicios.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository repository;

	public List<ProcessoDTO> listarProcessos() {
		return recuperarTodosOsProcessos();
	}

	public List<ProcessoDTO> listarProcessosPorAno(String ano) {

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();
		List<ProcessoDTO> listaDeProcessos = new ArrayList<ProcessoDTO>();

		for (ProcessoDTO processoDTO : todosOsProcessos) {
			if (ano.equals(processoDTO.getNuAnoProcesso())) {
				listaDeProcessos.add(processoDTO);
			}
		}

		return listaDeProcessos;
	}

	public ProcessoCriadoDTO cadastrarProcesso(ProcessoDTO processo) {

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();

		todosOsProcessos.add(processo);

		ProcessoCriadoDTO processoCriado = new ProcessoCriadoDTO();
		processoCriado.setChaveProcesso(processo.getChaveProcesso());
		return processoCriado;
	}

	public ProcessoAtualizadoDTO atualizarProcesso(Integer nuProcesso, ProcessoDTO novoProcesso) {

		ProcessoAtualizadoDTO processoAtualizado = new ProcessoAtualizadoDTO();

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();

		for (ProcessoDTO processo : todosOsProcessos) {
			if (nuProcesso == processo.getNuProcesso()) {
				processo.setDescricao(novoProcesso.getDescricao());
				processoAtualizado.setNumeroProcesso(processo.getNuProcesso());
			}
		}

		return processoAtualizado;
	}

	public List<ProcessoDTO> deletarProcesso(Integer nuProcesso) {

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();

		int contador = 0;
		for (ProcessoDTO processo : todosOsProcessos) {
			if (nuProcesso == processo.getNuProcesso()) {
				todosOsProcessos.remove(contador);
				return todosOsProcessos;
			}
			contador++;
		}
		return todosOsProcessos;
	}

	public List<ProcessoDTO> listarProcessosPaginadosPorCursor(Integer id_inicial, Integer id_final) {

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();
		List<ProcessoDTO> processosPaginados = new ArrayList<>();

		for (ProcessoDTO processo : todosOsProcessos) {
			if (processo.getNuProcesso() >= id_inicial && processo.getNuProcesso() <= id_final) {
				processosPaginados.add(processo);
			}
		}

		return processosPaginados;
	}

	private List<ProcessoDTO> recuperarTodosOsProcessos() {
		return repository.findAllProcessos();
	}
}
