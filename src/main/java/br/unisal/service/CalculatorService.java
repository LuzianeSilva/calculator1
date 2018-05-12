package br.unisal.service;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.soap.SOAPFaultException;

import br.unisal.exceptions.DivisionByZeroArithymeticException;
import br.unisal.interfaces.Calculable;

/**
 * 
 * @author jether.rodrigues
 *
 */

@WebService(endpointInterface = "br.unisal.interfaces.Calculable"
	, name = "CalculatorService"
	, serviceName = "CalculatorService"
	, targetNamespace = "http://jetherrodrigues.com/jaxws/calculator")
public class CalculatorService implements Calculable {
	SOAPFactory soapFactory;
	SOAPFault soapFault;

	public CalculatorService() throws SOAPException {
		soapFactory = SOAPFactory.newInstance();
	}

	@Override
	public double divide(double dividend, double divisor) throws SOAPException {
		if (divisor == 0) {
			DivisionByZeroArithymeticException dByZero = new DivisionByZeroArithymeticException("O divisor não pode ser igual a Zero.");
			soapFault = soapFactory.createFault(dByZero.getMessage(), new QName("http://schemas.xmlsoap.org/soap/envelope/", "CalculatorService")); 
			throw new SOAPFaultException(soapFault);
		}

		if (divisor < 0) {
			/*
			 * Pode ocorrer divisão por números negativos, no entanto para nosso
			 * serviço, foi definido que não (simulando uma definição de
			 * requisitos)
			 */
			IllegalArgumentException illegalArgumentException =  new IllegalArgumentException("Divisor não pode ser números negativos.");
			soapFault = soapFactory.createFault(illegalArgumentException.getMessage(), new QName("http://schemas.xmlsoap.org/soap/envelope/", "CalculatorService")); 
			throw new SOAPFaultException(soapFault);
		}

		return dividend / divisor;
	}

	@Override
	public double add(double portion1, double portion2) {
		// TODO Auto-generated method stub
		return portion1 + portion2;
	}

	@Override
	public double subtract(double portion1, double portion2) {
		// TODO Auto-generated method stub
		return portion1 - portion2;
	}

	@Override
	public double multiply(double factor1, double factor2) {
		// TODO Auto-generated method stub
		return factor1 * factor2;
	}

}
