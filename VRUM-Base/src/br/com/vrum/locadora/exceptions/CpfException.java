package br.com.vrum.locadora.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class CpfException extends RuntimeException{
}
