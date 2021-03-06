package example.chinook.rest;

import example.chinook.repository.AlbumRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.TestContextManager;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
@SpringBootTest(classes = RestConfig.class)
public class RepositoryTest {

	@Value("${local.server.port:8080}")
	private String port;
	private final String root;
	private RestTemplate rest = new RestTemplate();

	@Parameters(name = "link {0}")
	public static Collection<Object[]> data() {
		String packageName = AlbumRepository.class.getPackage().getName();
		Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));

		Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
		ArrayList<Object[]> params = new ArrayList<>();
		for (Class<?> c : allClasses) {
			String s = c.getName().substring(packageName.length() + 1, c.getName().length() - "Repository".length())
					.toLowerCase() + "s";
			params.add(new Object[] { s });
		}
		return params;
	}

	private String name;

	public RepositoryTest(String name) throws Exception {
		this.name = name;
		new TestContextManager(getClass()).prepareTestInstance(this);
		this.root = "http://localhost:" + port;
	}
	
	@Test
	public void testGET_1() {
		get(root + "/" + name + "/1");
	}

	@Test
	public void testGET() {
		// Request.Get("http://localhost:8080/")
		get(root + "/" + name);
		// ResponseEntity<Map> entity = rest.getForEntity(url, Map.class);
		/*
		 * <200
		 * OK,{_links={playlists={href=http://localhost:8080/playlists{?
		 * page, size,sort}, templated=true},
		 * customers={href=http://localhost:8080/customers{?page,size,sort},
		 * templated=true},
		 * invoices={href=http://localhost:8080/invoices{?page,size,sort},
		 * templated=true},
		 * artists={href=http://localhost:8080/artists{?page,size,sort},
		 * templated=true},
		 * employees={href=http://localhost:8080/employees{?page,size,sort},
		 * templated=true},
		 * mediatypes={href=http://localhost:8080/mediatypes{?page,size,sort
		 * }, templated=true},
		 * genres={href=http://localhost:8080/genres{?page,size,sort},
		 * templated=true},
		 * albums={href=http://localhost:8080/albums{?page,size,sort},
		 * templated=true},
		 * profile={href=http://localhost:8080/alps}}},{Server=[Apache-
		 * Coyote/1. 1], Content-Type=[application/json;charset=UTF-8],
		 * Transfer-Encoding=[chunked], Date=[Sat, 19 Sep 2015 08:39:54
		 * GMT]}>
		 */
	}

	private void get(String pUrl) {
		String url = pUrl;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		try {
			ResponseEntity<Map> entity = rest.exchange(url, HttpMethod.GET, requestEntity, Map.class);
			System.out.println(entity);

			assertEquals(HttpStatus.OK, entity.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.err.println(e.getResponseBodyAsString());
			assertNull(e);
		}
	}
	
	@Autowired
	private AlbumRepository albumRepo;
	
	@Test
	public void test() {
		albumRepo.findAll();
	}
}
