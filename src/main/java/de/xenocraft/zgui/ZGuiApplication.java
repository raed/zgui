package de.xenocraft.zgui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import de.xenocraft.zgui.model.Mensakarte;

@SpringBootApplication
public class ZGuiApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ZGuiApplication.class, args);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new MensakartenConverter());
		registry.addConverter(new MensakartenConverter2());
	}

	public static final class MensakartenConverter implements Converter<String, Mensakarte> {

		@Override
		public Mensakarte convert(String source) {
			if (source == null || source == "") {
				return null;
			}
			String number = source.split("\\D+")[0];
			String zusatz = source.substring(number.length());
			Mensakarte k = new Mensakarte();
			k.setNr(Integer.parseInt(number));
			k.setZusatz(zusatz);
			return k;
		}

	}

	public static final class MensakartenConverter2 implements Converter<Mensakarte, String> {

		@Override
		public String convert(Mensakarte source) {
			return source.getNr() + source.getZusatz();
		}

	}
}
