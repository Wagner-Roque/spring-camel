package com.wagner.debezium.camel.route;

import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.springframework.stereotype.Component;

@Component
public class DebeziumRoute extends RouteBuilder {

	private String offsetStorageFileName = "C:\\Users\\wagner.roque\\Documents\\workspace_camel_case\\debezium\\offset-file.dat";
	private String host = "localhost";
	private String username= "postgres";
	private String password = "postgres";
	private String db = "postgres";
	
	@Override
	public void configure() throws Exception {
		from("debezium-postgres:dbz-postgres?offsetStorageFileName=" + offsetStorageFileName 
				+ "&databaseHostName=" + host 
				+"&databaseUser=" + username
				+"&databasePassword=" + password
				+"&databaseServerName=" + db
				+"&databaseDbname= " +db
				+"&pluginName=pgoutput")
		.log("Evento: ${body}")
		.log("Identificador: ${headers.CamnelDebeziumIdentifier}")
		.log("Source Metadata: ${headers.CamnelDebeziumSourceMetadata}")
		.log("Operação: ${headers.CamnelDebeziumOperation}")//tipo do evento: c = create, u = update, d = delete, r = snapshot
		.log("Base: ${headers.CamnelDebeziumSourceMetadata[db]}")
		.log("Tabela: ${headers.CamnelDebeziumSourceMetadata[table]}")
		.log("Chave primária: ${headers.CamnelDebeziumKey}")
		.process(exchange ->{
			Struct body = exchange.getIn().getBody(Struct.class);
			Schema schema = body.schema();
			
			log.info("Body: " + body);
			log.info("Schema: " + schema);
			log.info("Campos: " + schema.fields());
			log.info("Campo name: " + schema.field("name"));
			
			//outra forma para conversão da mensagem
			Map bodies = exchange.getIn().getBody(Map.class);
			log.info("Body: " + bodies);
		});
		
	}

}
