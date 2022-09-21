/**
 * Name: Alankrit Jacinth Moses
 * Class: CSC 335
 * Description: This class is the front-end/GUI handler, which connects
 * 				the user to the back-end programs.
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import java.util.List;
import java.util.*;


public class GUI
{
	public static void main(String args[])
	{
		Display display = new Display();
		Shell sh = new Shell(display);
		Board b = new Board();
		Image[][] img = new Image[6][6];
		
		menu(display, sh, b);
		
		sh.setSize(900,700);
		sh.open();
		
		while( !sh.isDisposed())
		{
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	/**
	 * This function is responsible for creating the menu of the Game.
	 * @param display
	 * @param sh
	 * @param b
	 */
	public static void menu(Display display, Shell sh, Board b)
	{
		Canvas canvas = new Canvas(sh,SWT.NONE);
		canvas.setSize(900,700);
		
		Label l = new Label(canvas,SWT.NONE);
		l.setText("CONCENTRATION GAME");
		l.setBounds(125,50,630,70);
		l.setFont( new Font(display,"Comic Sans MS", 30, SWT.BOLD) );
		
		Label l1 = new Label(canvas,SWT.None);
		l1.setText("By Alankrit Moses");
		l1.setBounds(330,150,240,100);
		l1.setFont( new Font(display,"Comic Sans MS", 16, SWT.BOLD ) );
		
		Label l2 = new Label(canvas,SWT.None);
		l2.setText("Number of Players:");
		l2.setBounds(320,250,190,100);
		l2.setFont( new Font(display,"Comic Sans MS", 12, SWT.BOLD ) );
		
		Text t = new Text(canvas,SWT.None);
		t.setText("1");
		t.setBounds(530,257,50,20);
		
		Label l3 = new Label(canvas,SWT.None);
		l3.setText("Select Category:");
		l3.setBounds(320,350,170,50);
		l3.setFont( new Font(display,"Comic Sans MS", 12, SWT.BOLD ) );
		
		
		Button radio = new Button(canvas,SWT.RADIO);
		radio.setBounds(500,347,200,40);
		radio.setText("Animals");
		radio.setFont( new Font(display,"Comic Sans MS", 10, SWT.BOLD ) );
		
		Button radio1 = new Button(canvas,SWT.RADIO);
		radio1.setBounds(0,40,200,30);
		radio1.setBounds(500,387,200,30);
		radio1.setText("Fruits");
		radio1.setFont( new Font(display,"Comic Sans MS", 10, SWT.BOLD ) );
		
		Button oneFlip = new Button(canvas,SWT.CHECK);
		oneFlip.setBounds(0,40,200,30);
		oneFlip.setBounds(390,437,200,30);
		oneFlip.setText("ONE FLIP");
		oneFlip.setFont( new Font(display,"Comic Sans MS", 12, SWT.BOLD ) );
		
		Button start = new Button(canvas,SWT.PUSH);
		start.setBounds(400,500,100,40);
		start.setFont( new Font(display,"Italiano", 10, SWT.BOLD ) );
		start.setText("Start Game");
		start.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				String selection = "Fruits";
				if(radio.getSelection())
					selection = "Animals";
				b.game(Integer.parseInt(t.getText()),selection,oneFlip.getSelection());
				game(sh,display,b);
			}
		});
	}
	
	/**
	 * This function is responsible for creating the main part of the game.
	 * @param sh
	 * @param display
	 * @param b
	 */
	public static void game(Shell sh, Display display, Board b)
	{
		Color white = new Color(display,255,255,255);
		Color blue = new Color(display,63,72,204);
		Color lightBlue = new Color(display,195,248,255);
		Color beige = new Color(display,255,246,197);
		Color yellow = new Color(display,255,235,173);
		sh.getChildren()[0].dispose();
		//Image i = new Image(display,"cool-comic-pattern.jpg");
		Canvas canvas = new Canvas(sh,SWT.NONE);
		canvas.setSize(900,700);
		
		Label l = new Label(canvas,SWT.NONE);
		l.setText("CONCENTRATION GAME");
		l.setBounds(230,10,630,70);
		l.setFont( new Font(display,"Comic Sans MS", 30, SWT.BOLD) );
		l.setForeground(beige);
		l.setBackground(blue);
		
		Composite turn = new Composite(canvas,SWT.NONE);
		turn.setBounds(0,80,210,250);
		turn.setBackground(blue);
		Label l1 = new Label(turn,SWT.CENTER);
		l1.setText("Turn:\n\nPlayer 1");
		l1.setBounds(0,40,210,250);
		l1.setFont( new Font(display,"Comic Sans MS", 20, SWT.BOLD) );
		l1.setForeground(white);
		l1.setBackground(blue);
		
		Label made = new Label(canvas,SWT.NONE);
		made.setText("Made By:\nAlankrit Moses");
		made.setBounds(20,10,180,70);
		made.setFont( new Font(display,"Comic Sans MS",14, SWT.BOLD) );
		made.setForeground(blue);
		made.setBackground(lightBlue);
		
		Label score = new Label(canvas,SWT.NONE);
		score.setBounds(20,390,180,370);
		score.setText("LEADERBOARD\n\n1.)\n\n2.)\n\n3.)");
		score.setBackground(lightBlue);
		score.setFont( new Font(display,"Comic Sans MS", 14, SWT.BOLD) );
		
		Label list[][] = new Label[6][6];
		
		list[5][0] = l1;
		list[5][1] = score;
		
		for(int x=0;x<5;x++)
		{
			for(int y=0;y<6;y++)
			{
				list[x][y] = new Label(canvas,SWT.BORDER);
				list[x][y].addMouseListener( new canvasML(110*y,110*x,canvas,display,b,list));
			}
		}
		
		addImages(canvas,b,display,list);
		canvas.addPaintListener(new PaintListener() {
	        @Override
	        public void paintControl(PaintEvent e) {
	        	e.gc.setBackground(blue);
	        	e.gc.fillRectangle(0,0, 900, 80);
	        	e.gc.setBackground(blue);
	        	e.gc.fillRectangle(0,0, 210, 700);
	        	e.gc.setBackground(lightBlue);
	        	e.gc.fillRectangle(0,0,210,80);
	        	e.gc.fillRectangle(0, 330, 210, 370);
	        }
		});
	}

	/**
	 * This function is responsible for adding/updating the card images on the canvas.
	 * @param canvas
	 * @param b
	 * @param display
	 * @param list
	 */
	public static void addImages(Canvas canvas, Board b, Display display, Label[][] list) 
	{
		String deck[][] = b.getImages();
		int basey = 100;
		for(int x=0; x<5;x++)
		{
			int basex = 220;
			for(int y=0;y<6;y++)
			{
				list[x][y].setBounds(basex,basey,100,100);
				list[x][y].setImage(new Image(display,deck[x][y]+".jpg"));
				basex+=110;
			}
			basey+=110;
		}
	}

	/**
	 * This function updates the GUI responsible for 
	 * indicating the leaderboard and who's turn to play. 
	 * @param b
	 * @param list
	 */
	public static void updateGUI(Board b,Label[][] list) {
		list[5][0].setText("Turn:\n\n"+b.getCurrentPlayer());
		list[5][1].setText(b.leaderBoard());
	}

	/**
	 * This function is responsible for creating 
	 * the end screen and deleting the images
	 * @param canvas
	 * @param list
	 */
	public static void gameOver(Display display,Canvas canvas, Label[][] list) {
		for(int x=0;x<5;x++)
			for(int y=0;y<6;y++)
				list[x][y].dispose();
		
		Label l = new Label(canvas,SWT.NONE);
		l.setText("GAME OVER!");
		l.setBounds(350,300,630,70);
		l.setFont( new Font(display,"Comic Sans MS", 30, SWT.BOLD) );
	}
	
}
	
