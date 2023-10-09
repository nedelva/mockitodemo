package org.soapservice;


import java.util.Locale;
import java.util.concurrent.ExecutionException;

public interface MessageSource {
    String getMessage(String code, Locale inputLocale) throws ExecutionException;
}
