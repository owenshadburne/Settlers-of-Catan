import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class BoardSetup {
	public static BoardSetup instance;
	private ArrayList<Tile> tiles;
	private static ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private ArrayList<Port> ports;
	private boolean hasSetEdges;
	private int tileWidth;
	private int tileHeight;
	private int width;
	private int height;
	private Deck deck;
	private static Settlement mostRecentSettlement;
	public BoardSetup(int w, int h, int tw, int th) {
		instance = this;
		tileHeight = th;
		tileWidth = tw;
		width = w;
		height = h;
		deck = GameFrame.getDeck();
		setUp();
	}
	public static ArrayList<Vertex> getVertices() {
		return vertices;
	}
	public void setUp() {
		hasSetEdges = false;
		tiles = new ArrayList<>();
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		ports = new ArrayList<Port>();
		// tiles.add(new Tile(resource));
		tiles.add(new Tile());

		for (int i = 0; i < 3; i++){
			tiles.add(new Tile(Resource.ORE)); //mountain
		}
		for (int i = 0; i < 3; i++){
			tiles.add(new Tile(Resource.CLAY)); //hill
		}
		for (int i = 0; i < 4; i++){
			tiles.add(new Tile(Resource.WHEAT)); //wheat
		}
		for (int i = 0; i < 4; i++){
			tiles.add(new Tile(Resource.WOOD));
		}
		for (int i = 0; i < 4; i++){
			tiles.add(new Tile(Resource.WOOL));
		}

		Collections.shuffle(tiles);

////		int location = 0;
//		for(int i = 0; i < tiles.size(); i++){
//		//	if(tiles.get(i).isDesert() == false) {
////				tiles.get(i).setRollValue(numbers[location]);
//				tiles.get(i).setRollValue(i);
////				location++;
//		//	}
//		}
		setLocation();
		setTiles(width, height);
		setVertices();
		setEdges();
		setPorts();
	}
	public void setLocation() {
		int[] numbers = new int[]{5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
		Tile t[][]= new Tile[][] {
			new Tile[] {tiles.get(0),tiles.get(2), tiles.get(4)},
			new Tile[] {tiles.get(6),tiles.get(8),tiles.get(10), tiles.get(12)},
			new Tile[] {tiles.get(14),tiles.get(15), tiles.get(16), tiles.get(17),tiles.get(18)},
			new Tile[] {tiles.get(7),tiles.get(9), tiles.get(11),tiles.get(13)},
			new Tile[] {tiles.get(1),tiles.get(3),tiles.get(5)},
		};
		
		//Algroithm
		int index = 0;
		for(int i =0; i < t[0].length; i++) {
			if(t[0][i].isDesert() == false) {
				t[0][i].setRollValue(numbers[index]);
				index++;
			}
		}
		if(t[1][3].isDesert() == false) {
			t[1][3].setRollValue(numbers[index]);
			index++;
		}
		if(t[2][4].isDesert() == false) {
			t[2][4].setRollValue(numbers[index]);
			index++;
		}
		if(t[3][3].isDesert() == false) {
			t[3][3].setRollValue(numbers[index]);
			index++;
		}
		for(int i =t[4].length-1; i >= 0; i--) {
			if(t[4][i].isDesert() == false) {
				t[4][i].setRollValue(numbers[index]);
				index++;
			}
		}
		if(t[3][0].isDesert() == false) {
			t[3][0].setRollValue(numbers[index]);
			index++;
		}
		if(t[2][0].isDesert() == false) {
			t[2][0].setRollValue(numbers[index]);
			index++;
		}
		for(int i =0; i < t[1].length-1; i++) {
			if(t[1][i].isDesert() == false) {
				t[1][i].setRollValue(numbers[index]);
				index++;
			}
		}
		if(t[2][3].isDesert() == false) {
			t[2][3].setRollValue(numbers[index]);
			index++;
		}
		for(int i =t[3].length-2; i >= 1; i--) {
			if(t[3][i].isDesert() == false) {
				t[3][i].setRollValue(numbers[index]);
				index++;
			}
		}
		if(t[2][1].isDesert() == false) {
			t[2][1].setRollValue(numbers[index]);
			index++;
		}
		if(t[2][2].isDesert() == false) {
			t[2][2].setRollValue(numbers[index]);
			index++;
		}
		
		//Checking 8 and 6 (there are 4 total)
		ArrayList<Tile> eight = new ArrayList<Tile>();
		for(int i =0; i < tiles.size(); i++) {
			if(tiles.get(i).getRollValue() == 8 ||tiles.get(i).getRollValue() == 6) {
				eight.add(tiles.get(i));
			}
		}
		ArrayList<Vertex> v1 = eight.get(0).getVertices();
		ArrayList<Vertex> v2 = eight.get(1).getVertices();
		ArrayList<Vertex> v3 = eight.get(2).getVertices();
		ArrayList<Vertex> v4 = eight.get(3).getVertices();
		
		for(int j = 0; j < v1.size(); j++) {
			Vertex v = v1.get(j);
			for(int l = 0; l < v2.size(); l++) {
				if(v.equals(v2.get(l))) {
					Collections.shuffle(tiles);
					setLocation();
				}
			}
			for(int l = 0; l < v3.size(); l++) {
				if(v.equals(v3.get(l))) {
					Collections.shuffle(tiles);
					setLocation();
				}
			}
			for(int l = 0; l < v4.size(); l++) {
				if(v.equals(v4.get(l))) {
					Collections.shuffle(tiles);
					setLocation();
				}
			}
		}
		for(int j = 0; j < v2.size(); j++) {
			Vertex v = v2.get(j);
			for(int l = 0; l < v3.size(); l++) {
				if(v.equals(v3.get(l))) {
					Collections.shuffle(tiles);
					setLocation();
				}
			}
			for(int l = 0; l < v4.size(); l++) {
				if(v.equals(v4.get(l))) {
					Collections.shuffle(tiles);
					setLocation();
				}
			}
		}
		for(int j = 0; j < v3.size(); j++) {
			Vertex v = v3.get(j);
	
			for(int l = 0; l < v4.size(); l++) {
				if(v.equals(v4.get(l))) {
					Collections.shuffle(tiles);
					setLocation();
				}
			}
		}
	}
	public void setTiles(int width, int height) {
		ArrayList<Tile> tiles = new ArrayList<Tile>(getTiles());
		BufferedImage image = null;
		Tile current = null;
		int[] position = new int[2];
		for(int i = 4; i <10; i += 2) {
			position = new int[2];
			current = tiles.get(0);
			current.setProp((int)(tileWidth/2),(int)( tileHeight/2));

			image = tiles.get(0).getImage();
			tiles.remove(0);
			position[0] =	width/12 * i;
			position[1] = 	(height/6) * (1);
			current.setPosition(position);

			position = new int[2];
			current = tiles.get(0);
			image = tiles.get(0).getImage();
			tiles.remove(0);
			position[0] =width/12 * i;
			position[1] = (height/6) * (5);
			current.setProp((int)(tileWidth/2),(int)( tileHeight/2));
			current.setPosition(position);
		}

		for(int l = 3; l < 10; l+=2) {
			position = new int[2];
			current = tiles.get(0);
			image = tiles.get(0).getImage();
			tiles.remove(0);
			position[0] =(width/12) * l;
			position[1] = (height/6) * (2);
			current.setProp((int)(tileWidth/2),(int)( tileHeight/2));
			current.setPosition(position);

			position = new int[2];
			current = tiles.get(0);
			image = tiles.get(0).getImage();
			tiles.remove(0);
			position[0] =(width/12) * l;
			position[1] = (height/6) * (4);
			current.setProp((int)(tileWidth/2),(int)( tileHeight/2));
			current.setPosition(position);
		}

		for(int k = 2; k <= 10; k += 2) {
			position = new int[2];
			current = tiles.get(0);
			image = tiles.get(0).getImage();
			tiles.remove(0);
			position[0] =(width/12) * k;
			position[1] = (height/6) * (3);
			current.setProp((int)(tileWidth/2),(int)( tileHeight/2));
			current.setPosition(position);
		}
	}
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public void setVertices() {
		for(int i = 0; i < 54; i++) {
			Vertex v = new Vertex(i);
			vertices.add(v);

			switch(i){
				case 0:
					tiles.get(0).addVertex(v);
					v.addTiles(tiles.get(0));
					break;
				case 1:
					tiles.get(0).addVertex(v);
					v.addTiles(tiles.get(0));
					break;
				case 2:
					tiles.get(0).addVertex(v);
					tiles.get(2).addVertex(v);
					v.addTiles(tiles.get(0));
					v.addTiles(tiles.get(2));
					break;
				case 3:
					tiles.get(2).addVertex(v);
					v.addTiles(tiles.get(2));
					break;
				case 4:
					tiles.get(2).addVertex(v);
					tiles.get(4).addVertex(v);
					v.addTiles(tiles.get(2));
					v.addTiles(tiles.get(4));
					break;
				case 5:
					tiles.get(4).addVertex(v);
					v.addTiles(tiles.get(4));
					break;
				case 6:
					tiles.get(4).addVertex(v);
					v.addTiles(tiles.get(4));
					break;
				case 7:
					tiles.get(6).addVertex(v);
					v.addTiles(tiles.get(6));
					break;
				case 8:
					tiles.get(0).addVertex(v);
					tiles.get(6).addVertex(v);
					v.addTiles(tiles.get(0));
					v.addTiles(tiles.get(6));
					break;
				case 9:
					tiles.get(0).addVertex(v);
					tiles.get(6).addVertex(v);
					tiles.get(8).addVertex(v);
					v.addTiles(tiles.get(0));
					v.addTiles(tiles.get(6));
					v.addTiles(tiles.get(8));
					break;
				case 10:
					tiles.get(0).addVertex(v);
					tiles.get(2).addVertex(v);
					tiles.get(8).addVertex(v);
					v.addTiles(tiles.get(0));
					v.addTiles(tiles.get(2));
					v.addTiles(tiles.get(8));
					break;
				case 11:
					tiles.get(2).addVertex(v);
					tiles.get(10).addVertex(v);
					tiles.get(8).addVertex(v);
					v.addTiles(tiles.get(2));
					v.addTiles(tiles.get(10));
					v.addTiles(tiles.get(8));
					break;
				case 12:
					tiles.get(2).addVertex(v);
					tiles.get(4).addVertex(v);
					tiles.get(10).addVertex(v);
					v.addTiles(tiles.get(2));
					v.addTiles(tiles.get(4));
					v.addTiles(tiles.get(10));
					break;
				case 13:
					tiles.get(4).addVertex(v);
					tiles.get(10).addVertex(v);
					tiles.get(12).addVertex(v);
					v.addTiles(tiles.get(4));
					v.addTiles(tiles.get(10));
					v.addTiles(tiles.get(12));
					break;
				case 14:
					tiles.get(4).addVertex(v);
					tiles.get(12).addVertex(v);
					v.addTiles(tiles.get(4));
					v.addTiles(tiles.get(12));
					break;
				case 15:
					tiles.get(12).addVertex(v);
					v.addTiles(tiles.get(12));
					break;
				case 16:
					tiles.get(14).addVertex(v);
					v.addTiles(tiles.get(14));
					break;
				case 17:
					tiles.get(14).addVertex(v);
					tiles.get(6).addVertex(v);
					v.addTiles(tiles.get(14));
					v.addTiles(tiles.get(6));
					break;
				case 18:
					tiles.get(14).addVertex(v);
					tiles.get(6).addVertex(v);
					tiles.get(15).addVertex(v);
					v.addTiles(tiles.get(14));
					v.addTiles(tiles.get(6));
					v.addTiles(tiles.get(15));
					break;
				case 19:
					tiles.get(15).addVertex(v);
					tiles.get(8).addVertex(v);
					tiles.get(6).addVertex(v);
					v.addTiles(tiles.get(15));
					v.addTiles(tiles.get(6));
					v.addTiles(tiles.get(8));
					break;
				case 20:
					tiles.get(15).addVertex(v);
					tiles.get(8).addVertex(v);
					tiles.get(16).addVertex(v);
					v.addTiles(tiles.get(15));
					v.addTiles(tiles.get(8));
					v.addTiles(tiles.get(16));
					break;
				case 21:
					tiles.get(8).addVertex(v);
					tiles.get(16).addVertex(v);
					tiles.get(10).addVertex(v);
					v.addTiles(tiles.get(8));
					v.addTiles(tiles.get(10));
					v.addTiles(tiles.get(16));
					break;
				case 22:
					tiles.get(10).addVertex(v);
					tiles.get(16).addVertex(v);
					tiles.get(17).addVertex(v);
					v.addTiles(tiles.get(10));
					v.addTiles(tiles.get(16));
					v.addTiles(tiles.get(17));
					break;
				case 23:
					tiles.get(10).addVertex(v);
					tiles.get(12).addVertex(v);
					tiles.get(17).addVertex(v);
					v.addTiles(tiles.get(10));
					v.addTiles(tiles.get(12));
					v.addTiles(tiles.get(17));
					break;
				case 24:
					tiles.get(17).addVertex(v);
					tiles.get(12).addVertex(v);
					tiles.get(18).addVertex(v);
					v.addTiles(tiles.get(17));
					v.addTiles(tiles.get(12));
					v.addTiles(tiles.get(18));
					break;
				case 25:
					tiles.get(12).addVertex(v);
					tiles.get(18).addVertex(v);
					v.addTiles(tiles.get(12));
					v.addTiles(tiles.get(18));
					break;
				case 26:
					tiles.get(18).addVertex(v);
					v.addTiles(tiles.get(18));
					break;
				case 27:
					tiles.get(14).addVertex(v);
					v.addTiles(tiles.get(14));
					break;
				case 28:
					tiles.get(14).addVertex(v);
					tiles.get(7).addVertex(v);
					v.addTiles(tiles.get(14));
					v.addTiles(tiles.get(7));
					break;
				case 29:
					tiles.get(14).addVertex(v);
					tiles.get(7).addVertex(v);
					tiles.get(15).addVertex(v);
					v.addTiles(tiles.get(14));
					v.addTiles(tiles.get(7));
					v.addTiles(tiles.get(15));
					break;
				case 30:
					tiles.get(15).addVertex(v);
					tiles.get(7).addVertex(v);
					tiles.get(9).addVertex(v);
					v.addTiles(tiles.get(15));
					v.addTiles(tiles.get(7));
					v.addTiles(tiles.get(9));
					break;
				case 31:
					tiles.get(15).addVertex(v);
					tiles.get(16).addVertex(v);
					tiles.get(9).addVertex(v);
					v.addTiles(tiles.get(15));
					v.addTiles(tiles.get(16));
					v.addTiles(tiles.get(9));
					break;
				case 32:
					tiles.get(9).addVertex(v);
					tiles.get(16).addVertex(v);
					tiles.get(11).addVertex(v);
					v.addTiles(tiles.get(9));
					v.addTiles(tiles.get(16));
					v.addTiles(tiles.get(11));
					break;
				case 33:
					tiles.get(11).addVertex(v);
					tiles.get(16).addVertex(v);
					tiles.get(17).addVertex(v);
					v.addTiles(tiles.get(11));
					v.addTiles(tiles.get(16));
					v.addTiles(tiles.get(17));
					break;
				case 34:
					tiles.get(11).addVertex(v);
					tiles.get(17).addVertex(v);
					tiles.get(13).addVertex(v);
					v.addTiles(tiles.get(11));
					v.addTiles(tiles.get(17));
					v.addTiles(tiles.get(13));
					break;
				case 35:
					tiles.get(17).addVertex(v);
					tiles.get(18).addVertex(v);
					tiles.get(13).addVertex(v);
					v.addTiles(tiles.get(17));
					v.addTiles(tiles.get(18));
					v.addTiles(tiles.get(13));
					break;
				case 36:
					tiles.get(13).addVertex(v);
					tiles.get(18).addVertex(v);
					v.addTiles(tiles.get(13));
					v.addTiles(tiles.get(18));
					break;
				case 37:
					tiles.get(18).addVertex(v);
					v.addTiles(tiles.get(18));
					break;
				case 38:
					tiles.get(7).addVertex(v);
					v.addTiles(tiles.get(7));
					break;
				case 39:
					tiles.get(7).addVertex(v);
					tiles.get(1).addVertex(v);
					v.addTiles(tiles.get(7));
					v.addTiles(tiles.get(1));
					break;
				case 40:
					tiles.get(9).addVertex(v);
					tiles.get(7).addVertex(v);
					tiles.get(1).addVertex(v);
					v.addTiles(tiles.get(9));
					v.addTiles(tiles.get(7));
					v.addTiles(tiles.get(1));
					break;
				case 41:
					tiles.get(9).addVertex(v);
					tiles.get(1).addVertex(v);
					tiles.get(3).addVertex(v);
					v.addTiles(tiles.get(9));
					v.addTiles(tiles.get(1));
					v.addTiles(tiles.get(3));
					break;
				case 42:
					tiles.get(11).addVertex(v);
					tiles.get(3).addVertex(v);
					tiles.get(9).addVertex(v);
					v.addTiles(tiles.get(11));
					v.addTiles(tiles.get(3));
					v.addTiles(tiles.get(9));
					break;
				case 43:
					tiles.get(3).addVertex(v);
					tiles.get(11).addVertex(v);
					tiles.get(5).addVertex(v);
					v.addTiles(tiles.get(11));
					v.addTiles(tiles.get(3));
					v.addTiles(tiles.get(5));
					break;
				case 44:
					tiles.get(5).addVertex(v);
					tiles.get(11).addVertex(v);
					tiles.get(13).addVertex(v);
					v.addTiles(tiles.get(5));
					v.addTiles(tiles.get(11));
					v.addTiles(tiles.get(13));
					break;
				case 45:
					tiles.get(13).addVertex(v);
					tiles.get(5).addVertex(v);
					v.addTiles(tiles.get(13));
					v.addTiles(tiles.get(5));
					break;
				case 46:
					tiles.get(13).addVertex(v);
					v.addTiles(tiles.get(13));
					break;
				case 47:
					tiles.get(1).addVertex(v);
					v.addTiles(tiles.get(1));
					break;
				case 48:
					tiles.get(1).addVertex(v);
					v.addTiles(tiles.get(1));
					break;
				case 49:
					tiles.get(3).addVertex(v);
					tiles.get(1).addVertex(v);
					v.addTiles(tiles.get(3));
					v.addTiles(tiles.get(1));
					break;
				case 50:
					tiles.get(3).addVertex(v);
					v.addTiles(tiles.get(3));
					break;
				case 51:
					tiles.get(3).addVertex(v);
					tiles.get(5).addVertex(v);
					v.addTiles(tiles.get(3));
					v.addTiles(tiles.get(5));
					break;
				case 52:
					tiles.get(5).addVertex(v);
					v.addTiles(tiles.get(5));
					break;
				case 53:
					tiles.get(5).addVertex(v);
					v.addTiles(tiles.get(5));
					break;
			}
		}

	}
	/*     1
	 *  0     2
	 *
	 *
	 *  3     5
	 *     4
	 * */
	public void setVerticeLocations() {

		for(Vertex v : vertices) {
			ArrayList<Tile> vTiles = v.getTiles();
			int loc = vTiles.get(0).getVertices().indexOf(v);
			int[] position = new int[2];
			switch(loc) {
				case 0:
					position[0] = vTiles.get(0).getPosition()[0] - tileWidth/2- width/70;
					position[1] = (int)(vTiles.get(0).getPosition()[1] - tileHeight/4);
				break;
				case 1:
					position[0] =vTiles.get(0).getPosition()[0] -width/80;
					position[1] = (int)(vTiles.get(0).getPosition()[1] - tileHeight/1.8);
					break;
				case 2:
					position[0] = vTiles.get(0).getPosition()[0] + tileWidth/2- width/80;
					position[1] = (int)(vTiles.get(0).getPosition()[1] - tileHeight/4);
					break;
				case 3:
					position[0] = vTiles.get(0).getPosition()[0] - tileWidth/2- width/70;
					position[1] = (int)(vTiles.get(0).getPosition()[1] + tileHeight/6);
					break;
				case 4:
					position[0] =vTiles.get(0).getPosition()[0] -width/70;
					position[1] =(int)(vTiles.get(0).getPosition()[1] + tileHeight/2.4);
					break;
				case 5:
					position[0] = vTiles.get(0).getPosition()[0] + tileWidth/2- width/80;
					position[1] = (int)(vTiles.get(0).getPosition()[1] + tileHeight/5);
					break;
			}
			v.setPosition(position);
		}

	}

	public void setPorts() {
		Port p1 = new Port(vertices.get(2), vertices.get(3), tileWidth, tileHeight);
		vertices.get(2).setPort(p1);
		vertices.get(3).setPort(p1);

		Port p2 = new Port(vertices.get(5), vertices.get(6), tileWidth, tileHeight);
		vertices.get(5).setPort(p2);
		vertices.get(6).setPort(p2);

		Port p3 = new Port(vertices.get(7), vertices.get(8), tileWidth, tileHeight);
		vertices.get(7).setPort(p3);
		vertices.get(8).setPort(p3);

		Port p4 = new Port(vertices.get(15), vertices.get(25), tileWidth, tileHeight);
		vertices.get(15).setPort(p4);
		vertices.get(25).setPort(p4);

		Port p5 = new Port(vertices.get(16), vertices.get(27), tileWidth, tileHeight);
		vertices.get(16).setPort(p5);
		vertices.get(27).setPort(p5);

		Port p6 = new Port(vertices.get(36), vertices.get(46), tileWidth, tileHeight);
		vertices.get(36).setPort(p6);
		vertices.get(46).setPort(p6);

		Port p7 = new Port(vertices.get(38), vertices.get(39), tileWidth, tileHeight);
		vertices.get(38).setPort(p7);
		vertices.get(39).setPort(p7);

		Port p8 = new Port(vertices.get(49), vertices.get(50), tileWidth, tileHeight);
		vertices.get(49).setPort(p8);
		vertices.get(50).setPort(p8);

		Port p9 = new Port(vertices.get(52), vertices.get(53), tileWidth, tileHeight);
		vertices.get(52).setPort(p9);
		vertices.get(53).setPort(p9);

		ports.add(p1);
		ports.add(p2);
		ports.add(p3);
		ports.add(p4);
		ports.add(p5);
		ports.add(p6);
		ports.add(p7);
		ports.add(p8);
		ports.add(p9);

		Collections.shuffle(ports);

		int[] ratio = new int[2];
		ratio[0] = 2;
		ratio[1] = 1;
		ports.get(0).setType(Resource.WOOL, ratio);
		ports.get(1).setType(Resource.WOOD, ratio);
		ports.get(2).setType(Resource.ORE, ratio);
		ports.get(3).setType(Resource.CLAY, ratio);
		ports.get(4).setType(Resource.WHEAT, ratio);
	}
	public ArrayList<Port> getPorts(){
		return ports;
	}

	public void setProp(int w, int h, int tw, int th) {
		width = w;
		height = h;
		tileWidth = tw;
		tileHeight = th;
		this.setVerticeLocations();
		for(int i = 0; i< ports.size(); i++) {
			ports.get(i).setProp(tw, th,w,h);
		}

	}

	public void setEdges() {
		Edge e = null;
		for(int i = 0; i < vertices.size(); i++) {
			Vertex v1 = vertices.get(i);
			Vertex v2 = null;
			if( i +1 <54) {
				v2 = vertices.get(i + 1);
			} else {
				v2 = vertices.get(45);
			}
			e = new Edge(v1, v2);
			if(i ==6 || i  == 15 || i  == 26 || i  ==37 || i  == 46 ) {
				//nothing
			}else {
				v1.addEdge(e);
				v2.addEdge(e);
				edges.add(e);
			}
		}
		for(int l = 0; l<=6; l += 2) {
			Vertex v1 = vertices.get(l);
			Vertex v2 = vertices.get(l + 8);
			e = new Edge(v1, v2);
			v1.addEdge(e);
			v2.addEdge(e);
			edges.add(e);
			Vertex v3 = vertices.get(l+39);
			Vertex v4 = vertices.get(l+47);
			Edge e2 = new Edge(v3, v4);
			v3.addEdge(e2);
			v4.addEdge(e2);
			edges.add(e2);
		}
		for(int g =7; g <= 15; g += 2) {
			Vertex v1 = vertices.get(g);
			Vertex v2 = vertices.get(g + 10);
			e = new Edge(v1, v2);
			v1.addEdge(e);
			v2.addEdge(e);
			edges.add(e);
			Vertex v3 = vertices.get(g+28 -7);
			Vertex v4 = vertices.get(g+38 -7);
			Edge e2 = new Edge(v3, v4);
			v3.addEdge(e2);
			v4.addEdge(e2);
			edges.add(e2);
		}
		for(int y = 16; y  <=26; y+= 2) {
			Vertex v1 = vertices.get(y);
			Vertex v2 = vertices.get(y + 11);
			e = new Edge(v1, v2);
			v1.addEdge(e);
			v2.addEdge(e);
			edges.add(e);
		}

		for(int f = 0; f < edges.size(); f++) {
			edges.get(f).setIndex(f);
			for(int j = f+1; j < edges.size() -1; j++) {
				if(edges.get(f).isEqual(edges.get(j))) {
					edges.remove(f);
				}
			}
		}
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void paintRoads(Graphics g) {
		for(Edge e: edges) {
			e.draw(g);
		}
	}

	public void paintForBuildingModes(Graphics g) {
		if(BoardPanel.isBuildingRoad()) {
			for(Edge e: edges) {
				if(e.validRoadPlacement()) {
					e.highlight(g);
				}
			}
		}
		else if(BoardPanel.isBuildingSettlement()) {
			for(Vertex v: vertices) {
				if(v.validSettlementDistanceCheck() && v.validSettlementConnectionCheck()) {
					v.highlight(g);
				}
				else if(v.validSettlementDistanceCheck() && GameFrame.startOfGame) {
					v.highlight(g);
				}
			}
		}
	}
	public void checkClicks(int x, int y) {
		Player player = GameFrame.currentPlayer();
		Hand hand = player.getHand();
		//Add checks for validity of placements!!!!
		if(BoardPanel.isBuildingRoad()) {
			for(Edge e: edges) {
				if(e.getRoad() == null && e.checkIfClicked(x, y) && e.validRoadPlacement()) {
					e.setRoad(new Road(player.getColour()));
					//System.out.println(e.getRoad().toString());
					BoardPanel.isBuildingRoad(false);
					if(GameFrame.startOfGame) {
						BoardPanel.isBuildingSettlement(true);
						GameFrame.snakeTurn();
					}
					else if(GameFrame.roadDevelopmentCard) {
						GameFrame.roadsLeftToBuild--;
						if(GameFrame.roadsLeftToBuild > 0) {
							BoardPanel.isBuildingRoad(true);
						}
					}
					else {
						deck.add(hand.remove(Resource.WOOD));
						deck.add(hand.remove(Resource.CLAY));
					}
					player.roadsLeft--;
					GameFrame.repaintAllPanels();
					break;
				}
			}
		}
		else if(BoardPanel.isBuildingSettlement()) {
			for(Vertex v: vertices) {
				if(v.getSettlement() == null && v.checkIfClicked(x, y)) {
					if(v.validSettlementDistanceCheck()) {
						if(!GameFrame.startOfGame && v.validSettlementConnectionCheck()) {
							Settlement newestSettlement = new Settlement(player.getColour(), false);
							v.setSettlement(newestSettlement);
							BoardPanel.isBuildingSettlement(false);
							deck.add(hand.remove(Resource.WOOD));
							deck.add(hand.remove(Resource.ORE));
							deck.add(hand.remove(Resource.WHEAT));
							deck.add(hand.remove(Resource.WOOL));
							setMostRecentSettlement(newestSettlement);
							player.settlementsLeft--;
							GameFrame.repaintAllPanels();
							break;
						}
						else if(GameFrame.startOfGame){
							Settlement newestSettlement = new Settlement(player.getColour(), false);
							v.setSettlement(newestSettlement);
							BoardPanel.isBuildingSettlement(false);
							BoardPanel.isBuildingRoad(true);
							setMostRecentSettlement(newestSettlement);
							player.settlementsLeft--;
							
							for(Tile t: v.getTiles()) {
								Resource r = t.getResource();
								if(r != Resource.DESERT) {
									hand.add(deck.remove(t.getResource()));
								}
							}
							
							GameFrame.repaintAllPanels();
							break;
						}
					}
				}
			}
		}
		else if(BoardPanel.isBuildingCity()) {
			for(Vertex v: vertices) {
				if(v.getSettlement() != null && v.checkIfClicked(x, y) && !v.getSettlement().isCity()) {
					if(v.getSettlement().getColour() == player.getColour()) {
						v.setSettlement(new Settlement(player.getColour(), true));
						BoardPanel.isBuildingCity(false);
						deck.add(hand.remove(Resource.WHEAT));
						deck.add(hand.remove(Resource.WHEAT));
						deck.add(hand.remove(Resource.ORE));
						deck.add(hand.remove(Resource.ORE));
						deck.add(hand.remove(Resource.ORE));
						player.settlementsLeft++;
						player.citiesLeft--;
						GameFrame.repaintAllPanels();
						break;
					}
				}
			}
		}
		if(BoardPanel.robberActive) {
			for(Tile tile: tiles) {
				if(tile.checkIfClicked(x, y) && !tile.hasThief() && tile.validRobber()) {
					tile.setThiefLocation(true);
					for(Tile previousRobberLoc: tiles) {
						if(previousRobberLoc.hasThief() && previousRobberLoc != tile) {
							previousRobberLoc.setThiefLocation(false);
						}
					}
					BoardPanel.robberActive = false;
					if(GameFrame.numOfDiscardPanels <= 0) {
						GameFrame.lockAllButtons = false;
					}
					steal(tile);
				}
			}
		}
		GameFrame.repaintAllPanels();
	}
	private void steal(Tile tile) {
		Player currentPlayer = GameFrame.currentPlayer();
		ArrayList<Resource> defaultResources = new ArrayList<Resource>();
		defaultResources.add(Resource.ORE);
		defaultResources.add(Resource.WHEAT);
		defaultResources.add(Resource.WOOD);
		defaultResources.add(Resource.WOOL);
		defaultResources.add(Resource.CLAY);

		for(Vertex v: tile.getVertices()) {
			if(v.getSettlement() != null) {
				Player player = GameFrame.getPlayer(v.getSettlement().getColour());
				if(player != currentPlayer) {
					Resource toSteal = null;
					ArrayList<Resource> stealList = defaultResources;
					Collections.shuffle(stealList);
					while(toSteal == null && stealList.size() > 0) {
						toSteal = player.getHand().remove(stealList.remove(0));
					}
					if(toSteal != null) {
						currentPlayer.getHand().add(toSteal);
					}
					//System.out.println("STOLEN: " + (toSteal != null ? toSteal.toString() : "NULL"));
					//System.out.println("From player " + player.getColour().toString());
				}
			}
		}
	}
	public static void setMostRecentSettlement(Settlement s) {
		mostRecentSettlement = s;
	}
	public static Settlement getMostRecentSettlement() {
		return mostRecentSettlement;
	}
	public ArrayList<Resource> distribution(int roll) {
		ArrayList<Resource> distributedResources = new ArrayList<>();
		for(Tile tile: tiles) {
			if(tile.getRollValue() == roll)  {
				ArrayList<Resource> temp = tile.distribution();
				if(temp != null && temp.size() > 0) {
					distributedResources.addAll(temp);
				}
			}
		}
		return distributedResources;
	}

	public void robber(Graphics g) {
		GameFrame.startOfGame = true;
		moveRobber(g);
	}
	private void moveRobber(Graphics g) {
		for(Tile tile: tiles) {
			if(!tile.hasThief() && tile.validRobber()) {
				tile.hightlight(g);
			}
		}
		GameFrame.startOfGame = false;
	}

	public void checkForLongestRoad() {
		resetResults();

		ArrayList<Edge> checkedEdges = new ArrayList<>();
		for(Edge e: edges) {
			if(!checkedEdges.contains(e)) {
				ArrayList<Edge> returned = visitEdge(e);
				if(e.getRoad() != null) {
					determineLongest(e.getRoad().getColour(), returned.size());
				}
				addToList(checkedEdges, returned);
			}
		}
		compileResults();
	}
	private void addToList(ArrayList<Edge> to, ArrayList<Edge> from) {
		if(to == from) {
			return;
		}
		for(Edge e: from) {
			if(!to.contains(e)) {
				to.add(e);
			}
		}
	}
	private void determineLongest(Colour colour, int length) {
		Player player = GameFrame.getPlayer(colour);
		player.longestRoadCount = Math.max(player.longestRoadCount, length);
	}
	private void compileResults() {
		int max = 0;
		int numWithMax = 0;
		Player[] players = GameFrame.players;
		for(Player player: players) {
			max = Math.max(max, player.longestRoadCount);
			player.setLongRoad(false);
		}
		if(max >= 5) {
			for(Player player: players) {
				if(player.longestRoadCount == max) {
					numWithMax++;
				}
			}
			if(numWithMax == 1) {
				for(Player player: players) {
					if(player.longestRoadCount == max) {
						//System.out.println(player.toString() + " has the longest road");
						player.setLongRoad(true);
					}
				}
			}
		}
		//System.out.println(max);
	}
	private void resetResults() {
		for(Player player: GameFrame.players) {
			player.longestRoadCount = 0;
		}
	}
	private ArrayList<Edge> visitEdge(Edge e) {
		ArrayList<Edge> visited = new ArrayList<>();
		visited.add(e);
		if(e.getRoad() != null) {
			Colour colour = e.getRoad().getColour();
			print("Start with colour " + colour);

			//Left
			print("First left");
			addToList(visited, visitEdge(e.getVertices().get(0), visited, colour));
			//Right
			print("Then right");
			addToList(visited, visitEdge(e.getVertices().get(1), visited, colour));
		}
		return visited;
	}
	private ArrayList<Edge> visitEdge(Vertex v, ArrayList<Edge> visited, Colour colour) {
		print(visited.toString());
		if(v.getSettlement() == null || v.getSettlement().getColour() == colour) {
			//Case 1
			if(v.getEdges().size() == 2) {
				Edge left = v.getEdges().get(0);
				Edge right = v.getEdges().get(1);

				//Left
				if(!visited.contains(left)) {
					print("Going left, case 1");
					addToList(visited, visitEdge(left, visited, colour));
				}
				//Right
				if(!visited.contains(right)) {
					print("Going right, case 1");
					addToList(visited, visitEdge(right, visited, colour));
				}
			}
			else if(v.getEdges().size() == 3) {
				Edge left = v.getEdges().get(0);
				Edge center = v.getEdges().get(1);
				Edge right = v.getEdges().get(2);

				//Left
				if(!visited.contains(left)) {
					print("Going left, case 2");
					addToList(visited, visitEdge(left, visited, colour));
				}
				//Center
				if(!visited.contains(center)) {
					print("Going center, case 2");
					addToList(visited, visitEdge(center, visited, colour));
				}
				//Right
				if(!visited.contains(right)) {
					print("Going right, case 2");
					addToList(visited, visitEdge(right, visited, colour));
				}
			}
		}

		return visited;
	}
	private ArrayList<Edge> visitEdge(Edge e, ArrayList<Edge> visited, Colour colour) {
		if(!visited.contains(e)) {
			if(e.getRoad() != null && e.getRoad().getColour() == colour) {
				print(e + " added");
				visited.add(e);

				//Left
				print("Again left");
				addToList(visited, visitEdge(e.getVertices().get(0), visited, colour));
				//Right
				print("Again right");
				addToList(visited, visitEdge(e.getVertices().get(1), visited, colour));

			}
			else {
				print(visited + " does not contain " + e + ", but it does not pass the conditions");
			}
		}
		else {
			print(visited + " contains " + e);
		}
		return visited;
	}
	private void print(String m) {
		//System.out.println(m);
	}
}
