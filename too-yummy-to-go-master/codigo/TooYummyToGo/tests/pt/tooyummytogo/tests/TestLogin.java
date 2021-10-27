package pt.tooyummytogo.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.exception.UtilizadorExistenteException;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class TestLogin {

	//atributos
	private RegistarUtilizadorHandler r;
	private RegistarComercianteHandler rc;
	private PosicaoCoordenadas p;
	private LocalDateTime now;
	private LocalDateTime plusHours;
	private Random random = new Random();
	private double preco = random.nextInt(1000)/100.0;
	private TooYummyToGo tooYummyToGo;

	// Testa se um novo consumidor apos ser registado no catalogo de utilizadores e registado pelo Handler eh de facto um utilizador
	@Test
	void testUtilizadorHandler() throws UtilizadorExistenteException {

		tooYummyToGo = new TooYummyToGo();

		RegistarUtilizadorHandler r = tooYummyToGo.getRegistarUtilizadorHandler();
		r.registarUtilizador("ana", "134144");

		Optional <Sessao> s = tooYummyToGo.autenticar("ana", "134144");

		assertTrue(s.isPresent());
	}

	// Testa se um novo comerciante apos ser registado no catalogo de utilizadores e registado pelo Handler eh de facto um utilizador
	@Test
	void testComercianteHandler() throws UtilizadorExistenteException {

		tooYummyToGo = new TooYummyToGo();

		p = new PosicaoCoordenadas(20.2, 12.5);

		rc = tooYummyToGo.getRegistarComercianteHandler();
		rc.registarComerciante("andre", "123", p);

		Optional <Sessao> s = tooYummyToGo.autenticar("andre", "123");

		assertTrue(s.isPresent());
	}

	// Testa se um novo consumidor apos ser registado no catalogo de utilizadores e registado pelo Handler com um nome,
	// nao pode ser criado um segundo utilizador com o mesmo nome
	@Test
	void testUtilizadorSemMesmoNome() throws UtilizadorExistenteException {

		tooYummyToGo = new TooYummyToGo();

		r = tooYummyToGo.getRegistarUtilizadorHandler();
		r.registarUtilizador("ana", "134144");

		Optional <Sessao> s1 = tooYummyToGo.autenticar("ana", "134144");
		Optional <Sessao> s2 = tooYummyToGo.autenticar("ana", "123");

		assertTrue(s1.isPresent());
		assertFalse(s2.isPresent());
	}

	// Testa se um novo comerciante apos ser registado no catalogo de utilizadores e registado pelo Handler com um nome, 
	// nao pode ser criado um segundo utilizador com o mesmo nome
	@Test
	void testComercianteSemMesmoNome() throws UtilizadorExistenteException {

		tooYummyToGo = new TooYummyToGo();

		p = new PosicaoCoordenadas(20.2, 12.5);

		rc = tooYummyToGo.getRegistarComercianteHandler();
		rc.registarComerciante("andre", "123", p);

		Optional <Sessao> s1 = tooYummyToGo.autenticar("andre", "123");
		Optional <Sessao> s2 = tooYummyToGo.autenticar("andre", "1457");

		assertTrue(s1.isPresent() );
		assertFalse(s2.isPresent());
	}

	// Testa se um comerciante tem acesso ao AdicionarTipoDeProdutoHandler
	@Test
	void testAdicionarTipoDeProdutoHandlerTemAcesso() {

		tooYummyToGo = new TooYummyToGo();

		p = new PosicaoCoordenadas(20.2, 12.5);

		rc = tooYummyToGo.getRegistarComercianteHandler();
		rc.registarComerciante("andre", "123", p);

		Optional <Sessao> s = tooYummyToGo.autenticar("andre", "123");

		s.get().adicionarTipoDeProdutoHandler().registaTipoDeProduto("tp", preco);
		s.get().getColocarProdutoHandler().indicaProduto("tp", 5);

		assertTrue(s.get().getColocarProdutoHandler().inicioDeProdutosHoje().contains("tp"));
	}

	// Testa se um comerciante tem acesso ao ColocarTipoDeProdutoHandler
	@Test
	void testColocarProdutoHandler() {

		tooYummyToGo = new TooYummyToGo();

		p = new PosicaoCoordenadas(20.2, 12.5);

		now = LocalDateTime.now();
		plusHours = LocalDateTime.now().plusHours(6);

		rc = tooYummyToGo.getRegistarComercianteHandler();
		rc.registarComerciante("andre", "123", p); //consumidor andré

		Optional <Sessao> s1 = tooYummyToGo.autenticar("andre", "123");

		s1.get().adicionarTipoDeProdutoHandler().registaTipoDeProduto("tp", preco);

		ColocarProdutoHandler cph = s1.get().getColocarProdutoHandler();
		List<String> listaTiposDeProdutos = cph.inicioDeProdutosHoje();
		cph.indicaProduto(listaTiposDeProdutos.get(0), 5);
		cph.confirma(now, plusHours);

		assertFalse(cph.inicioDeProdutosHoje().isEmpty());
	}

	@Test
	void testNaoHaComerciante() {

		tooYummyToGo = new TooYummyToGo();

		p = new PosicaoCoordenadas(20.2, 12.5);

		rc = tooYummyToGo.getRegistarComercianteHandler();
		rc.registarComerciante("andre", "123", p); //consumidor andré

		Optional <Sessao> s1 = tooYummyToGo.autenticar("andre", "123");

		s1.get().adicionarTipoDeProdutoHandler().registaTipoDeProduto("tp", preco);
		s1.get().getColocarProdutoHandler().indicaProduto("tp", 5);

		//produto nao foi confirmado pra venda a certas horas, logo nao aparecerah na lista de comerciantes
		//Logo nao vao haver Comerciantes disponiveis

		r = tooYummyToGo.getRegistarUtilizadorHandler();
		r.registarUtilizador("ana", "134144"); // consumidor ana

		Optional <Sessao> s2 = tooYummyToGo.autenticar("ana", "123");
		assertThrows(NoSuchElementException.class, () -> {s2.get().getEncomendarComerciantesHandler().indicaLocalizacaoActual(p);});
	}

	// Testa se um consumidor tem acesso ao EncomendarHandler
	@Test
	void testEncomendarHandler() {

		tooYummyToGo = new TooYummyToGo();

		now = LocalDateTime.now();
		plusHours = LocalDateTime.now().plusHours(6);

		r = tooYummyToGo.getRegistarUtilizadorHandler();
		r.registarUtilizador("ana", "134144"); // consumidor ana

		Optional <Sessao> s1 = tooYummyToGo.autenticar("ana", "134144");

		p = new PosicaoCoordenadas(20.2, 12.5);

		rc = tooYummyToGo.getRegistarComercianteHandler();
		rc.registarComerciante("andre", "123", p);

		Optional <Sessao> s2 = tooYummyToGo.autenticar("andre", "123");

		ProdutoInfo prod = new ProdutoInfo("tp", 5, preco);
		List <ProdutoInfo> prods = new ArrayList<>();
		prods.add(prod);

		ComercianteInfo comInfo = new ComercianteInfo("andre", p, prods);

		s2.get().adicionarTipoDeProdutoHandler().registaTipoDeProduto("tp", preco);

		ColocarProdutoHandler cph = s2.get().getColocarProdutoHandler();
		List<String> listaTiposDeProdutos = cph.inicioDeProdutosHoje();
		cph.indicaProduto(listaTiposDeProdutos.get(0), 5);
		cph.confirma(now, plusHours);

		assertFalse(s1.get().getEncomendarComerciantesHandler().escolheComerciante(comInfo).isEmpty());
		assertNotNull(s1.get().getEncomendarComerciantesHandler().indicaLocalizacaoActual(p).get(0).getProdutos());
		assertEquals("andre", s1.get().getEncomendarComerciantesHandler().indicaLocalizacaoActual(p).get(0).toString());
	}
}
