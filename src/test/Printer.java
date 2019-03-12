package test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Printer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		JButton jb = new JButton("Print");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PrinterJob job = PrinterJob.getPrinterJob();
				PageFormat pageformat = new PageFormat();

				Paper paper = new Paper();
				paper.setSize(730, 850);
				paper.setImageableArea(0, 0, 730, 850);
				pageformat.setPaper(paper);

				Book book = new Book();
				Printable testPrintable = new Printable() {

					@Override
					public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
						graphics.drawString("这是一个test1", 0, 0);
						graphics.drawString("这是一个test2", 0, 100);
						graphics.drawString("这是一个test3", 0, 200);
						try {
							Image img = ImageIO.read(new File("D:\\BottomBar.png"));
							graphics.drawImage(img, 100, 100, null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return Printable.PAGE_EXISTS;
					}
				};
				book.append(testPrintable, pageformat);

				job.setPageable(book);

				boolean doPrint = job.printDialog();

				if (doPrint) {
					try {
						job.print();
					} catch (Exception ex) {
					}
				}
			}

		});
		jf.setSize(400, 300);
		jf.getContentPane().add(jb);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}

}
