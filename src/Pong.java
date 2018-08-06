import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Pong extends JFrame {
    private PongPanel mp;

    public Pong() {
        super("Multitouch");

        mp = new PongPanel();
        Container c = getContentPane();
        c.add(mp);

        addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            { mp.closeDown();  }    // not likely to be used, since frame is undecorated
        });

        hideCursor(c);
        setUndecorated(true);
        pack();    // the panel makes itself full-screen size
        setResizable(false);
        setVisible(true);
    }
    private void hideCursor(Container c)
    {
        // create a transparent 16 x 16 pixel cursor image
        BufferedImage cursorIm = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // create a new blank cursor
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorIm, new Point(0, 0), "blank cursor");

        // assign the blank cursor to the JFrame
        c.setCursor(blankCursor);
    }  // end of hideCursor()

    public static void main(String[] args) {
        new Pong();
    }
}
