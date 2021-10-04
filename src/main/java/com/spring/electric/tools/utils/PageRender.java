package com.spring.electric.tools.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int numeroElementosPorPaginas;
	private int paginaActual;
	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		numeroElementosPorPaginas = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1;
		this.paginas = new ArrayList<PageItem>();
		int desde;
		int hasta;

		if (totalPaginas <= numeroElementosPorPaginas) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if (paginaActual <= numeroElementosPorPaginas / 2) {
				desde = 1;
				hasta = numeroElementosPorPaginas;
			} else if (paginaActual >= totalPaginas - numeroElementosPorPaginas / 2) {
				desde = totalPaginas - numeroElementosPorPaginas + 1;
				hasta = numeroElementosPorPaginas;
			} else {
				desde = paginaActual - numeroElementosPorPaginas / 2;
				hasta = numeroElementosPorPaginas;
			}
		}

		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
