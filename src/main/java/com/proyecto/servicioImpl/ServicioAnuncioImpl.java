package com.proyecto.servicioImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.proyecto.Session;
import com.proyecto.enums.StateEnum;
import com.proyecto.modelo.AnuncioVO;
import com.proyecto.modelo.UsuarioVO;
import com.proyecto.repositorio.AnuncioRepository;
import com.proyecto.servicio.ServicioAnuncio;
import com.proyecto.servicio.ServicioUsuario;

@Service
public class ServicioAnuncioImpl implements ServicioAnuncio {

	@Autowired
	private AnuncioRepository ar;
	
	@Autowired
	private Session session;
	
	@Autowired
	private ServicioUsuario su;


	//Al insertar un anuncio nuevo
	//le añadimos el id del usuario que lo inserta
	//y le ponemos definido en estado de EN VENTA
	@Override
	public <S extends AnuncioVO> S save(S entity, MultipartFile file) {
		UsuarioVO usuario = su.getById((int) session.get(Session.ID_USER));
		entity.setUsuario(usuario);

		if (entity.getEstado() == null || entity.getEstado().isEmpty()){ 
			entity.setEstado(StateEnum.EN_VENTA.getEstado());
		} 
	
		String url  = "";
		if (!file.isEmpty()) {
			try {
				RestTemplate restTemplate = new RestTemplate();
		
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				map.add("key", "6d207e02198a847aa98d0a2a901485a5");
				map.add("action", "upload");
				map.add("source", encodeFileToBase64Binary(file));
		
				HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(map, new HttpHeaders());
		
				ResponseEntity<String> response = restTemplate.postForEntity("https://freeimage.host/api/1/upload", body , String.class);
				
				//Esto contiene el JSON que me devulve freeimage pero en un String
				String bodyResponse = response.getBody();
				
				//Lo transformo a un JSONObject para poder navegar en el JSON de forma comoda
				JSONObject jsonObject =  (JSONObject) JSONValue.parse(bodyResponse);
				
				//Ahora busco dentro del JSON la url de la imagen
				url = ((JSONObject) jsonObject.get("image")).get("url").toString();
			} catch(Exception e) {
				System.out.println("Algo fue mal en la subida de imagen: " + e.getMessage());
			}
		}
		
		//si imagen esta vacia ponemos una por defecto
		if (url.isEmpty()) {
			url = "https://iili.io/hXJIwJ.jpg";
		}
		entity.setUrlImg(url);
		return ar.save(entity);
	}
	
