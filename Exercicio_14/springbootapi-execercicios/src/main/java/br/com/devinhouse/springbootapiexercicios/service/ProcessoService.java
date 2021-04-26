package br.com.devinhouse.springbootapiexercicios.service;

import java.util.ArrayList;
import java.util.List;

import br.com.devinhouse.springbootapiexercicios.exceptions.IllegalYearFormatException;
import br.com.devinhouse.springbootapiexercicios.exceptions.ServiceNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.springbootapiexercicios.dto.ProcessoAtualizadoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoCriadoDTO;
import br.com.devinhouse.springbootapiexercicios.dto.ProcessoDTO;
import br.com.devinhouse.springbootapiexercicios.exceptions.ProcessNotFoundException;
import br.com.devinhouse.springbootapiexercicios.exceptions.YearNotFoundException;
import br.com.devinhouse.springbootapiexercicios.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository repository;

	public List<ProcessoDTO> listarProcessos() {
		return recuperarTodosOsProcessos();
	}

	public List<ProcessoDTO> listarProcessosPorAno(String ano) {

		try{
			Integer anoNumerico = Integer.parseInt(ano);
		}catch (IllegalYearFormatException iyfe){
			throw new IllegalYearFormatException("O ano que informastes não é válido! Por obséquio, informe outro!");
		}catch(NumberFormatException nfe){
			throw new NumberFormatException("O ano que informastes não é válido! Por obséquio, informe outro!");
		}

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();
		List<ProcessoDTO> listaDeProcessos = new ArrayList<ProcessoDTO>();

		for (ProcessoDTO processoDTO : todosOsProcessos) {
			if (ano.equals(processoDTO.getNuAnoProcesso())) {
				listaDeProcessos.add(processoDTO);
			}
		}

		if (listaDeProcessos.size() == 0) {
			throw new YearNotFoundException("Não consta, em nosso sistema, nenhum processo para esse ano!");
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

		throw new ProcessNotFoundException(
				"Não foi possível localizar vosso processo! Sinceros lamentos acerca disso!");
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

	public List<ProcessoDTO> listarProcessosPaginadosPorPagePageSize(Integer page, Integer pageSize) {

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();
		List<ProcessoDTO> processosPaginados = new ArrayList<>();

		double numberOfPages = Math.ceil(todosOsProcessos.size() / (pageSize * 1.00));

		if (page <= numberOfPages) {

			for (ProcessoDTO processo : todosOsProcessos) {
				if (processo.getNuProcesso() > ((page - 1) * pageSize)
						&& processo.getNuProcesso() <= (page * pageSize)) {
					processosPaginados.add(processo);
				}
			}
		}

		return processosPaginados;
	}

	public List<ProcessoDTO> listarProcessosPaginadosPorOffsetLimit(Integer offset, Integer limit) {

		List<ProcessoDTO> todosOsProcessos = recuperarTodosOsProcessos();
		List<ProcessoDTO> processosPaginados = new ArrayList<>();

		int contador = 1;
		for (ProcessoDTO processo : todosOsProcessos) {
			if (contador >= offset && contador <= limit) {
				processosPaginados.add(processo);
			}
			contador++;
		}

		return processosPaginados;
	}

	public List<ProcessoDTO> listarProcessosDeletados() {
		throw new ServiceNotExistException("O serviço pelo qual buscavas ainda não está disponível! Sinceros lamentos!");
	}
}
