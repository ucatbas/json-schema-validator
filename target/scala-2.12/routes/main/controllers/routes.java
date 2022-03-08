// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseSchemaController SchemaController = new controllers.ReverseSchemaController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseSchemaController SchemaController = new controllers.javascript.ReverseSchemaController(RoutesPrefix.byNamePrefix());
  }

}
