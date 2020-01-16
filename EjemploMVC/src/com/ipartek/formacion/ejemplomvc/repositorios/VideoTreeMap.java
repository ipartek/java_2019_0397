package com.ipartek.formacion.ejemplomvc.repositorios;

import java.util.TreeMap;

import com.ipartek.formacion.ejemplomvc.modelos.Video;

public class VideoTreeMap implements Dao<Video> {
	private TreeMap<Long, Video> videos = new TreeMap<>();

	// SINGLETON
	private final static VideoTreeMap INSTANCIA = new VideoTreeMap();
	
	private VideoTreeMap() {
		agregar(new Video("Hyperventilate", "https://www.youtube.com/watch?v=SVGjOUnQZ58"));
		agregar(new Video("Clases de Reaggeton", "https://www.youtube.com/watch?v=yGA0jBOSX9w"));
	}
	
	public static VideoTreeMap getInstancia() { return INSTANCIA; }
	// FIN SINGLETON
	
	@Override
	public Iterable<Video> obtenerTodos() {
		return videos.values();
	}

	@Override
	public Video obtenerPorId(Long id) {
		return videos.get(id);
	}

	@Override
	public void agregar(Video video) {
		// Comprobamos si la colección está vacía y en ese caso ponemos el id a 1
		// En caso contrario ponemos el id al último id + 1
		Long id = videos.size() == 0 ? 1L : videos.lastKey() + 1;
		video.setId(id);
		videos.put(id, video);
	}

	@Override
	public void modificar(Video video) {
		//throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
		videos.put(video.getId(), video);
	}

	@Override
	public void borrar(Long id) {
		videos.remove(id);
	}
}
