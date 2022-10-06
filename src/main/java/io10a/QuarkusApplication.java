package io10a;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "Greetings translator API", description = "short API test", version = "1.2"))
public class QuarkusApplication extends Application {
}
