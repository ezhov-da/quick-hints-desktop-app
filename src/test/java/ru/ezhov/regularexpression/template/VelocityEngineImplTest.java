package ru.ezhov.regularexpression.template;

import org.junit.Test;

import java.util.List;

/**
 * Created by ezhov_da on 24.04.2018.
 */
public class VelocityEngineImplTest {
	@Test
	public void words() throws Exception {

		Engine engine = new VelocityEngineImpl("all * from 0_0 where id = \n" +
			"\n" +
			"$hello\n" +
			"\n" +
			"$wow");

		List<String> list = engine.words();
		for (String s : list) {
			System.out.println(s);
		}
	}

}