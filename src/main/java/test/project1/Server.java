package test.project1;

import io.vertx.core.*;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.ext.web.*;
import io.vertx.ext.web.handler.BodyHandler;
import test.project1.StringAnalyzers.IStringAnalayzer;
import test.project1.StringAnalyzers.StringAnalyzersFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Server extends AbstractVerticle {

	private Router router;
	private Collection<IStringAnalayzer> stringAnalyzers = new StringAnalyzersFactory().getAnalyzers();

		private void analyze(RoutingContext routingContext) {

		try {
			final String text = routingContext.getBodyAsJson().getValue("text").toString();
			Map<String, Collection<String>> stringAnalysisResult = new HashMap<>();
			stringAnalyzers.stream().forEach((stringAnalyzer) -> {
				stringAnalysisResult.put(stringAnalyzer.GetName(),stringAnalyzer.GetClosest(text));
			});
			routingContext.response()
					.setStatusCode(201)
					.putHeader("content-type", "application/json; charset=utf-8")
					.end(Json.encodePrettily(stringAnalysisResult));
			routingContext.put("text",text);
			routingContext.next();
		}catch (DecodeException e){
			routingContext.response()
					.setStatusCode(400).end("Bad very Bad request... did you check json?");
		}
	}
	private void addData(RoutingContext routingContext) {
		System.out.println(routingContext.getBodyAsJson().getValue("text").toString());
		stringAnalyzers.stream().forEach((stringAnalyzer) -> {
			stringAnalyzer.UpdateData(routingContext.get("text"));
		});
	}

	@Override
	public void start(Future<Void> fut) throws Exception {
		router = Router.router(vertx);
		router.route("/analyze").handler(BodyHandler.create());
		router.post("/analyze").consumes("application/json").handler(this::analyze).blockingHandler(this::addData);

		vertx.createHttpServer().requestHandler(router::accept)
			.listen(
				config().getInteger("http.port", 90),
				result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});
	}

}