class canvasML implements MouseListener
{
	private int x,y;
	private Canvas canvas;
	private Display display;
	private Board b;
	private Label[][] list;
	/**
	 * Constructor for class implementing MouseListener for Image labels.
	 * @param x
	 * @param y
	 * @param canvas
	 * @param display
	 * @param b
	 * @param list
	 */
	public canvasML(int x, int y, Canvas canvas, Display display, Board b, Label[][] list)
	{
		this.x = x;
		this.y = y;
		this.canvas = canvas;
		this.display = display;
		this.b = b;
		this.list = list;
	}
	@Override
	public void mouseDown(MouseEvent e) {}

	/**
	 * This function is executed when the mouse button is lifted.
	 */
	@Override
	public synchronized void mouseUp(MouseEvent e) {
		int mouseX = e.x + x;
		int mouseY = e.y + y;
		int x_index=-1,y_index=-1;
		if(mouseY>=0 && mouseY<=100)
			x_index = 0;
		else if(mouseY>=110 && mouseY<=210)
			x_index = 1;
		else if(mouseY>=220 && mouseY<=320)
			x_index = 2;
		else if(mouseY>=330 && mouseY<=430)
			x_index = 3;
		else if(mouseY>=440 && mouseY<=540)
			x_index = 4;
		
		if(mouseX>=0 && mouseX<=100)
			y_index = 0;
		else if(mouseX>=110 && mouseX<=210)
			y_index = 1;
		else if(mouseX>=220 && mouseX<=320)
			y_index = 2;
		else if(mouseX>=330 && mouseX<=430)
			y_index = 3;
		else if(mouseX>=440 && mouseX<=540)
			y_index = 4;
		else if(mouseX>=550 && mouseX<=650)
			y_index = 5;
		
		if (x_index>=0 && y_index>=0)
		{
			b.flip(x_index, y_index);
			GUI.addImages(canvas,b,display, list);
			b.check();
			GUI.updateGUI(b,list);
			canvas.update();
			canvas.redraw();
			if(b.totalFound()==30)
				GUI.gameOver(display,canvas,list);
		}
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {}
}