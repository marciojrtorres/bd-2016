package dc.cobaia.webservices;

import spark.ResponseTransformer;

public abstract class WebService {
	
	public final String contentType;
	public final ResponseTransformer transformer;

	public WebService(String contentType, ResponseTransformer transformer) {
		this.contentType = contentType;
		this.transformer = transformer;
	}

}
