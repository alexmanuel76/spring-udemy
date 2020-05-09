package mova.laboratorio.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;

@RestController
public class LogAppController {

	Logger logger = LogManager.getLogger(LogAppController.class);

	public void Mensaje(String mensaje, String tipo) {
		switch (tipo) {
		case "INFO":
			logger.info(mensaje);
			break;
		case "WARN":
			logger.warn(mensaje);
			break;
		case "ERR":
			logger.error(mensaje);
			break;
		default:
			logger.info(mensaje);
		}
	}
}
