package com.OBLIGATORIO.OBLIGATORIO.Utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Respuesta {

  @Getter
  private String id;
  @Getter
  private Object parametro;

  public Respuesta(String id, Object parametro) {
    this.id = id;
    this.parametro = parametro;
  }

  public Respuesta() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setParametro(Object parametro) {
    this.parametro = parametro;
  }

  public static List<Respuesta> lista(Respuesta... respuestas) {
    List<Respuesta> retorno = new ArrayList<Respuesta>();
    for (Respuesta r : respuestas) {
      retorno.add(r);
    }
    return retorno;
  }

}
