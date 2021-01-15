package coreUtils;

import static coreUtils.RootApiUrl.setRootApiUrl;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Anup
 *
 */
public abstract class ApiCore {

	protected ApiCore() {
	}
	
	protected Response getByPath(final String url) {
		setRootApiUrl();
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).get(url).thenReturn();
	}
}