	private String encodeFileToBase64Binary(MultipartFile multiPartFile) {
		String base64 = "";
		try {
	        InputStream inputStreamReader = multiPartFile.getInputStream();
	        byte[] bytes = new byte[inputStreamReader.available()];
	        inputStreamReader.read(bytes);
	        base64 = new String(Base64.encodeBase64(bytes), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64;
    }
	
//	private String guardarImagen(MultipartFile file) {
//		
//		String nameFile = "";
//		
//        //Comprobamos que no este vacia
//        if (!file.isEmpty()) {
//
//            // declaramos la ruta del archivo
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            Date fechaActual = new Date();
//            String nuevoNombre = session.getUserLoggedId() + "_" + String.valueOf(fechaActual.getTime()) + "_" + fileName;
//            
//            // Guardamos el archivo
//            try {
//            	String ruta ="src/main/resources/static/imagenes_anuncio/";
//            	//local:  String ruta ="src/main/resources/static/imagenes_anuncio/";
//                Path path = Paths.get(ruta + nuevoNombre);
//                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//                nameFile = "imagenes_anuncio/" + nuevoNombre;
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//
//        }
//
//        return nameFile;
//        
//	}
	
    /*UsuarioVO usuario = su.getById((int) session.get(Session.ID_USER));
	entity.setUsuario(usuario);

	if (entity.getEstado() == null || entity.getEstado().isEmpty()){ 
		entity.setEstado(StateEnum.EN_VENTA.getEstado());
	} 
	
	
	
	String imagen = guardarImagen(file);
	//si imagen esta vacia ponemos una por defecto
	if (imagen.isEmpty()) {
		imagen = "imagenes_anuncio/sinImagen.jpg";
	}
	entity.setUrlImg(imagen);
	return ar.save(entity);
}

private String guardarImagen(MultipartFile file) {
	
	String nameFile = "";
	
    //Comprobamos que no este vacia
    if (!file.isEmpty()) {

        // declaramos la ruta del archivo
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Date fechaActual = new Date();
        String nuevoNombre = session.getUserLoggedId() + "_" + String.valueOf(fechaActual.getTime()) + "_" + fileName;
        
        // Guardamos el archivo
        try {
        	String ruta ="src/main/resources/static/imagenes_anuncio/";
        	//local:  String ruta ="src/main/resources/static/imagenes_anuncio/";
            Path path = Paths.get(ruta + nuevoNombre);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            nameFile = "imagenes_anuncio/" + nuevoNombre;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    return nameFile;*/
	

	@Override
	public <S extends AnuncioVO> Optional<S> findOne(Example<S> example) {
		return ar.findOne(example);
	}

	@Override
	public Page<AnuncioVO> findAll(Pageable pageable) {
		return ar.findAll(pageable);
	}

	@Override
	public List<AnuncioVO> findAll() {
		return ar.findAll();
	}

	@Override
	public List<AnuncioVO> findAllAnunciosAjenos() {
		return ar.findAllAnunciosAjenos(session.getUserLoggedId());
	}

	@Override
	public List<AnuncioVO> findAll(Sort sort) {
		return ar.findAll(sort);
	}

	@Override
	public List<AnuncioVO> findAllById(Iterable<Integer> ids) {
		return ar.findAllById(ids);
	}

	@Override
	public AnuncioVO findById(Integer id) {
		AnuncioVO anuncio = null;
		Optional<AnuncioVO> optionalAnuncio = ar.findById(id);
		if(optionalAnuncio.isPresent()) {
			anuncio = optionalAnuncio.get();
		}
		return anuncio;
	}

	@Override
	public <S extends AnuncioVO> List<S> saveAll(Iterable<S> entities) {
		return ar.saveAll(entities);
	}

	@Override
	public void flush() {
		ar.flush();
	}

	@Override
	public <S extends AnuncioVO> S saveAndFlush(S entity) {
		return ar.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return ar.existsById(id);
	}

	@Override
	public <S extends AnuncioVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return ar.saveAllAndFlush(entities);
	}

	@Override
	public <S extends AnuncioVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return ar.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<AnuncioVO> entities) {
		ar.deleteInBatch(entities);
	}

	@Override
	public <S extends AnuncioVO> long count(Example<S> example) {
		return ar.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<AnuncioVO> entities) {
		ar.deleteAllInBatch(entities);
	}

	@Override
	public <S extends AnuncioVO> boolean exists(Example<S> example) {
		return ar.exists(example);
	}

	@Override
	public long count() {
		return ar.count();
	}

	@Override
	public void deleteById(Integer id) {
		ar.deleteById(id);
	}

	@Override
	public <S extends AnuncioVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return ar.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		ar.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(AnuncioVO entity) {
		ar.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		ar.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		ar.deleteAllInBatch();
	}

	@Override
	public AnuncioVO getOne(Integer id) {
		return ar.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends AnuncioVO> entities) {
		ar.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		ar.deleteAll();
	}

	@Override
	public AnuncioVO getById(Integer id) {
		return ar.getById(id);
	}

	@Override
	public <S extends AnuncioVO> List<S> findAll(Example<S> example) {
		return ar.findAll(example);
	}

	@Override
	public <S extends AnuncioVO> List<S> findAll(Example<S> example, Sort sort) {
		return ar.findAll(example, sort);
	}

	@Override
	public List<AnuncioVO> findByNombre(String nombre) {
		return ar.findByNombre (nombre);
	}
	
	@Override
	public List<AnuncioVO> findByAnuncio(String nombre) {
		return ar.findByAnuncio (nombre);
	}

	
	@Override
	public List<AnuncioVO> findByCategoria(String nombre) {
		return ar.findByCategoria(nombre);
	}


	@Override
	public List<AnuncioVO> findByUsuario() {
		return ar.findByUsuario(session.getUserLoggedId());
	}

	@Override
	public List<AnuncioVO> findAllLikesR() {
		return ar.findAllLikesR(session.getUserLoggedId());
	}

	@Override
	public List<AnuncioVO> findByIdUsuario(Integer idUsuario) {
		// TODO Auto-generated method stub
		return ar.findByIdUsuario(idUsuario);
	}

	
}
