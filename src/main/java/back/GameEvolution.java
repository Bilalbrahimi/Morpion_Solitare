package back;

import java.util.ArrayList;
import java.util.List;






/**
 * 
 * @author bilal_brahimi
 *
 */

public class GameEvolution {
	
    public final static int GRID_SIZE = 16;

    
    private Version version;
    private int line_size;

    private Point[][] grid = new Point[GRID_SIZE][GRID_SIZE];
    private List<Line> all_list_lines = new ArrayList<>();


	/**
	 * 
	 * 
	 */
	public GameEvolution(int lineSize,Version version){
		
		this.setLineSize(lineSize);
		this.setVersion(version);
		this.setGrid(Grid.startingGrid(lineSize,GRID_SIZE));
	}


	public Point[][] getGrid() {
		return grid;
	}


	public void setGrid(Point[][] gameGrid) {
		this.grid = gameGrid;
	}

	public List<Line> getAllListLines() {
		return all_list_lines;
	}


	public Version getVersion() {
		return version;
	}


	public void setVersion(Version version) {
		this.version = version;
	}


	public int getLineSize() {
		return line_size;
	}


	public void setLineSize(int lineSize) {
		this.line_size = lineSize;
	}
	
	/**
	 * 
	 * @return PointLines
	 */
	public List<PlayablePoint> get_playable_points(){
    	List<PlayablePoint> listPointLines = new ArrayList<>();
    	PlayablePoint pl;
    	List<Line> listLines;

        for(int i =0; i < GRID_SIZE; i++){
            for(int j=0; j < GRID_SIZE; j++){
                if(grid[i][j].getState() == -1){
                	listLines = list_of_playable_lines(grid[i][j]);
                	if(listLines.size() > 0){
                		pl = new PlayablePoint(grid[i][j],listLines);
                		listPointLines.add(pl);
                	}
                }
            }
        }
        return listPointLines;
    }
	
	
	private List<Line> list_of_playable_lines(Point p){
		List<Line> list_lines= new ArrayList<>();
		int x = p.getX();
		int y = p.getY();
		
		Line lh = new Line(new Point(x-(line_size-1),y),new Point(x,y));
		Line ld1 = new Line(new Point(x-(line_size-1),y-(line_size-1)),new Point(x,y));
		Line ld2 = new Line(new Point(x-(line_size-1),y+(line_size-1)),new Point(x+(line_size-1),y-(line_size-1)));
		Line lv = new Line(new Point(x,y-(line_size-1)),new Point(x,y));
		
		
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				//int line_size_h = line_size;
				
				if(x_ < 0) {
					xx = 0;
					//line_size_h = line_size + x_;
				}else if (x_ >= GRID_SIZE - (line_size)) {
					xx= GRID_SIZE - (line_size);
				}
				
				
				//if(xx >= 0 && xx < GRID_SIZE-line_size+1 && yy >= 0 && yy < GRID_SIZE-line_size+1) {
					Point pp = new Point(xx,yy);
					
					if(lh.contain_Point(pp)){// horizontal
						boolean b = true;
						for(int i=xx; i<xx+line_size; i++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
							if(i >= 0 && i < GRID_SIZE) {
								if(this.grid[i][yy].getState() == -1 && i != x) {
									b = false;
								}
							
								
							}
						}
						if(b) {
							//System.out.println("-------xxx " + xx +" x+L "+ (xx+line_size_h-1) +" yyyyy "+ yy );
							list_lines.add(new Line(grid[xx][yy],grid[xx+line_size-1][yy]));
						}
						
						
						
					}
					/*
					else if(lv.contain_Point(pp)) { //vertical
						boolean b = true;
						for(int i=yy; i<yy+line_size; i++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
							if(i >= 0 && i < GRID_SIZE) {
								if(this.grid[xx][i].getState() == -1 && i != y) {
									b = false;
								}
							
							}
						}
						if(b) {
							System.out.println("-------y " + yy +" y+L "+ (yy+line_size-1) +" xx "+ xx );
							list_lines.add(new Line(grid[xx][yy],grid[xx][yy+line_size-1]));
						}
						
					}
					
					
					else if(ld1.contain_Point(pp)) { //diag1
						boolean b = true;
						for(int i= -line_size; i<0; i++) {
							if(this.grid[xx+i][yy+i].getState() == -1) {
								b = true;
							}
						}
						if(b) {
						
							list_lines.add(new Line(grid[xx][yy],grid[xx+line_size-1][yy+line_size-1]));
						}
						
						
					}else if(ld2.contain_Point(pp)) { //diag2
						boolean b = true;
						for(int i= 0; i<line_size; i++) {
							if(this.grid[xx-i][yy-i].getState() == -1) {
								b = true;
							}
						}
						if(b) {
							list_lines.add(new Line(grid[xx][yy],grid[xx-line_size-1][yy-line_size-1]));
						}
					}*/
				//}				
			}
		}
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				//int line_size_h = line_size;
				if(y_ < 0) {
					yy = 0;
				}else if (y_ >= GRID_SIZE - (line_size)) {
					yy= GRID_SIZE - (line_size);
				}
				
				
				//if(xx >= 0 && xx < GRID_SIZE-line_size+1 && yy >= 0 && yy < GRID_SIZE-line_size+1) {
					Point pp = new Point(xx,yy);
					

					if(lv.contain_Point(pp)) { //vertical
						boolean b = true;
						for(int i=yy; i<yy+line_size; i++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
							if(i >= 0 && i < GRID_SIZE) {
								if(this.grid[xx][i].getState() == -1 && i != y) {
									b = false;
								}
							
							}
						}
						if(b) {
							System.out.println("-------y " + yy +" y+L "+ (yy+line_size-1) +" xx "+ xx );
							list_lines.add(new Line(grid[xx][yy],grid[xx][yy+line_size-1]));
						}
						
					}
					
			
			}
		}
		
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				//int line_size_h = line_size;
				if(y_ < 0  || x_ < 0) {
					xx = 0;
					yy = 0;
				}else if (y_ >= GRID_SIZE - (line_size)  || (x_ >= GRID_SIZE - (line_size))) {
					xx= GRID_SIZE - (line_size);
					yy= GRID_SIZE - (line_size);
				}
				

				
				//if(xx >= 0 && xx < GRID_SIZE-line_size+1 && yy >= 0 && yy < GRID_SIZE-line_size+1) {
					Point pp = new Point(xx,yy);
					

					if(ld1.contain_Point(pp)) { //vertical
						boolean b = true;
						for(int i=yy; i<yy+line_size; i++) {
							for(int j=xx; j<xx+line_size; j++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
								if(i >= 0 && i < GRID_SIZE && j >= 0 && j < GRID_SIZE) {
									if(this.grid[j][i].getState() == -1 && i != y && j!=x) {
										b = false;
									}
								
								}
							}
						}
						if(b) {
							list_lines.add(new Line(grid[xx][yy],grid[xx+line_size-1][yy+line_size-1]));
						}
						
					}
					
			
			}
		}

		return list_lines;
		
		
	}
	
 	/**
 	 *
 	 * @param p Point
 	 * @param l Lines
 	 */
     public void change_state(Point p, Line l) {
     	this.grid[p.getX()][p.getY()].setState(p.getState());
     	this.all_list_lines.add(l);
     }
	
}
