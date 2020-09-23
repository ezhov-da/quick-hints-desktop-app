package ru.ezhov.notes.template;

import org.junit.Test;
import ru.ezhov.notes.template.domain.Engine;
import ru.ezhov.notes.template.infrastructure.VelocityEngineImpl;

import java.util.List;

/**
 * Created by ezhov_da on 24.04.2018.
 */
public class VelocityEngineImplTest {
	@Test
	public void words() throws Exception {

		Engine engine = new VelocityEngineImpl();

		List<String> list = engine.words("all * from 0_0 where id = \n" +
				"\n" +
				"$hello\n" +
				"\n" +
				"$wow");
		for (String s : list) {
			System.out.println(s);
		}
	}

}