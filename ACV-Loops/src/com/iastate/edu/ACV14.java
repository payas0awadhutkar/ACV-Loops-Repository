package com.iastate.edu;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ACV14 {

	Map<Edge, CompleteEdge> edgemap = new HashMap<Edge, CompleteEdge>();
	
	public void paintContents(LayerDiagram layer, Graphics g, FigPainter painter, int x, int y, int level, Fig parent) {

        Iterator<Fig> figsIter;

        figsIter = (new ArrayList<Fig>(layer.contents)).iterator();

        while (figsIter.hasNext()) {
            Fig fig = figsIter.next();
            Object owner = fig.getOwner();
            Node n = null;
            
            Vector<Edge> edges = n.in;
            Iterator<Edge> edgeerator = edges.iterator();
            while (edgeerator.hasNext()) {
            	Edge edge = edgeerator.next();
                CompleteEdge cedge = edgemap.get(edge);
                if (cedge == null) {
                	cedge = new CompleteEdge(edge);
                    edgemap.put(edge, cedge);
                }
                cedge.addInNode(fig);
            }
            
                edges = n.out;
                edgeerator = edges.iterator();
                while (edgeerator.hasNext()) {
                    Edge edge = edgeerator.next();
                    CompleteEdge cedge = edgemap.get(edge);
                    if (cedge == null) {
                        cedge = new CompleteEdge(edge);
                        edgemap.put(edge, cedge);
                    }
                    cedge.addOutNode(fig);
                }
            // operations to paint figure

            Iterator<CompleteEdge> completeedgeerator = edgemap.values().iterator();
            while (completeedgeerator.hasNext()) {
                CompleteEdge next = completeedgeerator.next();
                if (next.isComplete()) {
                    next.drawIt(g, painter);
                    completeedgeerator.remove();
                }
            }

            String type = n.type;
            
            // Container is a special type.
            // Attacker can input diagrams which includes nodes of type "container"
            
            if (type != null && type.startsWith("container:")) {
                String containername = type.substring("container:".length());
                LayerDiagram ldg = new LayerDiagram();
                paintContents(ldg, g, painter, x, y, level + 1, fig);
            }
        }
    }
	
	class Fig {

		public int scale;

		public Object Owner;
		
		public Object getOwner() {
			return this.Owner;
		}
		
	}
	
	class LayerDiagram {

		public Collection<? extends Fig> contents;
		
	}
	
	class FigPainter {
		
	}
		
	public class CompleteEdge {
		
		Edge edge;
		
		public CompleteEdge(Edge e) {
			this.edge = e;
		}
		
		
		public boolean isComplete() {
			// stub
			return false;
		}

		public void addInNode(Fig fig) {
			// stub
			
		}
			
		public void addOutNode(Fig fig) {
			// stub
			
		}
		public void drawIt(Graphics g, FigPainter painter) {
			// stub
				
		}

	}

	
	class Node {

		public Vector<Edge> out;
		public Vector<Edge> in;
		public String type;
		
	}
	
	class Edge {
		
	}
}
