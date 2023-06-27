package it.polito.tdp.nyc.model;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		super();
		this.dao = new NYCDao();
	}
	
	public void creaGrafo(String provider) {
		
		this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, this.dao.getVertici(provider));
		
		List<Arco> archi = this.dao.getArchi(provider);
		
		for(Arco a: archi) {
			this.grafo.addEdge(a.getC1(), a.getC2());
			Double dist = LatLngTool.distance(a.getPos1(), a.getPos2(), LengthUnit.KILOMETER);
			this.grafo.setEdgeWeight(a.getC1(), a.getC2(), dist);
			System.out.println(dist);
		}	
	}
	
	public List<String> getTendina(){
		return this.dao.getProvider();
	}

	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<String> getVertici(String provider) {

		List<String> lista = new LinkedList<>();
		
		for(String s: this.grafo.vertexSet()) {
			lista.add(s);
		}
		return lista;
	}
	
	public List<VerticeDist> getVicini(String provider, String city){
		/*
		se arco c1==city prendo c2 e dist
		se arco c2==city prendo c1 e dist
		*/
		List<VerticeDist> res = new LinkedList<>();
		List<Arco> archi = this.dao.getArchi(provider);
		List<String> vicini = Graphs.neighborListOf(grafo, city);
		
		for(Arco a: archi) {
			if(this.grafo.getEdge(a.getC1(), city) != null)
				res.add(new VerticeDist(a.getC1(), this.grafo.getEdgeWeight(this.grafo.getEdge(a.getC1(), city))));		
		}
		Collections.sort(res);
		return res;
	}
	
}
