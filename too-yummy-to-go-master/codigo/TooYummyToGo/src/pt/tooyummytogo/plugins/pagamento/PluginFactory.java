package pt.tooyummytogo.plugins.pagamento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class PluginFactory {

	// Receita para um singleton


	//atributos
	private List<PagamentoPlugin> cards = new ArrayList<>();
	
	// Passo 1: instancia estahtica
	private static PluginFactory INSTANCE = null;
	
	// Passo 2: getter estahtico da instancia
	public static PluginFactory getInstance() {
		if(INSTANCE == null) 
			INSTANCE = new PluginFactory();
		return INSTANCE;
	}

	// Passo 3: construtor privado
	protected PluginFactory() {
		Properties pluginsProp = new Properties();

		try {
			pluginsProp.load(new FileInputStream(new File("pagamento.properties")));

			String activatedPlugins = pluginsProp.getProperty("activatedPagamentoPlugins");
			for(String plugin : activatedPlugins.split(",")) {

				try {
					@SuppressWarnings("unchecked")
					Class<PagamentoPlugin> klass = (Class<PagamentoPlugin>) Class.forName(plugin);
					Constructor<PagamentoPlugin> constructor = klass.getConstructor();
					PagamentoPlugin p = constructor.newInstance();
					cards.add(p);
				} catch (ClassNotFoundException e) {
					// Do nothing, just ignore
				} catch (InstantiationException e) {
					// Do nothing, just ignore
				} catch (IllegalAccessException e) {
					// Do nothing, just ignore
				} catch (IllegalArgumentException e) {
					// Do nothing, just ignore
				} catch (InvocationTargetException e) {
					// Do nothing, just ignore
				} catch (NoSuchMethodException e) {
					// Do nothing, just ignore
				} catch (SecurityException e) {
					// Do nothing, just ignore
				}

			}
		} catch (FileNotFoundException e) {
			cards.add(new MonsterCardPlugin());
		} catch (IOException e) {
			cards.add(new MonsterCardPlugin());
		}


	}

	/**
	 * Metodos getPaymentPlugins
	 * @return cards
	 */
	public List<PagamentoPlugin> getPaymentPlugins() {
		return cards;
	}
}
